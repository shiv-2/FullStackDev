package com.gentech.event.serviceimpl;

import com.gentech.event.dto.EventDto;
import com.gentech.event.entity.EventEntity;
import com.gentech.event.mapper.EventMapper;
import com.gentech.event.repository.EventRepository;
import com.gentech.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Override
    public EventDto addEvent(EventDto eventDto) {
        EventEntity eventEntity= EventMapper.mapResDtoToRes(eventDto);
        eventRepository.save(eventEntity);
        eventDto=EventMapper.mapResToResDto(eventEntity);
        return eventDto;
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<EventEntity> eventEntity=eventRepository.findAll();
        List<EventDto> eventDto=new ArrayList<>();
        for(int i=0;i<eventEntity.size();i++){
            eventDto.add(EventMapper.mapResToResDto(eventEntity.get(i)));
        }
        return eventDto;
    }

    @Override
    public EventDto getEventById(int id) throws Exception {
        EventEntity eventEntity=eventRepository.findById(id).orElse(null);
        if(eventEntity!=null){
            EventDto eventDto=EventMapper.mapResToResDto(eventEntity);
            return eventDto;
        }
        throw new Exception("The given id "+id+" is not found in the database");
    }

    @Override
    public String updateEventById(int id, EventDto eventDto) throws Exception {
        EventEntity eventEntity=eventRepository.findById(id).orElse(null);
        if(eventEntity!=null){
            eventEntity.setEventName(eventDto.getEventName());
            eventEntity.setEventDate(eventDto.getEventDate());
            eventEntity.setEventValidation(eventDto.getEventValidation());
            eventEntity.setEventLoc(eventDto.getEventLoc());
            eventEntity.setEventStatus(eventDto.getEventStatus());
            eventRepository.save(eventEntity);
            return "The given data with values ["+eventEntity.getEventName()+"] "+"["+eventEntity.getEventDate()+"] "+"["+eventEntity.getEventValidation()+"] "+"["+eventEntity.getEventLoc()+"] "+"["+eventEntity.getEventStatus()+"] updated successfully in the database";
        }
        throw new Exception("The given id "+id+" is not found in the database");
    }

    @Override
    public String deleteEventById(int id) throws Exception {
        EventEntity eventEntity=eventRepository.findById(id).orElse(null);
        if(eventEntity!=null){
            eventRepository.deleteById(id);
            return "Event with event id "+id+" deleted successfully in the database";
        }
        throw new Exception("The given id "+id+" is not found in the database");
    }

    @Override
    public EventDto getEventByName(String name) {
        EventEntity eventEntity=eventRepository.findByEventName(name);
        if(eventEntity!=null){
            EventDto eventDto=EventMapper.mapResToResDto(eventEntity);
            return eventDto;
        }
        return null;
    }

    @Override
    public EventDto getEVentByDate(Date date) {
        EventEntity eventEntity=eventRepository.findByEventDate(date);
        if(eventEntity!=null){
            EventDto eventDto=EventMapper.mapResToResDto(eventEntity);
            return eventDto;
        }
        return null;
    }

    @Override
    public List<EventDto> getEventByNameAndLocation(String name, String loc) {
        List<EventEntity> eventEntityList = eventRepository.findByEventNameAndEventLoc(name,loc);
        List<EventDto> eventDtoList=new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(int i=0;i<eventEntityList.size();i++){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntityList.get(i)));
            }
            return eventDtoList;
        }
        return null;
    }

    @Override
    public List<EventDto> getEventByPartialLocationName(String loc) {
        List<EventEntity> eventEntityList = eventRepository.findByEventLocContaining(loc);
        List<EventDto> eventDtoList=new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(EventEntity eventEntity:eventEntityList){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntity));
            }
            return eventDtoList;
        }
        return null;
    }

    @Override
    public List<EventDto> getEventByEventNameOrEventLoc(String name, String loc) {
        List<EventEntity> eventEntityList = eventRepository.getAllEventByNameOrLocation(name,loc);
        List<EventDto> eventDtoList=new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(int i=0;i<eventEntityList.size();i++){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntityList.get(i)));
            }
            return eventDtoList;
        }
        return null;
    }

    @Override
    public List<EventDto> getEventBetweenStartAndEndDate(Date startDate, Date endDate) {
        List<EventEntity> eventEntityList = eventRepository.getAllEventBetweenStartAndEndDate(startDate,endDate);
        List<EventDto> eventDtoList = new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(int i=0;i<eventEntityList.size();i++){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntityList.get(i)));
            }
            return eventDtoList;
        }
        return null;
    }

    @Override
    public List<EventDto> getEvents(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber,pageSize);
        List<EventEntity> eventEntityList= eventRepository.findAll(pages).getContent();
        List<EventDto> eventDtoList=new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(int i=0;i<eventEntityList.size();i++){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntityList.get(i)));
            }
            return eventDtoList;
        }
        return null;
    }

    @Override
    public List<EventDto> getEventsBySorting(int pageNumber, int pageSize, String columnName) {
        Sort sort=Sort.by(Sort.Direction.ASC,columnName);
        Pageable pages = PageRequest.of(pageNumber,pageSize,sort);
        List<EventEntity> eventEntityList = eventRepository.findAll(pages).getContent();
        List<EventDto> eventDtoList=new ArrayList<>();
        if(!eventEntityList.isEmpty()){
            for(int i=0;i<eventEntityList.size();i++){
                eventDtoList.add(EventMapper.mapResToResDto(eventEntityList.get(i)));
            }
            return eventDtoList;
        }
        return null;
    }
}
