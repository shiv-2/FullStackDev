package com.gentech.catering.service;

import com.gentech.catering.dto.CateringDto;
import com.gentech.catering.entity.CateringEntity;

import java.util.List;

public interface CateringService {
    CateringDto addCateringItem(CateringDto cateringDto);
    List<CateringDto> getAllCatering();
    CateringDto getCateringById(int id);
    CateringDto updateCateringById(int id,CateringDto cateringDto);
    String deleteCateringById(int id);

    List<CateringDto> getCateringByName(String name);
    List<CateringDto> getAllCateringByCategory(String category);
    List<CateringDto> getCateringByPartialName(String partialName);
    List<CateringDto> getCateringIfItIsVeg(boolean value);
    List<CateringDto> getAllInActiveCatering(boolean active);

    List<CateringDto> getAllCateringBetweenPrices(double firstPrice,double secPrice);
    List<CateringDto> getAllCateringGreaterThanQuantity(int quantity);
    String deleteAllInactiveCatering();
    List<CateringDto> getAllCateringByPagination(int pageNumber,int pageSize);
    List<CateringDto> getAllCateringByPaginationAndSorting(int pageNumber,int pageSize,String columnName);
}
