package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentFindAllVo {

    private List<Assignment> assignments;
    private List<Integer> accessAssignments;

}
