package go.easy.practiseservice.repository;

import go.easy.practiseservice.dto.practice.PracticeEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.math.BigDecimal;

@DataMongoTest
class PracticeRepositoryTest {
    @Autowired
    private PracticeRepository practiceRepository;

    @BeforeEach
    void init() {
        practiceRepository.save(PracticeEntity.builder()
                .id("Practice_ID_1")
                .version(1)
                .sphereId("SPHERE_1")
                .minScore(BigDecimal.valueOf(5))
                .build());

        practiceRepository.save(PracticeEntity.builder()
                .id("Practice_ID_2")
                .version(1)
                .sphereId("SPHERE_2")
                .minScore(BigDecimal.valueOf(8))
                .build());
    }

    @AfterEach
    void cleanUp() {
        practiceRepository.deleteAll();
    }

    @Test
    void should_find_by_id() {
        // when
        var actual = practiceRepository.findById("Practice_ID_1");

        // then
        Assertions.assertThat(actual)
                .isPresent();
    }

    @Test
    void should_find_by_sphere() {
        // when
        var actual = practiceRepository.findAllBySphereId("SPHERE_1");

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getId().equals("Practice_ID_1"))
                .hasSize(1);
    }

    @Test
    void should_find_by_sphere_and_score() {
        // when
        var actual = practiceRepository.findAllBySphereIdAndMinScoreGreaterThan("SPHERE_2", BigDecimal.valueOf(6));

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getId().equals("Practice_ID_2"))
                .filteredOn(elt -> elt.getMinScore().compareTo(BigDecimal.valueOf(6)) > 0)
                .hasSize(1);
    }
}
