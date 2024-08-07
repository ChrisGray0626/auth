package pers.ruizhi.course.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author Chris
 * @Date 2024/8/6
 */
@Data
@AllArgsConstructor
public class AssignmentAccessDto {

    private Input input;

    public AssignmentAccessDto(List<Submission> submissions, List<Assignment> assignments) {
        input = new Input(submissions, assignments);
    }

    @Data
    @AllArgsConstructor
    public static class Input {
        private List<Submission> submissions;
        private List<Assignment> assignments;
    }
}
