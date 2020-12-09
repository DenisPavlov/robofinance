package my.denispavlov.robofinance.service;

import my.denispavlov.robofinance.domain.Address;

public interface AddressService {
    Address getById(Long id);
    Address update(Address address, Long id);
}