package com.gentech.catering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CateringDto {
    private int cateringId;
    private String cateringName;
    private String cateringDesc;
    private String cateringCategory;
    private double cateringPrice;
    private Date cateringAvailableFromDate;
    private Date cateringAvailableToDate;
    private int cateringQuantity;
    private boolean cateringIsVeg;
    private boolean cateringIsActive;
    private Date createdAt;
    private Date updatedAt;
}
