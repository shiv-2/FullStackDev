package com.gentech.event.tests;

import com.gentech.event.entity.EventEntity;
import com.gentech.event.repository.EventRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat; // Correct import for assertThat
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventTestUnitCases {
    @LocalServerPort
    private int port;
    private String baseURL = "http://localhost";
    private static RestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    @BeforeAll
    public static void initialization() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseURL = baseURL + ":" + port + "/v1/api"; // Added missing "/"
    }

    @AfterEach
    public void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2020-10-02"));
        eventEntity.setEventValidation(2);
        eventEntity.setEventLoc("Mangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity newEventEntity = restTemplate.postForObject(baseURL+"/addEvent", eventEntity, EventEntity.class); // Changed to postForObject
        assertNotNull(newEventEntity);
        assertThat(newEventEntity.getEvent_Id()).isNotNull(); // Corrected assertThat
        assertEquals("anniversary party", newEventEntity.getEventName());
    }

    @Test
    public void shouldDisplayAllEvents(){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2020-10-02"));
        eventEntity.setEventValidation(2);
        eventEntity.setEventLoc("Mangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity eventEntity1 = new EventEntity();
        eventEntity.setEventName("birthday party");
        eventEntity.setEventDate(Date.valueOf("2019-10-02"));
        eventEntity.setEventValidation(1);
        eventEntity.setEventLoc("Bangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity newEventEntity= restTemplate.postForObject(baseURL+"/addEvent",eventEntity,EventEntity.class);
        EventEntity newEventEntity1 = restTemplate.postForObject(baseURL+"/addEvent",eventEntity1,EventEntity.class);
        List<EventEntity> eventEntityList = restTemplate.getForObject(baseURL+"/getAll",List.class);

        //validation
        assertNotNull(eventEntityList);
        assertThat(eventEntityList.size()).isEqualTo(2);

    }

    @Test
    public void shouldDisplayEventByEventId(){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2020-10-02"));
        eventEntity.setEventValidation(2);
        eventEntity.setEventLoc("Mangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity newEventEntity = restTemplate.postForObject(baseURL+"/addEvent",eventEntity,EventEntity.class);
        EventEntity existingEvent = restTemplate.getForObject(baseURL + "/getEventById?id=" + newEventEntity.getEvent_Id(), EventEntity.class);

        //validation
        assertNotNull(existingEvent);
        assertEquals("anniversary party",existingEvent.getEventName());
    }

    @Test
    public void shouldUpdateExistingEvent(){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2020-10-02"));
        eventEntity.setEventValidation(2);
        eventEntity.setEventLoc("Mangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity newEventEntity = restTemplate.postForObject(baseURL+"/addEvent",eventEntity,EventEntity.class);
        newEventEntity.setEventName("birthday party");
        restTemplate.put(baseURL+"/updateEvent?id="+newEventEntity.getEvent_Id(),newEventEntity,EventEntity.class);

        //validation
        assertEquals("birthday party",newEventEntity.getEventName());
    }

    @Test
    public void shouldDeleteExistingEvent(){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2020-10-02"));
        eventEntity.setEventValidation(2);
        eventEntity.setEventLoc("Mangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity eventEntity1 = new EventEntity();
        eventEntity.setEventName("birthday party");
        eventEntity.setEventDate(Date.valueOf("2019-10-02"));
        eventEntity.setEventValidation(1);
        eventEntity.setEventLoc("Bangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity newEventEntity = restTemplate.postForObject(baseURL+"/addEvent",eventEntity,EventEntity.class);
        EventEntity newEventEntity1 = restTemplate.postForObject(baseURL+"/addEvent",eventEntity1,EventEntity.class);
        restTemplate.delete(baseURL+"/deleteEvent/"+newEventEntity.getEvent_Id());

        //validation
        assertThat(eventRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void shouldDisplayEventByEventName(){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName("anniversary party");
        eventEntity.setEventDate(Date.valueOf("2021-05-30"));
        eventEntity.setEventValidation(1);
        eventEntity.setEventLoc("Bangalore");
        eventEntity.setEventStatus("accepted");

        EventEntity eventEntity1 = new EventEntity();
        eventEntity1.setEventName("marraige event");
        eventEntity1.setEventDate(Date.valueOf("2019-12-30"));
        eventEntity1.setEventValidation(1);
        eventEntity1.setEventLoc("Tumkur");
        eventEntity1.setEventStatus("accepted");

        EventEntity eventEntity2 = new EventEntity();
        eventEntity2.setEventName("sports event");
        eventEntity2.setEventDate(Date.valueOf("2020-12-30"));
        eventEntity2.setEventValidation(12);
        eventEntity2.setEventLoc("Bangalore");
        eventEntity2.setEventStatus("accepted");

        EventEntity newEventEntity = restTemplate.postForObject(baseURL+"/addEvent",eventEntity,EventEntity.class);
        EventEntity newEventEntity1 = restTemplate.postForObject(baseURL+"/addEvent",eventEntity1,EventEntity.class);
        EventEntity newEventEntity2 = restTemplate.postForObject(baseURL+"/addEvent",eventEntity2,EventEntity.class);

        List<EventEntity> eventEntityList = restTemplate.getForObject(baseURL+"/getAllEventByNameOrLoc?name="+newEventEntity.getEventName()+"&loc="+newEventEntity.getEventLoc(),List.class);

        //validation
        assertNotNull(eventEntityList);
        assertThat(eventEntityList.size()).isEqualTo(2);
    }
}
