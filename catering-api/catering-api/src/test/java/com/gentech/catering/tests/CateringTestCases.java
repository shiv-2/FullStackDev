package com.gentech.catering.tests;

import com.gentech.catering.entity.CateringEntity;
import com.gentech.catering.repository.CateringRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CateringTestCases {
    @LocalServerPort
    private int port;
    private String baseURL="http://localhost";
    private static RestTemplate restTemplate;
    @Autowired
    private CateringRepository cateringRepository;
    @BeforeAll
    public static void initialization(){
        restTemplate= new RestTemplate();
    }
    @BeforeEach
    public void setUp(){
        baseURL=baseURL+":"+port+"/v1/api";
    }
    @AfterEach
    public void tearDown(){
        cateringRepository.deleteAll();
    }

    @Test
    public void shouldCreateCateringItem(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);

        //validation
        assertNotNull(newCateringEvent);
        assertThat(newCateringEvent.getCateringId()).isNotNull();
        assertThat(newCateringEvent.getCateringId()).isNotEqualTo(0);
        assertEquals("Paneer Tikka",newCateringEvent.getCateringName());
    }

    @Test
    public void shouldGetAllCatering(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1=new CateringEntity();
        cateringEntity1.setCateringName("Veg Sandwich");
        cateringEntity1.setCateringDesc("A fresh sandwich with assorted vegetables");
        cateringEntity1.setCateringCategory("Snacks");
        cateringEntity1.setCateringPrice(50.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(100);
        cateringEntity1.setCateringIsVeg(true);
        cateringEntity1.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        CateringEntity newCateringEvent1=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity1,CateringEntity.class);
        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getAll",List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(2);
    }
    @Test
    public void shouldGetCateringById(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        CateringEntity catering=restTemplate.getForObject(baseURL+"/getCateringById/"+newCateringEvent.getCateringId(),CateringEntity.class);
        //validation
        assertNotNull(catering);
        assertEquals("Paneer Tikka",catering.getCateringName());
    }

    @Test
    public void shouldUpdateCatering(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        newCateringEvent.setCateringQuantity(50);
        newCateringEvent.setCateringPrice(160);
        restTemplate.put(baseURL+"/updateCatering?id="+newCateringEvent.getCateringId(),newCateringEvent,CateringEntity.class);
        //validation
        assertEquals(50,newCateringEvent.getCateringQuantity());
        assertEquals(160,newCateringEvent.getCateringPrice());
    }

    @Test
    public void shouldDeleteCateringById(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1=new CateringEntity();
        cateringEntity1.setCateringName("Veg Sandwich");
        cateringEntity1.setCateringDesc("A fresh sandwich with assorted vegetables");
        cateringEntity1.setCateringCategory("Snacks");
        cateringEntity1.setCateringPrice(50.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(100);
        cateringEntity1.setCateringIsVeg(true);
        cateringEntity1.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        CateringEntity newCateringEvent1=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity1,CateringEntity.class);
        restTemplate.delete(baseURL+"/deleteCateringById?id="+newCateringEvent.getCateringId());
        //validation
        assertThat(cateringRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldGetCateringByName(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getCateringByName?name="+newCateringEvent.getCateringName(),List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetCateringByCategory(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getAllCateringByCategory?category="+newCateringEvent.getCateringCategory(),List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetCateringContainingPartialName(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity newCateringEvent=restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getCateringContainingName?partialName=Tikka",List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetAllVegCatering(){
        CateringEntity cateringEntity=new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1=new CateringEntity();
        cateringEntity1.setCateringName("Chicken Biriyani");
        cateringEntity1.setCateringDesc("Tasty and hot chicken biriyani");
        cateringEntity1.setCateringCategory("Lunch");
        cateringEntity1.setCateringPrice(200.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(40);
        cateringEntity1.setCateringIsVeg(false);
        cateringEntity1.setCateringIsActive(true);

        CateringEntity newCateringEntity = restTemplate.postForObject(baseURL+"/addCatering",cateringEntity,CateringEntity.class);
        CateringEntity newCateringEntity1 = restTemplate.postForObject(baseURL+"/addCatering",cateringEntity1,CateringEntity.class);

        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getVegCatering?value=true",List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetAllActiveOrInactiveCatering() {
        CateringEntity cateringEntity = new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1 = new CateringEntity();
        cateringEntity1.setCateringName("Chicken Biriyani");
        cateringEntity1.setCateringDesc("Tasty and hot chicken biriyani");
        cateringEntity1.setCateringCategory("Lunch");
        cateringEntity1.setCateringPrice(200.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(40);
        cateringEntity1.setCateringIsVeg(false);
        cateringEntity1.setCateringIsActive(false);

        CateringEntity newCateringEntity = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity, CateringEntity.class);
        CateringEntity newCateringEntity1 = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity1, CateringEntity.class);

        List<CateringEntity> cateringEntityList = restTemplate.getForObject(baseURL + "/getActiveOrInActiveCatering?active=true", List.class);

        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(1);
    }

    @Test
    public void shouldDisplayCateringBetweenPriceRanges(){
        CateringEntity cateringEntity = new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1 = new CateringEntity();
        cateringEntity1.setCateringName("Chicken Biriyani");
        cateringEntity1.setCateringDesc("Tasty and hot chicken biriyani");
        cateringEntity1.setCateringCategory("Lunch");
        cateringEntity1.setCateringPrice(250.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(40);
        cateringEntity1.setCateringIsVeg(false);
        cateringEntity1.setCateringIsActive(false);

        CateringEntity cateringEntity2 = new CateringEntity();
        cateringEntity2.setCateringName("Veg Biriyani");
        cateringEntity2.setCateringDesc("Tasty and hot Veg biriyani");
        cateringEntity2.setCateringCategory("Lunch");
        cateringEntity2.setCateringPrice(200.0);
        cateringEntity2.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity2.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity2.setCateringQuantity(40);
        cateringEntity2.setCateringIsVeg(true);
        cateringEntity2.setCateringIsActive(true);

        CateringEntity newCateringEntity = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity, CateringEntity.class);
        CateringEntity newCateringEntity1 = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity1, CateringEntity.class);
        CateringEntity newCateringEntity2 = restTemplate.postForObject(baseURL+"/addCatering",cateringEntity2,CateringEntity.class);

        List<CateringEntity> cateringEntityList = restTemplate.getForObject(baseURL+"/getCateringBetweenPrices?firstPrice=100.00&secPrice=200.00",List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(2);
    }

    @Test
    public void shouldDisplayCateringGreaterThanSpecifiedQuantity(){
        CateringEntity cateringEntity = new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1 = new CateringEntity();
        cateringEntity1.setCateringName("Chicken Biriyani");
        cateringEntity1.setCateringDesc("Tasty and hot chicken biriyani");
        cateringEntity1.setCateringCategory("Lunch");
        cateringEntity1.setCateringPrice(250.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(40);
        cateringEntity1.setCateringIsVeg(false);
        cateringEntity1.setCateringIsActive(false);

        CateringEntity cateringEntity2 = new CateringEntity();
        cateringEntity2.setCateringName("Veg Biriyani");
        cateringEntity2.setCateringDesc("Tasty and hot Veg biriyani");
        cateringEntity2.setCateringCategory("Lunch");
        cateringEntity2.setCateringPrice(200.0);
        cateringEntity2.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity2.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity2.setCateringQuantity(40);
        cateringEntity2.setCateringIsVeg(true);
        cateringEntity2.setCateringIsActive(true);

        CateringEntity newCateringEntity = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity, CateringEntity.class);
        CateringEntity newCateringEntity1 = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity1, CateringEntity.class);
        CateringEntity newCateringEntity2 = restTemplate.postForObject(baseURL+"/addCatering",cateringEntity2,CateringEntity.class);

        List<CateringEntity> cateringEntityList=restTemplate.getForObject(baseURL+"/getCateringGreaterThanQuantity?quantity=30",List.class);
        //validation
        assertNotNull(cateringEntityList);
        assertThat(cateringEntityList.size()).isEqualTo(2);
    }

    @Test
    public void shouldDeleteAllInactiveCaterings(){
        CateringEntity cateringEntity = new CateringEntity();
        cateringEntity.setCateringName("Paneer Tikka");
        cateringEntity.setCateringDesc("Spicy grilled paneer with herbs");
        cateringEntity.setCateringCategory("Dinner");
        cateringEntity.setCateringPrice(150.0);
        cateringEntity.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity.setCateringQuantity(30);
        cateringEntity.setCateringIsVeg(true);
        cateringEntity.setCateringIsActive(true);

        CateringEntity cateringEntity1 = new CateringEntity();
        cateringEntity1.setCateringName("Chicken Biriyani");
        cateringEntity1.setCateringDesc("Tasty and hot chicken biriyani");
        cateringEntity1.setCateringCategory("Lunch");
        cateringEntity1.setCateringPrice(250.0);
        cateringEntity1.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity1.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity1.setCateringQuantity(40);
        cateringEntity1.setCateringIsVeg(false);
        cateringEntity1.setCateringIsActive(false);

        CateringEntity cateringEntity2 = new CateringEntity();
        cateringEntity2.setCateringName("Veg Biriyani");
        cateringEntity2.setCateringDesc("Tasty and hot Veg biriyani");
        cateringEntity2.setCateringCategory("Lunch");
        cateringEntity2.setCateringPrice(200.0);
        cateringEntity2.setCateringAvailableFromDate(Date.valueOf("2024-09-29"));
        cateringEntity2.setCateringAvailableToDate(Date.valueOf("2024-09-30"));
        cateringEntity2.setCateringQuantity(40);
        cateringEntity2.setCateringIsVeg(true);
        cateringEntity2.setCateringIsActive(true);

        CateringEntity newCateringEntity = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity, CateringEntity.class);
        CateringEntity newCateringEntity1 = restTemplate.postForObject(baseURL + "/addCatering", cateringEntity1, CateringEntity.class);
        CateringEntity newCateringEntity2 = restTemplate.postForObject(baseURL+"/addCatering",cateringEntity2,CateringEntity.class);

        restTemplate.delete(baseURL+"/deleteAllInactiveCatering");
        //validation
        assertThat(cateringRepository.findAll().size()).isEqualTo(2);
    }
}
