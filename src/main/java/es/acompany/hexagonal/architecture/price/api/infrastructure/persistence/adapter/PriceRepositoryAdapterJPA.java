package es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.adapter;


import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;
import es.acompany.hexagonal.architecture.price.api.domain.port.PricePersistentPort;
import es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.mapper.PriceMapper;
import es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.repository.PriceRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapterJPA implements PricePersistentPort {

    private final PriceRepositoryJPA priceRepositoryJPA;

    /**
     * Retrieves a list of prices for a given product and brand that are valid within a specific date range.
     *
     * @param productId The ID of the product to search for.
     * @param brandId   The ID of the brand associated with the product.
     * @param startDate The start of the date range (prices must have started before this date).
     * @param endDate   The end of the date range (prices must be valid after this date).
     * @return A list of {@link PriceDto} objects that match the given criteria.
     */
    @Override
    public List<PriceDto> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate) {

        // Retrieve all price entities matching the search criteria from the repository
        var priceEntities =
                this.priceRepositoryJPA.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(productId, brandId, startDate, endDate);

        // Convert the retrieved PriceEntity objects into PriceDto objects using the mapper
        return priceEntities.stream().map(PriceMapper::toDomain).collect(Collectors.toList());
    }
}
