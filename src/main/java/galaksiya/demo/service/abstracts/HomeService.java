package galaksiya.demo.service.abstracts;

import galaksiya.demo.core.utilities.results.DataResult;
import galaksiya.demo.core.utilities.results.Result;
import galaksiya.demo.entities.concretes.Home;

import java.util.List;
import java.util.Optional;

public interface HomeService  {
   DataResult<List<Home>> getAll();

   DataResult<Optional<Home>> findById(int homeId);

   Result deleteById(int homeId);

   DataResult<Home> updateById(Home home);

   DataResult<Home> updateById(Home home,int homeId);

   DataResult<List<Home>> getAllSorted();

   DataResult<List<Home>> getAll(int pageNo,int pageSize);

   DataResult<Home> add(Home home);

   String addMq(Home home);

   void addMqMessage(Home home);

   Result deleteAll();

   DataResult<List<Home>> getByPropertyName(String propertyName);

   DataResult<List<Home>> getByPropertyNameAndLocation(String propertyName,String location);

   DataResult<List<Home>> getByPropertyNameOrLocation(String propertyName, String location);

   DataResult<List<Home>> getByIdIn(List<Integer> homesId);

   DataResult<List<Home>> getByPropertyNameContains(String propertyName);

   DataResult<List<Home>> getByPropertyNameStartsWith(String propertyName);

   DataResult<List<Home>> getByBedroomsNoAndLocation(int bedroomsNo,String location);

   DataResult<List<Home>> getAllId();




}
