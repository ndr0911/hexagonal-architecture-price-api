package es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.mapper;


import es.acompany.hexagonal.architecture.price.api.domain.model.PriceDto;
import es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.entity.PriceEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriceMapper {

    public static PriceDto toDomain(PriceEntity priceEntity){
        if(priceEntity == null){
            return null;
        }
        PriceDto.PriceDtoBuilder price = PriceDto.builder();
        price.brandId(priceEntity.getBrandId());
        price.startDate(priceEntity.getStartDate());
        price.endDate(priceEntity.getEndDate());
        price.priority(priceEntity.getPriority());
        price.priceList(priceEntity.getPriceList());
        price.productId(priceEntity.getProductId());
        price.price(priceEntity.getPrice());

        return price.build();
    }
}
