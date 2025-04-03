package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentFindAllVo {

    private List<AssignmentSimpleVo> assignments;
    private List<Integer> accessAssignments;

}
