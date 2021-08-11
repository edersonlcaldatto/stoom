package com.qualification.stoom.service;

import com.qualification.stoom.model.Address;

import java.util.List;

public interface AddreesService {

    Address create(Address toSave);

    Address update(Long id, Address toUpdate);

    void deleteById(Long id);

    List<Address> findAll();

    Address findById(Long id);

}
