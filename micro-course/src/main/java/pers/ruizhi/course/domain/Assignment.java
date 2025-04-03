package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pers.ruizhi.common.BaseEntity;

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
@SuperBuilder
@Entity
public class Assignment extends BaseEntity {

    private Integer courseId;
    private String name;
    private Integer order;
    private String question;
    private LocalDateTime dueTime;

}
