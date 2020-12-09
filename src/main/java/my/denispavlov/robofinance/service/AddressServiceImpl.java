package my.denispavlov.robofinance.service;

import lombok.AllArgsConstructor;
import my.denispavlov.robofinance.domain.Address;
import my.denispavlov.robofinance.exception.AddressNotFoundException;
import my.denispavlov.robofinance.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    @Override
    public Address getById(Long id) {
        return repository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

    @Override
    public Address update(Address address, Long id) {
        getById(id);
        address.setId(id);
        address.setModified(new Date());
        return repository.save(address);
    }
}