package es.acompany.hexagonal.architecture.price.api.domain.service;

import es.acompany.hexagonal.architecture.price.api.domain.exception.PriceNotFoundException;
import es.acompany.hexagonal.architecture.price.api.domain.exception.ProductNotFoundException;
import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;
import es.acompany.hexagonal.architecture.price.api.domain.model.PriceResponse;
import es.acompany.hexagonal.architecture.price.api.domain.port.PricePersistentPort;
import es.acompany.hexagonal.architecture.price.api.domain.port.PriceServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceServicePortImpl implements PriceServicePort {

    private final PricePersistentPort pricePersistentPort;

    @Autowired
    public PriceServicePortImpl(PricePersistentPort pricePersistentPort) {
        this.pricePersistentPort = pricePersistentPort;
    }

    /**
     * Retrieves the most relevant price for a given product and brand at a specific date and time.
     * If multiple prices are found, it selects the one with the highest priority.
     *
     * @param applicationDate The date and time when the price should be applied.
     * @param productId       The ID of the product to check.
     * @param brandId         The ID of the brand associated with the product.
     * @return A {@link PriceResponse} containing the price details.
     * @throws PriceNotFoundException if no price is found for the given criteria.
     * @throws ProductNotFoundException If no price is found for the given parameters.
     */
    @Override
    public PriceResponse getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {

        // Fetch all prices that match the given product, brand, and date range
        List<PriceDto> prices = pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, applicationDate, applicationDate);

        // If no prices are found for the given parameters, throw an exception
        if (prices.isEmpty()) {
            throw new ProductNotFoundException("No price found for product " + productId + " and brand " + brandId + " at " + applicationDate);
        }

        // Select the price with the highest priority or throw an exception if no price is found
        return prices.stream()
                .max(Comparator.comparingInt(PriceDto::getPriority))
                .map(price -> new PriceResponse(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()
                ))
                .orElseThrow(() -> new PriceNotFoundException("The price of the product was not found on the requested date " + applicationDate));
    }

}