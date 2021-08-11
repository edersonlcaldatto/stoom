package com.qualification.stoom.service;

import com.qualification.stoom.Util.GeoCode;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address toSave){
        if(toSave.getLatitude() == null || toSave.getLongitude() == null){
            var geo = new GeoCode();
            try {
                var location = geo.getGeolocationFromAddress(toSave);
                toSave.setLongitude(location.get("lng"));
                toSave.setLatitude(location.get("lat"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return addressRepository.save(toSave);
    }

    public Address update(Address toUpdate){
        if(!addressRepository.existsById(toUpdate.getId())){
            throw new NoResultException(String.format("Addres no find with code %d",toUpdate.getId()));
        }
        return this.save(toUpdate);
    }

    public void deleteById(Long id){

        if(!addressRepository.existsById(id)){
            throw new NoResultException(String.format("Addres no find with code %d",id));
        }
        addressRepository.deleteById(id);
    }

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public Address getById(Long id){
        return addressRepository.findById(id)
                .orElseThrow( () -> new NoResultException(String.format("Addres no find with code %d", id)));
    }


}
