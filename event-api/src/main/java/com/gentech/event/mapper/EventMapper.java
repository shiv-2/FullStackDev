package com.gentech.event.mapper;

import com.gentech.event.dto.EventDto;
import com.gentech.event.entity.EventEntity;

public class EventMapper {
    public static EventEntity mapResDtoToRes(EventDto eventDto){
        EventEntity eventEntity=new EventEntity(
                eventDto.getEvent_Id(),
                eventDto.getEventName(),
                eventDto.getEventDate(),
                eventDto.getEventValidation(),
                eventDto.getEventLoc(),
                eventDto.getEventStatus(),
                eventDto.getCreatedAt(),
                eventDto.getUpdatedAt()
        );
        return eventEntity;
    }
    public static EventDto mapResToResDto(EventEntity eventEntity){
        EventDto eventDto=new EventDto(
                eventEntity.getEvent_Id(),
                eventEntity.getEventName(),
                eventEntity.getEventDate(),
                eventEntity.getEventValidation(),
                eventEntity.getEventLoc(),
                eventEntity.getEventStatus(),
                eventEntity.getCreatedAt(),
                eventEntity.getUpdatedAt()
        );
        return eventDto;
    }
}