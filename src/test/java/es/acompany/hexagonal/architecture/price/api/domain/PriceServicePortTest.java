package es.acompany.hexagonal.architecture.price.api.domain;

import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;
import es.acompany.hexagonal.architecture.price.api.domain.model.PriceResponse;
import es.acompany.hexagonal.architecture.price.api.domain.port.PricePersistentPort;
import es.acompany.hexagonal.architecture.price.api.domain.service.PriceServicePortImpl;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PriceServicePortTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Mock
    private PricePersistentPort pricePersistentPort;

    @InjectMocks
    private PriceServicePortImpl priceService;

    private static PriceDto price1, price2, price3, price4;

    @BeforeAll
    public static void setup() {
        price1 = new PriceDto(35455, 1, 1, 1,
                LocalDateTime.parse("2020-06-14-00.00.00", formatter),
                LocalDateTime.parse("2020-12-31-23.59.59", formatter),
                new BigDecimal("35.50"));

        price2 = new PriceDto(35455, 1, 2, 1,
                LocalDateTime.parse("2020-06-14-15.00.00", formatter),
                LocalDateTime.parse("2020-06-14-18.30.00", formatter),
                new BigDecimal("25.45"));

        price3 = new PriceDto(35455, 1, 3, 1,
                LocalDateTime.parse("2020-06-15-00.00.00", formatter),
                LocalDateTime.parse("2020-06-15-11.00.00", formatter),
                new BigDecimal("30.50"));

        price4 = new PriceDto(35455, 1, 4, 1,
                LocalDateTime.parse("2020-06-15-16.00.00", formatter),
                LocalDateTime.parse("2020-12-31-23.59.59", formatter),
                new BigDecimal("38.95"));
    }

    @Test
    public void test01__2020_06_14_1000_brandId_1_productId_35455() {
        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(price1));

        PriceResponse result = priceService.getPrice(
                LocalDateTime.parse("2020-06-14-10.00.00", formatter), 1, 35455);

        assertEquals(1, result.getPriceList());
    }

    @Test
    public void test02__2020_06_14_1600_brandId_1_productId_35455() {
        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(price2));

        PriceResponse result = priceService.getPrice(
                LocalDateTime.parse("2020-06-14-16.00.00", formatter), 1, 35455);

        assertEquals(2, result.getPriceList());
    }

    @Test
    public void test03__2020_06_14_2100_brandId_1_productId_35455() {
        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(price1));

        PriceResponse result = priceService.getPrice(
                LocalDateTime.parse("2020-06-14-21.00.00", formatter), 1, 35455);

        assertEquals(1, result.getPriceList());
    }

    @Test
    public void test04__2020_06_15_1000_brandId_1_productId_35455() {
        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(price3));

        PriceResponse result = priceService.getPrice(
                LocalDateTime.parse("2020-06-15-10.00.00", formatter), 1, 35455);

        assertEquals(3, result.getPriceList());
    }

    @Test
    public void test05__2020_06_16_2100_brandId_1_productId_35455() {
        Mockito.when(pricePersistentPort.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                        Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(price4));

        PriceResponse result = priceService.getPrice(
                LocalDateTime.parse("2020-06-16-21.00.00", formatter), 1, 35455);

        assertEquals(4, result.getPriceList());
    }
}
