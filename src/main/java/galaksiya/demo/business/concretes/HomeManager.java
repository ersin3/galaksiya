package galaksiya.demo.business.concretes;

import galaksiya.demo.business.abstracts.HomeService;
import galaksiya.demo.core.utilities.results.*;
import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HomeManager implements HomeService {
    List<Home> homes;

    private HomeDao homeDao;

    @Autowired
    public HomeManager(HomeDao homeDao) {
        this.homeDao = homeDao;
    }



    @Override
    public DataResult<List<Home>> getAll() {
        return new SuccessDataResult<List<Home>>(this.homeDao.findAll(),"Data Listelendi");

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

    public Result  updateById(Home home){
        homes = this.homeDao.findAll();
        for(int i = 0;i<homes.size();i++){
            if (homes.get(i).getId()==home.getId()){
                this.homeDao.save(home);
                return new SuccessResult("Ev güncellendi");
            }
        }
        return new ErrorResult("Böyle bir id bulunmamaktadır");
    }

    public Result  updateById(Home home,int id){
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
    public Result add(Home home) {
        this.homeDao.save(home);
        return new SuccessResult("Ürün eklendi");

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
