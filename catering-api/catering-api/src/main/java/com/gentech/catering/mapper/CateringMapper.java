package com.gentech.catering.mapper;

import com.gentech.catering.dto.CateringDto;
import com.gentech.catering.entity.CateringEntity;

public class CateringMapper {
    public static CateringDto mapResToResDto(CateringEntity cateringEntity){
        CateringDto cateringDto=new CateringDto(
                cateringEntity.getCateringId(),
                cateringEntity.getCateringName(),
                cateringEntity.getCateringDesc(),
                cateringEntity.getCateringCategory(),
                cateringEntity.getCateringPrice(),
                cateringEntity.getCateringAvailableFromDate(),
                cateringEntity.getCateringAvailableToDate(),
                cateringEntity.getCateringQuantity(),
                cateringEntity.isCateringIsVeg(),
                cateringEntity.isCateringIsActive(),
                cateringEntity.getCreatedAt(),
                cateringEntity.getUpdatedAt()
        );
        return cateringDto;
    }
    public static CateringEntity mapResDtoToRes(CateringDto cateringDto){
        CateringEntity cateringEntity=new CateringEntity(
                cateringDto.getCateringId(),
                cateringDto.getCateringName(),
                cateringDto.getCateringDesc(),
                cateringDto.getCateringCategory(),
                cateringDto.getCateringPrice(),
                cateringDto.getCateringAvailableFromDate(),
                cateringDto.getCateringAvailableToDate(),
                cateringDto.getCateringQuantity(),
                cateringDto.isCateringIsVeg(),
                cateringDto.isCateringIsActive(),
                cateringDto.getCreatedAt(),
                cateringDto.getUpdatedAt()
        );
        return cateringEntity;
    }
}
