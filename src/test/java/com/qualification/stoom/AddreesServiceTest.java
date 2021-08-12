package com.qualification.stoom;

import com.qualification.stoom.mock.AddressMock;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import com.qualification.stoom.service.AddreesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddreesServiceTest {

    @Autowired
    private AddreesService addreesService;

    @Autowired
    private AddressRepository addressRepository;

    private Address addressTest;

    @BeforeEach
    public void setUp(){
        this.addressTest = AddressMock.getOne();
        addressRepository.deleteAll();
    }

    @Test
    public void shouldCreateAddreesAndFindGeoByAddress(){
        addressTest.setLatitude(null);
        addressTest.setLongitude(null);

        this.addressTest = this.addreesService.create(addressTest);

        assertNotNull(addressTest.getId());
        assertNotNull(addressTest.getLongitude());
        assertNotNull(addressTest.getLatitude());
    }




}
