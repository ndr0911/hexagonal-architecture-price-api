package es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.repository;

import es.acompany.hexagonal.architecture.price.api.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryJPA extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
}
