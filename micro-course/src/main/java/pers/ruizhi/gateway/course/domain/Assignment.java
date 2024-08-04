package pers.ruizhi.gateway.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.ruizhi.gateway.common.domain.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assignment extends BaseEntity {

    private Integer courseId;
    private String name;
    private Integer order;
    private String question;
    private LocalDateTime dueTime;

}
