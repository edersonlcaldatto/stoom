package com.qualification.stoom.api;

import com.qualification.stoom.dto.AddressDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Address Controller")
public interface AddressController {

    @GetMapping()
    @ApiOperation(value = "Retrive list of all address")
    ResponseEntity<List<AddressDto>> findAll();

    @GetMapping("/{id}")
    @ApiOperation(value = "Find address by ID")
    ResponseEntity<AddressDto> findById(@PathVariable("id") Long id);

    @PostMapping()
    @ApiOperation(value = "Create a new address")
    ResponseEntity<AddressDto> create(@RequestBody @Valid AddressDto toSave);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update address by ID")
    ResponseEntity<AddressDto> update(@PathVariable("id") Long id, @RequestBody @Valid AddressDto toUpdate);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete addrees by ID")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);

}
