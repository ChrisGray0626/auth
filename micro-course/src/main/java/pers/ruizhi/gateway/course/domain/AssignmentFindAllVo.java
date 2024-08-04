package pers.ruizhi.gateway.course.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Data
@NoArgsConstructor
public class AssignmentFindAllVo {

    private Integer id;
    private String name;
    private LocalDateTime dueTime;

    public AssignmentFindAllVo(Integer id, String name, LocalDateTime dueTime) {
        this.id = id;
        this.name = name;
        this.dueTime = dueTime;
    }
}
