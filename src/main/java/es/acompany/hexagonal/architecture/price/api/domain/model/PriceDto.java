package es.acompany.hexagonal.architecture.price.api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDto implements Serializable {
    
    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private Integer priority;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss", timezone = "Europe/Madrid")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss", timezone = "Europe/Madrid")
    private LocalDateTime endDate;
    private BigDecimal price;
    
}
