package com.amalitech.ecommerce.dtos;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
