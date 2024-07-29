package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.ruizhi.common.domain.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Submission extends BaseEntity {

    private Integer studentId;
    private Integer assignmentId;
    private String content;
    private Double grade;
    private LocalDateTime submitTime;

}
