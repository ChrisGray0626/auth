package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/8/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentAccessResponse {

    private Result result;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        List<Integer> accessAssignments;

    }

}
