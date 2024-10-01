package com.gentech.catering.serviceimpl;

import com.gentech.catering.dto.CateringDto;
import com.gentech.catering.entity.CateringEntity;
import com.gentech.catering.exception.ResourceNotFoundException;
import com.gentech.catering.mapper.CateringMapper;
import com.gentech.catering.repository.CateringRepository;
import com.gentech.catering.service.CateringService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CateringServiceImpl implements CateringService {
    @Autowired
    private CateringRepository cateringRepository;

    @Override
    public CateringDto addCateringItem(CateringDto cateringDto) {
        CateringEntity cateringEntity=CateringMapper.mapResDtoToRes(cateringDto);
        cateringRepository.save(cateringEntity);
        cateringDto=CateringMapper.mapResToResDto(cateringEntity);
        return cateringDto;
    }

    @Override
    public List<CateringDto> getAllCatering() {
        return cateringRepository.findAll()
                .stream().map((cateringEntity) ->CateringMapper.mapResToResDto(cateringEntity)).collect(Collectors.toList());
    }

    @Override
    public CateringDto getCateringById(int id) {
        CateringEntity cateringEntity=cateringRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Catering","Id",id));
        CateringDto cateringDto=CateringMapper.mapResToResDto(cateringEntity);
        return cateringDto;
    }

    @Override
    public CateringDto updateCateringById(int id, CateringDto cateringDto) {
        CateringEntity cateringEntity = cateringRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Catering","Id",id));
        cateringEntity.setCateringName(cateringDto.getCateringName());
        cateringEntity.setCateringDesc(cateringDto.getCateringDesc());
        cateringEntity.setCateringCategory(cateringDto.getCateringCategory());
        cateringEntity.setCateringPrice(cateringDto.getCateringPrice());
        cateringEntity.setCateringAvailableFromDate(cateringDto.getCateringAvailableFromDate());
        cateringEntity.setCateringAvailableToDate(cateringDto.getCateringAvailableToDate());
        cateringEntity.setCateringQuantity(cateringDto.getCateringQuantity());
        cateringEntity.setCateringIsVeg(cateringDto.isCateringIsVeg());
        cateringEntity.setCateringIsActive(cateringDto.isCateringIsActive());

        cateringRepository.save(cateringEntity);
        return CateringMapper.mapResToResDto(cateringEntity);
    }

    @Override
    public String deleteCateringById(int id) {
        CateringEntity cateringEntity=cateringRepository.findById(id).orElse(null);
        if(cateringEntity!=null){
            cateringRepository.deleteById(id);
            return "The Given Id : "+id+" successfully deleted in the database";
        }
        return "The Given Id : "+id+" is not found in the database";
    }

    @Override
    public List<CateringDto> getCateringByName(String name) {
        List<CateringEntity> cateringEntities = cateringRepository.findByCateringName(name);
        if(!cateringEntities.isEmpty()){
            return cateringEntities.stream().map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity))).collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Catering","CateringName",name);
    }

    @Override
    public List<CateringDto> getAllCateringByCategory(String category) {
        List<CateringEntity> cateringEntityList=cateringRepository.findByCateringCategory(category);
        if(!cateringEntityList.isEmpty()){
            return cateringEntityList.stream().map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity))).collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Catering","Category",category);
    }

    @Override
    public List<CateringDto> getCateringByPartialName(String partialName) {
        List<CateringEntity> cateringEntityList=cateringRepository.findByCateringNameContaining(partialName);
        if(!cateringEntityList.isEmpty()){
            return cateringEntityList.stream().map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity))).collect(Collectors.toList());
        }
        throw new ResourceNotFoundException("Catering","PartialName",partialName);
    }

    @Override
    public List<CateringDto> getCateringIfItIsVeg(boolean value) {
        return cateringRepository.findByCateringIsVeg(value).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CateringDto> getAllInActiveCatering(boolean active) {
        return cateringRepository.findByCateringIsActive(active).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CateringDto> getAllCateringBetweenPrices(double firstPrice, double secPrice) {
        return cateringRepository.getAllCateringBetweenPrices(firstPrice,secPrice).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CateringDto> getAllCateringGreaterThanQuantity(int quantity) {
        return cateringRepository.getAllCateringGreaterThanQuantity(quantity).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteAllInactiveCatering() {
        cateringRepository.deleteAllInactiveCatering();
        return "All Inactive caterings are successfully deleted in the database";
    }

    @Override
    public List<CateringDto> getAllCateringByPagination(int pageNumber, int pageSize) {
        Pageable pages= PageRequest.of(pageNumber,pageSize);
        return cateringRepository.findAll(pages).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CateringDto> getAllCateringByPaginationAndSorting(int pageNumber, int pageSize, String columnName) {
        Sort sort= Sort.by(Sort.Direction.ASC,columnName);
        Pageable pages= PageRequest.of(pageNumber,pageSize,sort);
        return cateringRepository.findAll(pages).stream()
                .map((cateringEntity)->(CateringMapper.mapResToResDto(cateringEntity)))
                .collect(Collectors.toList());
    }
}
