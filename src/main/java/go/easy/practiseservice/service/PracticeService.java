package go.easy.practiseservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import go.easy.practiseservice.dto.practice.PracticeEntity;
import go.easy.practiseservice.exception.PracticeNotFoundException;
import go.easy.practiseservice.repository.PracticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PracticeService {

    private final PracticeRepository practiceRepository;
    private final ObjectMapper objectMapper;


    public List<PracticeEntity> getPracticeByBySphere(String sphere) {
        log.info("getting practice by sphere: {}", sphere);
        return practiceRepository.findAllBySphereId(sphere)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new PracticeNotFoundException(HttpStatus.NOT_FOUND, "can't find practice by sphere: " + sphere);
                    return result;
                }));
    }

    public List<PracticeEntity> getPracticeByBySphereAndScore(String sphere, BigDecimal sphereScore) {
        log.info("getting practice by sphere: {}", sphere);
        return practiceRepository.findAllBySphereAndScore(sphere, sphereScore)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new PracticeNotFoundException(HttpStatus.NOT_FOUND, String.format("can't find practice by sphere: %s and score: %s", sphere, sphereScore));
                    return result;
                }));
    }

    public void uploadPractice() {
        parseFile().forEach(this::process);
    }

    private void process(PracticeEntity practiceEntity) {
        log.info("uploading practice: {}", practiceEntity.getId());
        practiceRepository.findById(practiceEntity.getId()).
                ifPresentOrElse(originSet -> {
                    if (practiceEntity.getVersion() > originSet.getVersion()) {
                        save(practiceEntity);
                    }
                }, () -> save(practiceEntity));
    }

    private void save(PracticeEntity practiceEntity) {
        practiceRepository.save(practiceEntity);
    }

    @SneakyThrows
    private List<PracticeEntity> parseFile() {
        log.info("parsing file with practice");
        var inputStream = new ClassPathResource("practice.json").getInputStream();
        return objectMapper.readValue(inputStream,
                new TypeReference<>() {
                });
    }


}
