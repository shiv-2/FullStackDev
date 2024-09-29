package com.gentech.event.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int event_Id;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "event_validation")
    private int eventValidation;
    @Column(name = "event_loc")
    private String eventLoc;
    @Column(name = "event_status")
    private String eventStatus;
    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private Date updatedAt;
}
