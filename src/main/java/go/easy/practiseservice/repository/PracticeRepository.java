package go.easy.practiseservice.repository;

import go.easy.practiseservice.dto.practice.PracticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PracticeRepository extends MongoRepository<PracticeEntity, String> {
    List<PracticeEntity> findAllBySphereId(String sphere);

    List<PracticeEntity> findAllBySphereIdAndMinScoreGreaterThan(String sphereId, BigDecimal minScore);

}
