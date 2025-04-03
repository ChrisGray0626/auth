package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pers.ruizhi.common.BaseEntity;

import javax.persistence.Entity;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseEntity {

    private String name;
}
