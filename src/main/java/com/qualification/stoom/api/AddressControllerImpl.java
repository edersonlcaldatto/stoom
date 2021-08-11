package com.qualification.stoom.api;

import com.qualification.stoom.dto.AddressDto;
import com.qualification.stoom.mapper.AddressMapper;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.service.AddressServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/address")
public class AddressControllerImpl implements AddressController {

    private final AddressServiceImpl addressServiceImpl;
    private final AddressMapper addreesMapper;

    public AddressControllerImpl(AddressServiceImpl addressServiceImpl, AddressMapper addreesMapper) {
        this.addressServiceImpl = addressServiceImpl;
        this.addreesMapper = addreesMapper;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(addressServiceImpl.findAll()
                .stream()
                .map(addreesMapper::fromEntity)
                .collect(Collectors.toList())
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(addreesMapper.fromEntity(addressServiceImpl.findById(id)));
    }

    @Override
    @PostMapping()
    public ResponseEntity<AddressDto> create(@RequestBody @Valid AddressDto toSave){
        return ResponseEntity.ok(addreesMapper.fromEntity(addressServiceImpl.create(addreesMapper.toEntity(toSave))));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable("id") Long id, @RequestBody @Valid AddressDto toUpdate){
        return ResponseEntity.ok(addreesMapper.fromEntity(addressServiceImpl.update(id, addreesMapper.toEntity(toUpdate))));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        addressServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
