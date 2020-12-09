package my.denispavlov.robofinance.controller;

import lombok.AllArgsConstructor;
import my.denispavlov.robofinance.domain.Address;
import my.denispavlov.robofinance.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController()
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressService.getById(id);
    }

    @PutMapping("/{id}")
    public Address update(@Valid @RequestBody Address address, @PathVariable Long id) {
        return addressService.update(address, id);
    }
}