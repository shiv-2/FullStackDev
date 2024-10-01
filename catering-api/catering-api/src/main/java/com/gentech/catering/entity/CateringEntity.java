package com.gentech.catering.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catering")
public class CateringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catering_id")
    private int cateringId;
    @Column(name= "name")
    private String cateringName;
    @Column(name = "description")
    private String cateringDesc;
    @Column(name = "category")
    private String cateringCategory;
    @Column(name = "price")
    private double cateringPrice;
    @Column(name = "available_from")
    private Date cateringAvailableFromDate;
    @Column(name = "available_to")
    private Date cateringAvailableToDate;
    @Column(name = "quantity")
    private int cateringQuantity;
    @Column(name = "is_veg")
    private boolean cateringIsVeg;
    @Column(name = "is_active")
    private boolean cateringIsActive;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
