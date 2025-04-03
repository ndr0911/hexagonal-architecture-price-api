package es.acompany.hexagonal.architecture.price.api.domain.port;


import es.acompany.hexagonal.architecture.price.api.domain.model.PriceResponse;

import java.time.LocalDateTime;

public interface PriceServicePort {

    PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
