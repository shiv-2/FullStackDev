package com.gentech.event.dto;

import lombok.*;

import java.sql.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private int event_Id;
    private String eventName;
    private Date eventDate;
    private int eventValidation;
    private String eventLoc;
    private String eventStatus;
    private Date createdAt;
    private Date updatedAt;
}
