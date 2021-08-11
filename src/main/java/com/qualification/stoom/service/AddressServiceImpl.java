package com.qualification.stoom.service;

import com.qualification.stoom.Util.GeoCode;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.List;

@Service
public class AddressServiceImpl implements AddreesService {

    private final AddressRepository addressRepository;
    private final GeoCode geoCode;

    public AddressServiceImpl(AddressRepository addressRepository, GeoCode geoCode) {
        this.addressRepository = addressRepository;
        this.geoCode = geoCode;
    }

    @Override
    public Address create(Address toSave){
        if(toSave.getLatitude() == null || toSave.getLongitude() == null){
            geoCode.findAndGeolocationFromAddress(toSave);
        }
        return addressRepository.save(toSave);
    }

    @Override
    public Address update(Long id, Address toUpdate){
        if(!addressRepository.existsById(id)){
            throw new NoResultException(String.format("Addres no find with code %d",toUpdate.getId()));
        }
        return this.create(toUpdate);
    }

    @Override
    public void deleteById(Long id){

        if(!addressRepository.existsById(id)){
            throw new NoResultException(String.format("Addres no find with code %d",id));
        }
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id){
        return addressRepository.findById(id)
                .orElseThrow( () -> new NoResultException(String.format("Addres no find with code %d", id)));
    }


}
