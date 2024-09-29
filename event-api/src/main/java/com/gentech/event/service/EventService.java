package com.gentech.event.service;

import com.gentech.event.dto.EventDto;

import java.sql.Date;
import java.util.List;

public interface EventService {
    EventDto addEvent(EventDto eventDto);
    List<EventDto> getAllEvents();
    EventDto getEventById(int id) throws Exception;
    String updateEventById(int id,EventDto eventDto) throws Exception;
    String deleteEventById(int id) throws Exception;

    EventDto getEventByName(String name);
    EventDto getEVentByDate(Date date);
    List<EventDto> getEventByNameAndLocation(String name, String loc);
    List<EventDto> getEventByPartialLocationName(String loc);
    List<EventDto> getEventByEventNameOrEventLoc(String name,String loc);
    List<EventDto> getEventBetweenStartAndEndDate(Date startDate,Date endDate);

    List<EventDto> getEvents(int pageNumber, int pageSize);
    List<EventDto> getEventsBySorting(int pageNumber,int pageSize,String columnName);
}