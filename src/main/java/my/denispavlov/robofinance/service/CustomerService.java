package my.denispavlov.robofinance.service;

import my.denispavlov.robofinance.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    /**
     * Поиск по имени и фамилии.
     * @param name имя. Обязательный параметр, не может быть пустым.
     * @param lastName фамилия. Обязательный параметр, не может быть пустым.
     * @return клиен
     */
    Customer findByFirstNameAndLastName(String name, String lastName);

    List<Customer> getAll();
}
