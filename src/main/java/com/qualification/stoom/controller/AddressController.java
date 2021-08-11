package com.qualification.stoom.controller;

import com.qualification.stoom.model.Address;
import com.qualification.stoom.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<List<Address>> findAll(){
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Address> save(@RequestBody Address toSave){
        return ResponseEntity.ok(addressService.save(toSave));
    }

    public ResponseEntity<Address> update(@RequestBody Address toUpdate){
        return ResponseEntity.ok(addressService.update(toUpdate));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        addressService.deleteById(id);
    }


}
