package com.amalitech.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer id,
        @Positive(message = "Quantity should be specified")
        double quantity
) {
}
