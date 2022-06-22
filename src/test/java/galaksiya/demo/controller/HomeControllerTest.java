package galaksiya.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import galaksiya.demo.core.utilities.results.DataResult;
import galaksiya.demo.entities.concretes.Home;
import galaksiya.demo.service.abstracts.HomeService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;

    @Autowired
    private ObjectMapper objectMapper;


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
        doNothing().when(homeService).addMqMessage(isA(Home.class));
        homeService.addMqMessage(home1);


        // Assert
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(homeService,times(1)).addMqMessage(home1);


    }

    @Test
    public void givenHomeObject_whenCreateHome_thenReturnSavedHome() throws Exception{

        //given
        Home home = Home.builder()
                .propertyName("EvEge").price(5)
                .houseType("big").areaInSq(425)
                .bedroomsNo(4).bathroomsNo(3)
                .receptionsNo(8).location("London")
                .cityCountry("London").postalCode("35000")
                .build();
        DataResult<Home> savedDataResult = new DataResult<Home>(home,true,"Ev eklendi");
        //when
        when(homeService.add(home)).thenReturn(savedDataResult);
        ResultActions response = mockMvc.perform(post("/api/homes/add").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(home)));

        //then - verify
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.propertyName",CoreMatchers.is(home.getPropertyName())));


    }

    @Test
    public void givenListOfHomes_whenGetAll_thenReturnHomesList() throws Exception{

        //given
        Home home1 = Home.builder()
                .propertyName("EvEge").price(5)
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

        List<Home> listOfHomes = new ArrayList<>();
        listOfHomes.add(home1);
        listOfHomes.add(home2);
        DataResult<List<Home>> resultList = new DataResult<List<Home>>(listOfHomes,true,"Başarılı");

        //when
        when(homeService.getAll()).thenReturn(resultList);
        ResultActions response =mockMvc.perform(get("/api/homes/getall"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.size()",CoreMatchers.is(listOfHomes.size())));



    }









}