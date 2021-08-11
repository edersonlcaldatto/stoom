package com.qualification.stoom;

import com.qualification.stoom.Util.GeoCode;
import com.qualification.stoom.model.Address;
import com.qualification.stoom.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AddreesRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void shouldCreateAddrees(){
        Address addrees = new Address();
        addrees.setStreetName("Rua belo horizonte");
        addrees.setNumber("123");
        addrees.setComplement(null);
        addrees.setNeighborhood("centro");
        addrees.setCity("Curitiba");
        addrees.setState("PR");
        addrees.setCountry("Brasil");
        addrees.setZipCode("85505-555");
        addrees.setLatitude(new BigDecimal(26.214258));
        addrees.setLongitude(new BigDecimal(-52.666897));

        this.addressRepository.save(addrees);

        assertThat(addrees.getId()).isNotNull();
        assertThat(addrees.getStreetName()).isEqualTo("Rua belo horizonte");
        assertThat(addrees.getZipCode()).isEqualTo("85505-555");
    }

    @Test
    public void shouldCreateAddreesAndFindGeoByAddress(){
        Address addrees = new Address();
        addrees.setStreetName("Rua belo horizonte");
        addrees.setNumber("123");
        addrees.setComplement(null);
        addrees.setNeighborhood("centro");
        addrees.setCity("Curitiba");
        addrees.setState("PR");
        addrees.setCountry("Brasil");
        addrees.setZipCode("85505-555");
        addrees.setLatitude(null);
        addrees.setLongitude(null);

        this.addressRepository.save(addrees);

        assertThat(addrees.getId()).isNotNull();
        assertThat(addrees.getLongitude()).isNotNull();
        assertThat(addrees.getLatitude()).isNotNull();
    }

    @Test
    public void validNotNullFieldsWhenCreate(){
        Address addrees = new Address();
        addrees.setNumber("123");
        addrees.setComplement(null);
        addrees.setNeighborhood("centro");
        addrees.setCity("Curitiba");
        addrees.setState("PR");
        addrees.setCountry("Brasil");
        addrees.setZipCode("85505-555");
        addrees.setLatitude(null);
        addrees.setLongitude(null);

        this.addressRepository.save(addrees);

        assertThat(addrees.getId()).isNotNull();
        assertThat(addrees.getLongitude()).isNotNull();
        assertThat(addrees.getLatitude()).isNotNull();
    }

    public void shouldDeleteAddress(){

    }

    public void shouldUpdateAddress(){

    }

    public void shouldGetAddress(){

    }

    public void shouldGetLocationByAddress(){

    }



}
