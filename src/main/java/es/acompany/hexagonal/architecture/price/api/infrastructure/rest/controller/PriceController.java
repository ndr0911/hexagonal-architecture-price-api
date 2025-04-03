package es.acompany.hexagonal.architecture.price.api.infrastructure.rest.controller;

import es.acompany.hexagonal.architecture.price.api.domain.model.PriceResponse;
import es.acompany.hexagonal.architecture.price.api.domain.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@Tag(name = "Prices", description = "API para consultar precios de productos")
@Validated
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Retrieves the applicable price for a given product, brand, and application date.
     * <p>
     * This endpoint allows querying the price that should be applied based on the given parameters.
     * It requires an application date, a product ID, and a brand ID.
     *
     * @param applicationDate The date and time when the price should be applied (ISO 8601 format).
     * @param productId       The unique identifier of the product.
     * @param brandId         The unique identifier of the brand.
     * @return A {@link ResponseEntity} containing the {@link PriceResponse} with the applicable price details.
     * Returns a 200 OK status with the price information if found.
     */
    @GetMapping
    @Operation(summary = "Consulta los precios según la fecha, producto y marca")
    public ResponseEntity<PriceResponse> getPrice(
            @Parameter(description = "Fecha de aplicación (ISO 8601)", example = "0000-00-00T00:00:00")
            @RequestParam @NotNull LocalDateTime applicationDate,
            @Parameter(description = "ID del producto", example = "00000")
            @RequestParam @NotNull Integer productId,
            @Parameter(description = "ID de la marca", example = "0")
            @RequestParam @NotNull Integer brandId) {

        return ResponseEntity.ok(priceService.getPrice(applicationDate, productId, brandId));
    }
}
