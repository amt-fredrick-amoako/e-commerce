package com.amalitech.ecommerce.exceptionHandlers;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
