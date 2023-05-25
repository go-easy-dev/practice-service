package go.easy.practiseservice.repository;

import go.easy.practiseservice.dto.practice.PracticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PracticeRepository extends MongoRepository<PracticeEntity, String> {
    List<PracticeEntity> findAllBySphereId(String sphere);

    @Query("{'sphereId': ?0, 'minScore': {$lte: ?1}, 'maxScore': {$gte: ?1}}")
    List<PracticeEntity> findAllBySphereAndScore(String sphereId, BigDecimal sphereScore);

}
