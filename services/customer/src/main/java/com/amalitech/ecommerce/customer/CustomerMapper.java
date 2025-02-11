package com.amalitech.ecommerce.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(@Valid CustomerRequest customer) {
        if (customer == null) {
            return null;
        }

        return Customer.builder()
                .id(customer.id())
                .firstname(customer.firstname())
                .lastname(customer.lastname())
                .email(customer.email())
                .address(customer.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
