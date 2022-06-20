package galaksiya.demo.integration;

import galaksiya.demo.core.utilities.results.DataResult;
import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HomeControllerIT  extends AbstractionBaseTest{

/*

    @Container
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("home")
            .withPassword("password")
            .withUsername("username");

    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username",postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password",postgreSQLContainer::getPassword);
    }
*/

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeDao homeDao;


    @BeforeEach
    void setup(){
        homeDao.deleteAll();
    }


    @Test
    public void givenListOfHomes_whenGetAll_thenReturnHomesList() throws Exception{

        //given
        Home home1 = Home.builder()
                .propertyName("EvEge24575").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        Home home2 = Home.builder()
                .propertyName("EvErsin22").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        homeDao.save(home1);
        homeDao.save(home2);

        //when

        ResultActions response =mockMvc.perform(get("/api/homes/getall"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()",CoreMatchers.is(2)));

    }





}
