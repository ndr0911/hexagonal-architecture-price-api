package es.acompany.hexagonal.architecture.price.api.domain;

import es.acompany.hexagonal.architecture.price.api.domain.exception.ProductNotFoundException;
import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;
import es.acompany.hexagonal.architecture.price.api.domain.port.PricePersistentPort;
import es.acompany.hexagonal.architecture.price.api.domain.service.PriceServicePortImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PriceServicePortTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Mock
    private PricePersistentPort pricePersistentPort;

    @InjectMocks
    private PriceServicePortImpl priceService;

    @Test
    void testGetPrice_WhenMultiplePrices_ShouldReturnHighestPriority() {
        var applicationDate = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
        var price1 = new PriceDto(35455, 1, 1, 1,
                LocalDateTime.parse("2020-06-14-00.00.00", formatter),
                LocalDateTime.parse("2020-12-31-23.59.59", formatter),
                new BigDecimal("35.50"));
        var price2 = new PriceDto(35455, 1, 2, 2,
                LocalDateTime.parse("2020-06-14-15.00.00", formatter),
                LocalDateTime.parse("2020-06-14-18.30.00", formatter),
                new BigDecimal("25.45"));

        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(List.of(price1, price2));

        var result = priceService.getPrice(applicationDate, 1, 35455);

        assertEquals(2, result.getPriceList());
    }

    @Test
    void testGetPrice_WhenNoPriceAvailable_ShouldThrowException() {
        var applicationDate = LocalDateTime.parse("2020-06-14-16.00.00", formatter);

        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        var exception = assertThrows(ProductNotFoundException.class, () ->
                priceService.getPrice(applicationDate, 35455, 1));

        assertEquals("No price found for product 35455 and brand 1 at 2020-06-14T16:00", exception.getMessage());
    }

    @Test
    void testGetPrice_WhenDatabaseThrowsException_ShouldPropagateException() {
        var applicationDate = LocalDateTime.parse("2020-06-14-16.00.00", formatter);

        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () ->
                priceService.getPrice(applicationDate, 1, 35455));

        assertEquals("Database error", exception.getMessage());
    }
}
