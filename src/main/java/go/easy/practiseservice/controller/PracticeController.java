package go.easy.practiseservice.controller;

import go.easy.practiseservice.dto.practice.PracticeEntity;
import go.easy.practiseservice.service.PracticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/practice")
@RequiredArgsConstructor
public class PracticeController {

    private final PracticeService service;

    @Operation(summary = "Получить все практики по сфере")
    @GetMapping("sphere/{sphere}")
    ResponseEntity<List<PracticeEntity>> getPracticeBySphere(@PathVariable String sphere) {
        return ResponseEntity.ok(service.getPracticeByBySphere(sphere));
    }

    @Operation(summary = "Получить все практики по сфере отвечающие скорингу")
    @GetMapping("sphere/{sphere}/{sphereScore}")
    ResponseEntity<List<PracticeEntity>> getPracticeBySphereAndScore(@PathVariable String sphere, @PathVariable BigDecimal sphereScore) {
        return ResponseEntity.ok(service.getPracticeByBySphereAndScore(sphere, sphereScore));
    }


}
