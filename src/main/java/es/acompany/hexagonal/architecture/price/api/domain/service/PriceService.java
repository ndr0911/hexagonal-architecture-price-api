package es.acompany.hexagonal.architecture.price.api.domain.service;


import es.acompany.hexagonal.architecture.price.api.domain.model.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {

    PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
