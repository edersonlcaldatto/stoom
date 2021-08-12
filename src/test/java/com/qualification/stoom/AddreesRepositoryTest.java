package com.qualification.stoom;

import com.qualification.stoom.mock.AddressMock;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AddreesRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private Address addressTest;

    @BeforeEach
    public void setUp(){
        this.addressTest = AddressMock.getOne();
        addressRepository.deleteAll();
    }

    @Test
    public void shouldCreateAddrees(){
        this.addressTest = this.addressRepository.save(addressTest);
        assertNotNull(addressTest.getId());
    }

    @Test
    public void validNotNullFieldsWhenCreate(){
        try{
            addressTest.setStreetName(null);
            this.addressRepository.save(addressTest);
            assertTrue(false);
        }catch(Exception expected){
            expected.printStackTrace();
            assertTrue(true);
        }        
    }

    @Test
    public void shouldDeleteAddress(){
        this.addressTest = this.addressRepository.save(addressTest);
        addressRepository.deleteById(addressTest.getId());

        var count = addressRepository.findAll().size();
        assertEquals(0, count);
    }

    @Test
    public void shouldUpdateAddress(){
        this.addressTest = addressRepository.save(addressTest);

        this.addressTest.setStreetName("Different streetName");
        this.addressTest = addressRepository.save(addressTest);

        Optional<Address> byId = addressRepository.findById(addressTest.getId());
        assertTrue(byId.isPresent());
        assertEquals("Different streetName", byId.get().getStreetName());

    }

    @Test
    public void shouldFindAddressById(){
        this.addressTest = addressRepository.save(addressTest);
        Optional<Address> byId = addressRepository.findById(addressTest.getId());
        assertTrue(byId.isPresent());
        assertEquals(addressTest.getId(), byId.get().getId());
    }


}
