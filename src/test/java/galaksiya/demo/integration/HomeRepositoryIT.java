package galaksiya.demo.integration;

import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HomeRepositoryIT extends AbstractionBaseTest{

    @Autowired
    private HomeDao homeDao;

    /*
   private Home home;

   @BeforeEach
   public void setup(){
        home =  Home.builder()
               .propertyName("EvEge").price(5)
               .houseType("big").areaInSq(425)
               .bedroomsNo(4).bathroomsNo(3)
               .receptionsNo(8).location("London")
               .cityCountry("London").postalCode("35000")
               .build();

   }
   */

    //JUnit test for save home operation
    @DisplayName("JUnit test for save home operation")
    @Test
    public void givenHomeObject_whenSave_thenReturnSavedHome(){

        //given
        Home home = Home.builder()
                .propertyName("Ev")
                .price(5)
                .houseType("big")
                .areaInSq(425)
                .bedroomsNo(4)
                .bathroomsNo(3)
                .receptionsNo(8)
                .location("London")
                .cityCountry("London")
                .postalCode("35000")
                .build();

        //when
        Home savedHome = homeDao.save(home);

        //then verify
        assertThat(savedHome).isNotNull();
        assertThat(savedHome.getId()).isGreaterThan(0);

    }

    //JUnit test for get all homes operation
    @DisplayName("JUnit test for get all homes operation")
    @Test
    public void givenHomeList_whenFindAll_thenHomeList(){

        //given
        Home home1 = Home.builder()
                .propertyName("Ev1").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("Ev2").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();

        homeDao.save(home1);
        homeDao.save(home2);

        //when
        List<Home> homeList = homeDao.findAll();

        //then
        assertThat(homeList).isNotNull();
        assertThat(homeList.size()).isEqualTo(2);

    }

    // JUnit test for get get home by id operation
    @DisplayName("JUnit test for get get home by id operation")
    @Test
    public void givenHomeObject_whenFindById_thenReturnHomeObject(){

        //given
        Home home1 = Home.builder()
                .propertyName("Ev1").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);

        //when
        Home homeDB = homeDao.findById(home1.getId()).get();

        //then
        assertThat(homeDB).isNotNull();


    }

    //JUnit test for get home by propertyName operation
    @DisplayName("JUnit test for get home by propertyName operation")
    @Test
    public void givenHomePropertyName_whenGetByPropertyName_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);

        //when
        Home homeDB = homeDao.getByPropertyName(home1.getPropertyName()).get(0);

        //then
        assertThat(homeDB).isNotNull();

    }

    //JUnit test for get by propertyName And Location operation
    @DisplayName("JUnit test for get by propertyName And Location operation")
    @Test
    public void givenHomePropertyNameAndLocation_whenGetByPropertyNameAndLocation_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);

        //when
        Home homeDB = homeDao.getByPropertyNameAndLocation(home1.getPropertyName(),home1.getLocation()).get(0);

        //then
        assertThat(homeDB).isNotNull();
        assertThat(homeDB.getPropertyName()).isEqualTo(home1.getPropertyName());

    }
    //JUnit test for get by propertyName or location operation
    @DisplayName("JUnit test for get by propertyName or location operation")
    @Test
    public void givenHomeLocation_whenGetByPropertyNameOrLocation_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);

        //when
        Home homeDB = homeDao.getByPropertyNameOrLocation(null,home1.getLocation()).get(0);

        //then
        assertThat(homeDB).isNotNull();
        assertThat(homeDB.getPropertyName()).isEqualTo(home1.getPropertyName());

    }

    //JUnit test for get by propertyName contains
    @DisplayName("JUnit test for get by propertyName contains")
    @Test
    public void givenPropertyNameContains_whenGetByPropertyNameContains_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("Ev2").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);
        homeDao.save(home2);
        String homePropertyNameContains = "E";

        //when
        List<Home> homeList = homeDao.getByPropertyNameContains(homePropertyNameContains);

        //then
        assertThat(homeList).isNotNull();
        assertThat(homeList.get(0).getPropertyName()).isEqualTo(home1.getPropertyName());
        assertThat(homeList.get(1).getPropertyName()).isEqualTo(home2.getPropertyName());

    }

    //JUnit test for get by id in operation
    @DisplayName("JUnit test for get by id in operation")
    @Test
    public void givenHomesId_whenGetByIdIn_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);
        homeDao.save(home2);

        List<Integer> homesId = new ArrayList<>();
        homesId.add(home1.getId());
        homesId.add(home2.getId());

        //when
        List<Home> homeList = homeDao.getByIdIn(homesId);

        //then
        assertThat(homeList).isNotNull();
        assertThat(homeList.get(0).getPropertyName()).isEqualTo(home1.getPropertyName());
        assertThat(homeList.get(1).getPropertyName()).isEqualTo(home2.getPropertyName());

    }

    //JUnit test for get by property name starts with operation
    @DisplayName("JUnit test for get by property name starts with operation")
    @Test
    public void givenPropertyName_whenGetByPropertyNameStartsWith_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("Villa").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("Residence").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);
        homeDao.save(home2);

        String homePropertyName = "R";

        //when
        List<Home> homeList = homeDao.getByPropertyNameStartsWith(homePropertyName);

        //then
        assertThat(homeList).isNotNull();
        assertThat(homeList.get(0).getPropertyName()).isEqualTo(home2.getPropertyName());

    }

    //JUnit test for get by bedrooms no and location operation
    @DisplayName("JUnit test for get by bedrooms no and location operation")
    @Test
    public void givenHomeBedroomsNoAndLocation_whengetByBedroomsNoAndLocation_thenReturnHomeList(){
        //given
        Home home1 = Home.builder()
                .propertyName("Villa").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(3).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("Residence").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);
        homeDao.save(home2);

        //when
        List<Home> homeList = homeDao.getByBedroomsNoAndLocation(4,"London");

        //then
        assertThat(homeList).isNotNull();
        assertThat(homeList.get(0).getPropertyName()).isEqualTo(home2.getPropertyName());


    }



    //JUnit test for delete home operation
    @DisplayName("JUnit test for delete home operation")
    @Test
    public void givenHomeObject_whenDelete_thenRemoveHome(){

        //given
        Home home1 = Home.builder()
                .propertyName("EvErsin").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);

        //when
        homeDao.delete(home1);
        Optional<Home> optionalHome = homeDao.findById(home1.getId());

        //then
        assertThat(optionalHome).isEmpty();

    }

}
