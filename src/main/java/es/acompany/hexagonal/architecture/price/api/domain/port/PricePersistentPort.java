package es.acompany.hexagonal.architecture.price.api.domain.port;

import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistentPort {

    List<PriceDto> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
}