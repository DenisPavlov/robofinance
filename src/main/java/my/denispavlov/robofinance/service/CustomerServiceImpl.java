package my.denispavlov.robofinance.service;

import lombok.AllArgsConstructor;
import my.denispavlov.robofinance.domain.Address;
import my.denispavlov.robofinance.domain.Customer;
import my.denispavlov.robofinance.exception.CustomerAlreadyExistsException;
import my.denispavlov.robofinance.exception.CustomerNotFoundException;
import my.denispavlov.robofinance.repository.AddressRepository;
import my.denispavlov.robofinance.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    @Override
    public Customer create(Customer customer) {
        Optional<Customer> existedItem =
                customerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(customer.getFirstName(), customer.getLastName());
        if (existedItem.isPresent()) throw new CustomerAlreadyExistsException();

        Optional<Address> optActualAddress = addressRepository.findById(customer.getActualAddressId());
        if (optActualAddress.isPresent()) {
            customer.setActualAddress(optActualAddress.get());
        } else {
            Address address = new Address();
            customer.setActualAddress(addressRepository.save(address));
        }

        Optional<Address> optRegisteredAddress = addressRepository.findById(customer.getRegisteredAddressId());
        if (optRegisteredAddress.isPresent()) {
            customer.setRegisteredAddress(optRegisteredAddress.get());
        } else {
            Address address = new Address();
            customer.setRegisteredAddress(addressRepository.save(address));
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
