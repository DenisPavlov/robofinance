package my.denispavlov.robofinance.controller;

import lombok.AllArgsConstructor;
import my.denispavlov.robofinance.domain.Customer;
import my.denispavlov.robofinance.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping()
    public Customer getByNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return customerService.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping()
    public Customer create(@Valid @RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return customerService.getAll();
    }
}