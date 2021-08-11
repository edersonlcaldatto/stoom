package com.qualification.stoom;

import com.qualification.stoom.Util.GeoCode;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoomApplicationTests {

    @Autowired
    private AddressRepository addressRepository;


    void contextLoads() {
    }


    void shouldSaveAddreess(){
        var addrees = new Address();
        addrees.setStreetName(" rua belo horizonte");
        addrees.setNumber("123");
        addrees.setComplement("");
        addrees.setNeighborhood("centro");
        addrees.setCity("Curitiba");
        addrees.setState("PR");
        addrees.setCountry("Brasil");
        addrees.setZipCode("85505-555");
        addrees.setLatitude(null);
        addrees.setLongitude(null);

        Integer countAdress = addressRepository.findAll().size();

        addressRepository.save(addrees);
        Integer countAdressAfterSave = addressRepository.findAll().size();

        assertEquals(countAdressAfterSave, countAdress+1);
    }

    void shouldDeleteAddreess(){

    }


    void shouldFindById(){

    }


    void shouldUpdateAddreess(){

    }

    @Test
    void testMap() throws IOException, InterruptedException {
        var geo = new GeoCode();
        var addrees = new Address();
        addrees.setStreetName(" rua belo horizonte");
        addrees.setNumber("123");
        addrees.setComplement("");
        addrees.setNeighborhood("centro");
        addrees.setCity("Curitiba");
        addrees.setState("PR");
        addrees.setCountry("Brasil");
        addrees.setZipCode("85505-555");
        addrees.setLatitude(null);
        addrees.setLongitude(null);
        geo.getGeolocationFromAddress(addrees);
    }

}
