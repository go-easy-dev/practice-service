package go.easy.practiseservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import go.easy.practiseservice.dto.practice.PracticeEntity;
import go.easy.practiseservice.exception.PracticeNotFoundException;
import go.easy.practiseservice.repository.PracticeRepository;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    private final EasyRandom random = new EasyRandom();
    @Mock
    ObjectMapper objectMapper;
    @Mock
    PracticeRepository practiceRepository;
    @InjectMocks
    PracticeService practiceService;

    @Test
    void should_get_practice_by_sphere() {
        // given:
        Mockito.when(practiceRepository.findAllBySphereId("SPHERE_ID")).thenReturn(
                List.of(PracticeEntity.builder()
                        .id("ID")
                        .sphereId("SPHERE_ID")
                        .build())
        );

        // when:
        var actual = practiceService.getPracticeByBySphere("SPHERE_ID");

        // then:
        Assertions.assertThat(actual)
                .isNotEmpty();
    }

    @Test
    void should_throw_practice_by_sphere() {
        // given:
        Mockito.when(practiceRepository.findAllBySphereId(Mockito.any())).thenReturn(Collections.emptyList());

        // then:
        Assertions.assertThatThrownBy(() -> practiceService.getPracticeByBySphere("SPHERE_ID"))
                .isInstanceOf(PracticeNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"can't find survey by sphere: SPHERE_ID\"");
    }

    @Test
    void should_get_practice_by_sphere_and_score() {
        // given:
        Mockito.when(practiceRepository.findAllBySphereIdAndMinScoreGreaterThan("SPHERE_ID", BigDecimal.ONE))
                .thenReturn(List.of(PracticeEntity.builder()
                        .id("ID")
                        .sphereId("SPHERE_ID")
                        .build())
                );

        // when:
        var actual = practiceService.getPracticeByBySphereAndScore("SPHERE_ID", BigDecimal.ONE);

        // then:
        Assertions.assertThat(actual)
                .isNotEmpty();
    }

    @Test
    void should_throw_practice_by_sphere_and_score() {
        // given:
        Mockito.when(practiceRepository.findAllBySphereIdAndMinScoreGreaterThan("sphere", BigDecimal.ONE))
                .thenReturn(Collections.emptyList());

        // then:
        Assertions.assertThatThrownBy(() -> practiceService.getPracticeByBySphereAndScore("sphere", BigDecimal.ONE))
                .isInstanceOf(PracticeNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"can't find survey by sphere: sphere and score: 1\"");
    }
}
