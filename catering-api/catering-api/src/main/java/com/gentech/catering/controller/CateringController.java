package com.gentech.catering.controller;

import com.gentech.catering.dto.CateringDto;
import com.gentech.catering.service.CateringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CateringController {
    @Autowired
    private CateringService cateringService;
    @PostMapping("/addCatering")
    ResponseEntity<CateringDto> addCateringItem(@RequestBody CateringDto cateringDto){
        return new ResponseEntity<CateringDto>(cateringService.addCateringItem(cateringDto), HttpStatusCode.valueOf(201));
    }
    @GetMapping("/getAll")
    ResponseEntity<List<CateringDto>> getAllCateringItems(){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCatering(),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringById/{id}")
    ResponseEntity<CateringDto> getCateringByCateringId(@PathVariable int id){
        return new ResponseEntity<CateringDto>(cateringService.getCateringById(id),HttpStatusCode.valueOf(200));
    }
    @PutMapping("/updateCatering")
    ResponseEntity<CateringDto> updateCateringItemByCateringId(@RequestParam int id,@RequestBody CateringDto cateringDto){
        return new ResponseEntity<CateringDto>(cateringService.updateCateringById(id,cateringDto),HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/deleteCateringById")
    ResponseEntity<String> deleteCateringByCateringId(@RequestParam int id){
        return new ResponseEntity<String>(cateringService.deleteCateringById(id),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringByName")
    ResponseEntity<List<CateringDto>> getCateringByCateringName(@RequestParam String name){
        return new ResponseEntity<List<CateringDto>>(cateringService.getCateringByName(name),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getAllCateringByCategory")
    ResponseEntity<List<CateringDto>> getAllCateringByCateringCategory(@RequestParam String category){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCateringByCategory(category),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringContainingName")
    ResponseEntity<List<CateringDto>> getCateringContainingPartialName(@RequestParam String partialName){
        return new ResponseEntity<List<CateringDto>>(cateringService.getCateringByPartialName(partialName),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getVegCatering")
    ResponseEntity<List<CateringDto>> getAllVegCatering(@RequestParam boolean value){
        return new ResponseEntity<List<CateringDto>>(cateringService.getCateringIfItIsVeg(value),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getActiveOrInActiveCatering")
    ResponseEntity<List<CateringDto>> getAllCateringWhichIsActiveOrInActive(@RequestParam boolean active){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllInActiveCatering(active),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringBetweenPrices")
    ResponseEntity<List<CateringDto>> getAllCateringBetweenCateringPrices(@RequestParam double firstPrice, @RequestParam double secPrice){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCateringBetweenPrices(firstPrice,secPrice),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringGreaterThanQuantity")
    ResponseEntity<List<CateringDto>> getAllCateringGreaterThanCateringQuantity(@RequestParam int quantity){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCateringGreaterThanQuantity(quantity),HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/deleteAllInactiveCatering")
    ResponseEntity<String> deleteAllCateringWhichIsNotActive(){
        return new ResponseEntity<String>(cateringService.deleteAllInactiveCatering(),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringByPages")
    ResponseEntity<List<CateringDto>> getAllCateringByPagination(@RequestParam int pageNumber, @RequestParam int pageSize){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCateringByPagination(pageNumber,pageSize),HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getCateringByPagesAndSorting")
    ResponseEntity<List<CateringDto>> getAllCateringByPaginationAndSorting(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String columnName){
        return new ResponseEntity<List<CateringDto>>(cateringService.getAllCateringByPaginationAndSorting(pageNumber,pageSize,columnName),HttpStatusCode.valueOf(200));
    }
}
