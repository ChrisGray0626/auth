package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/8/10
 */
@Data
@AllArgsConstructor
@Builder
public class AssignmentSimpleVo {
    private Integer id;
    private String name;
    private Integer order;
    private LocalDateTime dueTime;
}