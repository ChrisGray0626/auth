package pers.ruizhi.course.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import static pers.ruizhi.course.Constant.OPA_URL_PREFIX;
import pers.ruizhi.course.domain.AssignmentAccessDto;
import pers.ruizhi.course.domain.AssignmentAccessResponse;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/8/6
 */
@Slf4j
@Component
public class OpaWebClient {

    private final WebClient client;
    @Resource
    private ObjectMapper objectMapper;
    @Value("${opa.policy}")
    private String policy;

    public OpaWebClient() {
        this.client = WebClient
                .builder()
                .baseUrl(OPA_URL_PREFIX + policy)
                .build();
    }

    public AssignmentAccessResponse accessAssignment(AssignmentAccessDto assignmentAccessDto) {
        String value;
        try {
            value = objectMapper.writeValueAsString(assignmentAccessDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        AssignmentAccessResponse response = client
                .post()
                .bodyValue(value)
                .retrieve()
                .bodyToMono(AssignmentAccessResponse.class)
                .timeout(Duration.of(60, ChronoUnit.SECONDS))
                .block();
        log.info("assignmentAccessVo {}", response);
        return response;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
