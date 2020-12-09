package my.denispavlov.robofinance.service;

import my.denispavlov.robofinance.domain.Address;
import my.denispavlov.robofinance.domain.Customer;
import my.denispavlov.robofinance.exception.CustomerAlreadyExistsException;
import my.denispavlov.robofinance.exception.CustomerNotFoundException;
import my.denispavlov.robofinance.repository.AddressRepository;
import my.denispavlov.robofinance.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;

    private CustomerService service;
    private Customer testedCustomer;
    private Address actualAddress;
    private Address registeredAddress;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new CustomerServiceImpl(customerRepository, addressRepository);

        actualAddress = new Address();
        actualAddress.setId(1L);
        registeredAddress = new Address();
        registeredAddress.setId(2L);

        testedCustomer = new Customer();
        testedCustomer.setFirstName("Ivan");
        testedCustomer.setLastName("Ivanov");
        testedCustomer.setActualAddress(actualAddress);
        testedCustomer.setRegisteredAddress(registeredAddress);
    }

    @Test(expected = CustomerAlreadyExistsException.class)
    public void createAlreadyExists(){
        when(customerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(testedCustomer.getFirstName(), testedCustomer.getLastName()))
                .thenReturn(Optional.of(testedCustomer));

        service.create(testedCustomer);
    }

    @Test
    public void creteSuccess(){
        when(addressRepository.findById(testedCustomer.getActualAddressId())).thenReturn(Optional.of(actualAddress));
        when(addressRepository.findById(testedCustomer.getRegisteredAddressId())).thenReturn(Optional.of(registeredAddress));
        when(customerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(any(),any())).thenReturn(Optional.empty());

        service.create(testedCustomer);

        verify(customerRepository, times(1))
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(testedCustomer.getFirstName(), testedCustomer.getLastName());
        verify(customerRepository, times(1))
                .save(testedCustomer);
    }

    @Test
    public void findByFirstNameAndLastNameSuccess() {
        when(customerRepository
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(testedCustomer.getFirstName(), testedCustomer.getLastName()))
                .thenReturn(Optional.of(testedCustomer));

        Customer customer = service.findByFirstNameAndLastName(testedCustomer.getFirstName(), testedCustomer.getLastName());

        assertEquals(testedCustomer.getFirstName(), customer.getFirstName());
        assertEquals(testedCustomer.getLastName(), customer.getLastName());
        verify(customerRepository, times(1))
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(testedCustomer.getFirstName(), testedCustomer.getLastName());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findByFirstNameAndLastNameNotFound(){
        when(customerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(testedCustomer.getFirstName(), testedCustomer.getLastName()))
                .thenReturn(Optional.empty());

        service.findByFirstNameAndLastName(testedCustomer.getFirstName(), testedCustomer.getLastName());
    }

    @Test
    public void getAll() {
        List<Customer> expectedCustomers = Collections.singletonList(testedCustomer);
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<Customer> resultCustomers = service.getAll();

        assertEquals(expectedCustomers, resultCustomers);
        verify(customerRepository, times(1)).findAll();
    }
}