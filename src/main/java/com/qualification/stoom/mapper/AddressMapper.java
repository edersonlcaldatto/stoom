package com.qualification.stoom.mapper;

import com.qualification.stoom.dto.AddressDto;
import com.qualification.stoom.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressDto dto){
        Address address = new Address();
        address.setId(dto.getId());
        address.setStreetName(dto.getStreetName());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        address.setNeighborhood(dto.getNeighborhood());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setZipCode(dto.getZipCode());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());

        return address;
    }

    public AddressDto fromEntity(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreetName(address.getStreetName());
        addressDto.setNumber(address.getNumber());
        addressDto.setComplement(address.getComplement());
        addressDto.setNeighborhood(address.getNeighborhood());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setLatitude(address.getLatitude());
        addressDto.setLongitude(address.getLongitude());

        return addressDto;
    }

}
