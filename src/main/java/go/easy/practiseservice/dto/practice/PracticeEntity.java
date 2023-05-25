package go.easy.practiseservice.dto.practice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "practice")
public class PracticeEntity {
    @Id
    private String id;

    @NotBlank
    private String sphereId;

    @NotBlank
    private String title;

    private BigDecimal minScore;

    private BigDecimal maxScore;

    @Positive
    private long version;

    @NotBlank
    private String practiceDescription;

}
