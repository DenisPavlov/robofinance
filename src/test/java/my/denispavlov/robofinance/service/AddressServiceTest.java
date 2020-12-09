package my.denispavlov.robofinance.service;

import my.denispavlov.robofinance.domain.Address;
import my.denispavlov.robofinance.exception.AddressNotFoundException;
import my.denispavlov.robofinance.repository.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    private AddressService service;
    private Address testedAddress;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new AddressServiceImpl(addressRepository);

        testedAddress = new Address();
        testedAddress.setId(1L);
        testedAddress.setCountry("Russia");
    }

    @Test
    public void getByIdSuccess() {
        when(addressRepository.findById(testedAddress.getId())).thenReturn(Optional.of(testedAddress));

        Address resultAddress = service.getById(testedAddress.getId());
        assertEquals(testedAddress, resultAddress);
        verify(addressRepository, times(1)).findById(testedAddress.getId());
    }

    @Test(expected = AddressNotFoundException.class)
    public void getByIdNotFound() {
        when(addressRepository.findById(testedAddress.getId())).thenReturn(Optional.empty());
        service.getById(testedAddress.getId());
    }

    @Test(expected = AddressNotFoundException.class)
    public void updateNotFoundException() {
        when(addressRepository.findById(testedAddress.getId())).thenReturn(Optional.empty());
        service.update(testedAddress, testedAddress.getId());
    }

    @Test
    public void updateSuccess() {
        Address dbAddress = new Address();
        dbAddress.setId(testedAddress.getId());
        dbAddress.setCountry("USA");
        when(addressRepository.findById(testedAddress.getId())).thenReturn(Optional.of(dbAddress));
        when(addressRepository.save(testedAddress)).thenReturn(testedAddress);

        Address updated = service.update(testedAddress, testedAddress.getId());
        assertNotEquals(dbAddress.getCountry(), updated.getCountry());
    }
}