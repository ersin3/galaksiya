package galaksiya.demo.service.concretes;

import galaksiya.demo.service.abstracts.HomeService;
import galaksiya.demo.core.utilities.results.*;
import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HomeManager implements HomeService {

    private HomeDao homeDao;
    private AmqpTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public HomeManager(HomeDao homeDao,AmqpTemplate rabbitTemplate,DirectExchange exchange) {
        this.homeDao = homeDao;
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    @Value("${sample.rabbitmq.queue}")
    String queueName;

    @Override
    public DataResult<List<Home>> getAll() {
        return new SuccessDataResult<List<Home>>(this.homeDao.findAll(),"Data Listelendi");

    }



    @Override
    public Result deleteAll() {
        this.homeDao.deleteAll();
        return new SuccessResult("Datalar Silindi");
    }

    public String addMq(Home home){
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey,home);
        return "Isteginiz olusturulmustur";
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public void addMqMessage(Home home){
        if (!home.getPropertyName().isEmpty()) {
            homeDao.save(home);
        }
       else{ System.out.println("Ev ismi boş Olamaz");}
    }

    @Override
    public DataResult<Optional<Home>> findById(int homeId) {
        return new SuccessDataResult<Optional<Home>>(this.homeDao.findById(homeId),"Ev bulundu");
    }

    @Override
    public Result deleteById(int homeId) {
        this.homeDao.deleteById(homeId);
        return new SuccessResult("Ev Silindi");
    }

    public DataResult<Home>  updateById(Home home){

        Optional<Home> updateHome = homeDao.findById(home.getId());

        if (!updateHome.isPresent()){
            return new ErrorDataResult<Home>(home,"Böyle bir ev bulunmamaktadır");
        }

        this.homeDao.save(home);

        return new SuccessDataResult<Home>(home,"Ev güncellendi");

        /*
        homes = this.homeDao.findAll();
        for(int i = 0;i<homes.size();i++){
            if (homes.get(i).getId()==home.getId()){
                this.homeDao.save(home);
                return new SuccessResult("Ev güncellendi");
            }
        }
        return new ErrorResult("Böyle bir id bulunmamaktadır");

         */
    }

    public DataResult<Home>  updateById(Home home,int id){

        Optional<Home> updateHome = homeDao.findById(id);

        if (!updateHome.isPresent()){
            return new ErrorDataResult<Home>(home,"böyle bir Ev bulunmamaktadır");
        }

        home.setId(id);
        this.homeDao.save(home);

        return new SuccessDataResult<Home>(home,"Ev güncellenmiştir");


             /*
        homes = this.homeDao.findAll();
        for (int i = 0;i<homes.size();i++){
            if (homes.get(i).getId()==id){
                homes.set(i,home);
                home.setId(id);
                this.homeDao.save(home);
                return new SuccessResult("Ev güncellendi");
            }
        }

        return new ErrorResult("Böyle bir id bulunmamaktadır");
         */

    }

    public DataResult<List<Home>> getAllId(){
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return new SuccessDataResult<List<Home>>(this.homeDao.findAll(sort),"Başarılı");
    }

    @Override
    public DataResult<List<Home>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return new SuccessDataResult<List<Home>>(this.homeDao.findAll(sort),"Başarılı");
    }

    @Override
    public DataResult<List<Home>> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo-1,pageSize);

        return new SuccessDataResult<List<Home>>(this.homeDao.findAll(pageable).getContent(),"Başarılı");
    }

    @Override
    public DataResult<Home> add(Home home) {

        this.homeDao.save(home);
        return new SuccessDataResult<Home>(home,"Ev eklendi");

    }

    @Override
    public DataResult<List<Home>> getByPropertyName(String propertyName) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByPropertyName(propertyName),"Data Listelendi");
    }

    @Override
    public DataResult<List<Home>> getByPropertyNameAndLocation(String propertyName, String location) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByPropertyNameAndLocation(propertyName,location));
    }

    @Override
    public DataResult<List<Home>> getByPropertyNameOrLocation(String propertyName, String location) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByPropertyNameOrLocation(propertyName,location),"Data Listelendi");
    }

    @Override
    public DataResult<List<Home>> getByIdIn(List<Integer> homesId) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByIdIn(homesId),"Data Listelendi");
    }

    @Override
    public DataResult<List<Home>> getByPropertyNameContains(String propertyName) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByPropertyNameContains(propertyName),"Data Listelendi");
    }

    @Override
    public DataResult<List<Home>> getByPropertyNameStartsWith(String propertyName) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByPropertyNameStartsWith(propertyName),"Data Listelendi");
    }

    @Override
    public DataResult<List<Home>> getByBedroomsNoAndLocation(int bedroomsNo, String location) {
        return new SuccessDataResult<List<Home>>(this.homeDao.getByBedroomsNoAndLocation(bedroomsNo,location),"Data Listelendi");
    }


}
