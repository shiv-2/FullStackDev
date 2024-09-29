package com.gentech.event.repository;

import com.gentech.event.dto.EventDto;
import com.gentech.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Integer> {
    EventEntity findByEventName(String name);
    EventEntity findByEventDate(Date date);
    List<EventEntity> findByEventNameAndEventLoc(String name,String loc);
    List<EventEntity> findByEventLocContaining(String loc);

    @Query("FROM EventEntity WHERE eventName=:name OR eventLoc=:loc")
    List<EventEntity> getAllEventByNameOrLocation(String name,String loc);

    @Query("FROM EventEntity WHERE eventDate BETWEEN :startDate AND :endDate")
    List<EventEntity> getAllEventBetweenStartAndEndDate(Date startDate, Date endDate);
}
