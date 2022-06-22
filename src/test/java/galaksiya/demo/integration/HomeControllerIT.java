package galaksiya.demo.integration;

import galaksiya.demo.core.utilities.results.DataResult;
import galaksiya.demo.dataAccess.abstracts.HomeDao;
import galaksiya.demo.entities.concretes.Home;
import galaksiya.demo.service.abstracts.HomeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.google.common.base.Verify.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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


    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;


    @BeforeEach
    void setup(){
        homeDao.deleteAll();
    }

    @Test
    void addMqTest() throws Exception{

        // Setup
        Home home1 = Home.builder().id(1)
                .propertyName("EvEge24575").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();

        // Execute
        ResultActions response =mockMvc.perform(post("/api/homes/addMq").contentType(MediaType
                .APPLICATION_JSON).content(objectMapper.writeValueAsString(home1)));


        // Assert
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertThat(homeDao.getByPropertyName("EvEge24575")).isNotNull();
        assertThat(homeDao.getByPropertyName("EvEge24575").get(0)).isEqualTo(home1);


    }

    /*

    @Test
    void addMqMessage() throws Exception{

        // Setup
        Home home1 = Home.builder()
                .propertyName("EvEge24575").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();


        // Execute
        ResultActions response =mockMvc.perform(post("/api/homes/addMq").contentType(MediaType
                .APPLICATION_JSON).content(objectMapper.writeValueAsString(home1)));
        homeService.addMqMessage(home1);


        // Assert
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        // assertThat(homeDao.getByPropertyName("EvEge24575").get(0)).isEqualTo(home1);


    }


     */

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
