package galaksiya.demo.service;

import galaksiya.demo.service.concretes.HomeManager;
import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HomeManagerTest {

    @Mock
    private HomeDao homeDao;

    @InjectMocks
    private HomeManager homeService;

    /*
    @BeforeEach
    void setUp(){
        homeService = new HomeManager(homeDao);

    }
    */

    @Test
    void canGetAll() {

        //when
        homeService.getAll();

        //then verify
        verify(homeDao).findAll();

    }

    @Test
    void givenHomeId_whenGetHomeById_thenReturnHomeObject() {

        //given
        Optional<Home> home = Optional.ofNullable(new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500"));

        //when
        when(homeDao.findById(5)).thenReturn(home);

        //then verify
        assertEquals("must be equals",home,homeService.findById(5).getData());

    }


    @Test
    void givenHomeObject_whenSaveHome_thenReturnHomeObject() {

        //given
        Home home = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");

        //when
        when(homeDao.save(home)).thenReturn(home);
        Home home1 = homeService.add(home).getData();

        //then verify
        assertEquals("must be equal",home,home1);

    }

    @Test
    void givenHomeId_whenDeleteHome_thenNothing() {

        //given
        int homeId = 5;

        //when
        willDoNothing().given(homeDao).deleteById(homeId);
        homeService.deleteById(homeId);

        //then verify
        verify(homeDao,times(1)).deleteById(homeId);

    }

    @Test
    void givenHomeObject_whenUpdateHome_thenReturnUpdateHome() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        lenient().when(homeDao.save(home1)).thenReturn(home1);
        home1.setPropertyName("Update");
        home1.setPrice(3500);

        //when
        Home updateHome = homeService.updateById(home1).getData();

        //then verify
        assertEquals("Must be equals","Update",updateHome.getPropertyName());
        assertEquals("Must be equals",3500.0,updateHome.getPrice());


    }

    @Test
    void givenHomeObject_whenUpdateIdHome_thenReturnUpdateHome() {

        //given
        Home home1 = new Home(0,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        int updateHomeId = 5;
        lenient().when(homeDao.save(home1)).thenReturn(home1);
        home1.setPropertyName("updateDeneme");
        home1.setPrice(4800);
        home1.setId(updateHomeId);

        //when
        Home updateHome = homeService.updateById(home1,updateHomeId).getData();

        //then verify
        assertEquals("Must be equals","updateDeneme",updateHome.getPropertyName());
        assertEquals("Must be equals",4800.0,updateHome.getPrice());

    }

    @Test
    void givenHomeList_whenGetAllHomeASC_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");

        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"London"
                ,"London","3500");

        Sort sort = Sort.by(Sort.Direction.ASC,"id");

        //when
        when(homeDao.findAll(sort)).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getAllId().getData();

        //then verify
        assertEquals("Must be equals home1",home1,homeList.get(0));
        assertEquals("Must be equals",2,homeList.size());

    }

    @Test
    void givenHomeList_whenGetAllHomeDESC_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");

        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"London"
                ,"London","3500");

        Sort sort = Sort.by(Sort.Direction.DESC,"id");

        //when
        when(homeDao.findAll(sort)).thenReturn(List.of(home2,home1));
        List<Home> homeList = homeService.getAllSorted().getData();

        //then verify
        assertEquals("Must be equals home1",home2,homeList.get(0));
        assertEquals("Must be equals",2,homeList.size());

    }

    @Test
    void givenHomeList_whenGetByPropertyName_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"London"
                ,"London","3500");
        String propertyName = "Ev1";

        //when
        when(homeDao.getByPropertyName(propertyName)).thenReturn(List.of(home1));
        List<Home> homeList = homeService.getByPropertyName(propertyName).getData();

        //then verify
        assertNotNull("homeList cannot be null",homeList);
        assertEquals("homeList size must be 1",1,homeList.size());
    }

    @Test
    void givenHomeObject_whenGetByPropertyNameAndLocation_thenReturnHomeList() {

        //given
        Home home = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        String propertyName = "Ev";
        String location = "London";

        //when
        when(homeDao.getByPropertyNameAndLocation(propertyName,location)).thenReturn(List.of(home));
        List<Home> homeList = homeService.getByPropertyNameAndLocation(propertyName,location).getData();

        //then verify
        assertNotNull("homeList cannot be null",homeList);
        assertEquals("home must be equals homeList.get 0 index",home,homeList.get(0));


    }

    @Test
    void givenHomeList_whenGetByPropertyNameOrLocation_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"Izmir"
                ,"London","3500");
        String propertyName = "Ev1";
        String location = "Izmir";

        //when
        when(homeDao.getByPropertyNameOrLocation(propertyName,location)).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getByPropertyNameOrLocation(propertyName,location).getData();

        //then verify
        assertEquals("must be equals",home1,homeList.get(0));
        assertEquals("must be equals",home2,homeList.get(1));

    }

    @Test
    void givenHomeList_whenGetByIdIn_thenReturnHomeList() {

        //given
        List<Integer> homesId = new ArrayList<>();
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"Izmir"
                ,"London","3500");
        homesId.add(5);
        homesId.add(10);

        //when
        when(homeDao.getByIdIn(homesId)).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getByIdIn(homesId).getData();

        //then verify
        assertEquals("must be equals",home1,homeList.get(0));
        assertEquals("must be equals",home2,homeList.get(1));

    }

    @Test
    void givenHomeList_whenGetByPropertyNameContains_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1Ersin",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(10,"Ev2Ersin",15,"small",300
                ,4,3,8,"Izmir"
                ,"London","3500");
        String homePropertyName = "Ersin";

        //when
        when(homeDao.getByPropertyNameContains(homePropertyName)).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getByPropertyNameContains(homePropertyName).getData();

        //then verify
        assertNotNull("homeList cannot be null",homeList);
        assertEquals("homeList size must be 2",2,homeList.size());

    }

    @Test
    void givenHomeList_whenGetByPropertyNameStartsWith_thenReturnHomeList() {
        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(10,"Ev2",15,"small",300
                ,4,3,8,"Izmir"
                ,"London","3500");
        String homePropertyName = "E";

        //when
        when(homeDao.getByPropertyNameStartsWith(homePropertyName)).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getByPropertyNameStartsWith(homePropertyName).getData();

        //then verify
        assertNotNull("homeList cannot be null",homeList);
        assertEquals("homeList size must be 2",2,homeList.size());
    }

    @Test
    void givenHomeList_whenGetByBedroomsNoAndLocation_thenReturnHomeList() {

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        int bedroomsNo = 4;
        String location = "London";

        //when
        when(homeDao.getByBedroomsNoAndLocation(bedroomsNo,location)).thenReturn(List.of(home1));
        List<Home> homeList = homeService.getByBedroomsNoAndLocation(bedroomsNo,location).getData();

        //then verify
        assertNotNull("homeList cannot be null",homeList);
        assertEquals("home must be equals homeList.get 0 index",home1,homeList.get(0));

    }

    @Test
    @Disabled
    void givenHomeList_whenGetAllPageable_thenReturnHomeList(){

        //given
        Home home1 = new Home(5,"Ev1",5,"big",425
                ,4,3,8,"London"
                ,"London","3500");
        Home home2 = new Home(6,"Ev2",15,"small",300
                ,4,3,8,"Izmir"
                ,"London","3500");

        Pageable pageable = PageRequest.of(0,2);

        //when
        when(homeDao.findAll(pageable).getContent()).thenReturn(List.of(home1,home2));
        List<Home> homeList = homeService.getAll(1,2).getData();

        //then verify
        assertEquals("must be equals",home1,homeList.get(0));
        assertEquals("must be equals",home2,homeList.get(1));


    }

}