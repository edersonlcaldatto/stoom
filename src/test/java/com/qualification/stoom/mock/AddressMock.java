package com.qualification.stoom.mock;

import com.qualification.stoom.model.Address;

import java.math.BigDecimal;

public class AddressMock {

    public static Address getOne(){
        Address address = new Address();
        address.setStreetName("Rua belo horizonte");
        address.setNumber("123");
        address.setComplement(null);
        address.setNeighborhood("centro");
        address.setCity("Curitiba");
        address.setState("PR");
        address.setCountry("Brasil");
        address.setZipCode("85505-555");
        address.setLatitude(new BigDecimal(26.214258));
        address.setLongitude(new BigDecimal(-52.666897));
        return address;
    }

}
