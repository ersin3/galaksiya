package galaksiya.demo.dataAccess.abstracts;

import galaksiya.demo.entities.concretes.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeDao extends JpaRepository<Home,Integer> {

        List<Home> getByPropertyName(String propertyName);

        List<Home> getByPropertyNameAndLocation(String propertyName,String location);

        List<Home> getByPropertyNameOrLocation(String propertyName, String location);

        List<Home> getByIdIn(List<Integer> homesId);

        List<Home> getByPropertyNameContains(String propertyName);

        List<Home> getByPropertyNameStartsWith(String propertyName);

        List<Home> getByBedroomsNoAndLocation(int bedroomsNo, String location);

}
