package com.gentech.event.controller;

import com.gentech.event.dto.EventDto;
import com.gentech.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class EventController {
    @Autowired
    private EventService eventService;
    //http://localhost:8082/v1/api/addEvent
    @PostMapping("/addEvent")
    ResponseEntity<EventDto> addEventItem(@RequestBody EventDto eventDto){
        return new ResponseEntity<EventDto>(eventService.addEvent(eventDto),HttpStatusCode.valueOf(201));
    }
    //http://localhost:8082/v1/api/getAll
    @GetMapping("/getAll")
    ResponseEntity<List<EventDto>> getAllEventItems(){
        return  new ResponseEntity<List<EventDto>>(eventService.getAllEvents(),HttpStatusCode.valueOf(200));
    }
    //http://localhost:8082/v1/api/getEventById
    @GetMapping("/getEventById")
    ResponseEntity<EventDto> getEventItemsById(@RequestParam int id) throws Exception{
        return new ResponseEntity<EventDto>(eventService.getEventById(id),HttpStatusCode.valueOf(200));
    }
    //http://localhost:8082/v1/api/updateEvent
    @PutMapping("/updateEvent")
    ResponseEntity<String> updateEventItemsById(@RequestParam int id,@RequestBody EventDto eventDto) throws Exception{
        return new ResponseEntity<String>(eventService.updateEventById(id,eventDto),HttpStatusCode.valueOf(201));
    }
    //http://localhost:8082/v1/api/deleteEvent/{id}
    @DeleteMapping("/deleteEvent/{id}")
    ResponseEntity<String> deleteEventItemsById(@PathVariable int id) throws Exception{
        return new ResponseEntity<String>(eventService.deleteEventById(id),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEventByName")
    ResponseEntity<EventDto> getEventByEventName(@RequestParam String name){
        return new ResponseEntity<EventDto>(eventService.getEventByName(name),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEventByDate")
    ResponseEntity<EventDto> getEventByEventDate(@RequestParam Date date){
        return new ResponseEntity<EventDto>(eventService.getEVentByDate(date),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEventByNameAndLoc")
    ResponseEntity<List<EventDto>> getEventByEventNameAndEventLoc(@RequestParam String name, @RequestParam String loc){
        return new ResponseEntity<List<EventDto>>(eventService.getEventByNameAndLocation(name,loc),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEventByLocContainingPartialLoc")
    ResponseEntity<List<EventDto>> getEventByEventNameAndEventLoc(@RequestParam String loc){
        return new ResponseEntity<List<EventDto>>(eventService.getEventByPartialLocationName(loc),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getAllEventByNameOrLoc")
    ResponseEntity<List<EventDto>> getAllEventByEventNameOrEventLoc(@RequestParam String name, @RequestParam String loc){
        return new ResponseEntity<List<EventDto>>(eventService.getEventByEventNameOrEventLoc(name,loc),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getAllEventBetweenDates")
    ResponseEntity<List<EventDto>> getAllEventListBetweenStartDateAndEndDate(@RequestParam Date startDate, @RequestParam Date endDate){
        return new ResponseEntity<List<EventDto>>(eventService.getEventBetweenStartAndEndDate(startDate,endDate),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEvents/pagination")
    ResponseEntity<List<EventDto>> getAllEventByPagination(@RequestParam int pageNumber,@RequestParam int pageSize){
        return new ResponseEntity<List<EventDto>>(eventService.getEvents(pageNumber,pageSize),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getEvents/PaginationAndSorting")
    ResponseEntity<List<EventDto>> getAllEventsByPaginationAndSorting(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String columnName){
        return new ResponseEntity<List<EventDto>>(eventService.getEventsBySorting(pageNumber,pageSize,columnName),HttpStatusCode.valueOf(200));
    }
}
