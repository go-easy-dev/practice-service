package go.easy.practiseservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final PracticeService questionService;

    @Async
    @EventListener(value = ContextRefreshedEvent.class)
    public void upload() {
        log.info("uploading practice start");
        questionService.uploadPractice();
        log.info("uploading practice finished");
    }
}
