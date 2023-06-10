package com.ajayCodes.calebFyp.Controller;

import com.ajayCodes.calebFyp.Config.AppConstants;
import com.ajayCodes.calebFyp.DTO.DataDto;
import com.ajayCodes.calebFyp.DTO.Response.ApiResponse;
import com.ajayCodes.calebFyp.Entity.Data;
import com.ajayCodes.calebFyp.Exceptions.BadRequestException;
import com.ajayCodes.calebFyp.Repository.DataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/data")
public class appController {
    @Autowired
    DataRepository dataRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> createDataEntry(@RequestBody DataDto dataDto){
        Data data = modelMapper.map(dataDto, Data.class);
        ZoneId gmtPlus1 = ZoneId.of("GMT+1");
        ZonedDateTime currentDateTime = ZonedDateTime.now(gmtPlus1);
        // Format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        data.setDateTime(formattedDateTime);
        dataRepository.save(data);
        return ResponseEntity.ok(new ApiResponse(true,"Data entry successful" , data));

    }
    @GetMapping("/")
    public ResponseEntity<?> getAllDataEntries(@RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                               @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Data> data = dataRepository.getAllDataEntries(pageable);
        return ResponseEntity.ok(new ApiResponse(true,"All entries" , data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDataById(@PathVariable Long id){
        Data data = dataRepository.findDataByDataId(id);
        return ResponseEntity.ok(new ApiResponse(true,"Data with data Id: " + id , data));
    }




    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }


    }
}
