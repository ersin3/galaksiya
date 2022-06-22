package galaksiya.demo.api.controllers;

import galaksiya.demo.service.abstracts.HomeService;
import galaksiya.demo.core.utilities.results.DataResult;
import galaksiya.demo.core.utilities.results.Result;
import galaksiya.demo.entities.concretes.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/homes")
public class HomesController {

    private HomeService homeService;

    @Autowired
    public HomesController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/getall")
    public DataResult<List<Home>> getAll(){

        return this.homeService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Home home){
        return this.homeService.add(home);
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody Home home){

        return this.homeService.updateById(home);
    }

    @PutMapping("/updateByIdObject")
    public Result  updateById(@RequestBody Home home,@RequestParam int id){
      return  this.homeService.updateById(home,id);
    }

    @GetMapping("/findById")
    public DataResult<Optional<Home>> findById(@RequestParam int homeId){
        return this.homeService.findById(homeId);
    }

    @GetMapping("/getByPropertyName")
    public DataResult<List<Home>> getByPropertyName(@RequestParam String propertyName){
        return this.homeService.getByPropertyName(propertyName);
    }

    @GetMapping("/getByPropertyNameAndLocation")
    public DataResult<List<Home>> getByPropertyNameAndLocation(@RequestParam String propertyName, String location){
        return this.homeService.getByPropertyNameAndLocation(propertyName,location);
    }

    @GetMapping("/getByPropertyNameContains")
    public DataResult<List<Home>> getByPropertyNameContains(@RequestParam String propertyName){
        return this.homeService.getByPropertyNameContains(propertyName);
    }

    @GetMapping("/getAllByPage")
    public DataResult<List<Home>> getAll(int pageNo, int pageSize){
        return this.homeService.getAll(pageNo,pageSize);
    }

    @GetMapping("/getAllSortedDesc")
    public DataResult<List<Home>> getAllSorted(){
        return this.homeService.getAllSorted();
    }

    @GetMapping("/getAllSortedASC")
    public DataResult<List<Home>> getAllSortedASC(){
        return this.homeService.getAllId();
    }

    @GetMapping("/getByPropertyNameOrLocation")
    public DataResult<List<Home>> getByPropertyNameOrLocation(@RequestParam String propertyName, String location){
        return this.homeService.getByPropertyNameOrLocation(propertyName,location);
    }

    @GetMapping("/getByIdIn")
    public DataResult<List<Home>> getByIdIn(@RequestParam List<Integer> homesId){
        return this.homeService.getByIdIn(homesId);
    }

    @GetMapping("/getByPropertyNameStartsWith")
    public DataResult<List<Home>> getByPropertyNameStartsWith(@RequestParam String propertyName){
        return this.homeService.getByPropertyNameStartsWith(propertyName);
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam int homeId){
        return this.homeService.deleteById(homeId);
    }

    @DeleteMapping("/deleteall")
    public Result deleteAll(){
        return this.homeService.deleteAll();
    }

    @GetMapping("/getByBedroomsNoAndLocation")
    public DataResult<List<Home>> getByBedroomsNoAndLocation(@RequestParam int bedroomsNo,@RequestParam String location){
        return this.homeService.getByBedroomsNoAndLocation(bedroomsNo,location);
    }

    @PostMapping("/addMq")
    public ResponseEntity<String> addMq(@RequestBody Home home){
        this.homeService.addMq(home);
        return ResponseEntity.ok("İşleminiz başarıyla alınmıştır!");
    }




}
