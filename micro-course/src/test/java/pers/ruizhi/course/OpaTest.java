package pers.ruizhi.course;

import org.junit.jupiter.api.Test;
import pers.ruizhi.course.domain.Assignment;
import pers.ruizhi.course.domain.AssignmentAccessDto;
import pers.ruizhi.course.domain.AssignmentAccessResponse;
import pers.ruizhi.course.domain.Submission;
import pers.ruizhi.course.util.OpaWebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Chris
 * @Date 2024/8/6
 */
public class OpaTest {

    @Test
    public void test() {
        OpaWebClient opaWebClient = new OpaWebClient();
        Submission submission1 = new Submission(0, 0, "", 0.0, LocalDateTime.now());
        Submission submission2 = new Submission(0, 1, "", 0.0, LocalDateTime.now());
        List<Submission> submissions = new ArrayList<>();
        submissions.add(submission1);
        submissions.add(submission2);
        Assignment assignment1 = Assignment
                .builder()
                .id(0)
                .order(1)
                .build();
        Assignment assignment2 = Assignment
                .builder()
                .id(1)
                .order(2)
                .build();
        Assignment assignment3 = Assignment
                .builder()
                .id(2)
                .order(3)
                .build();
        List<Assignment> assignments = new ArrayList<>();
        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);

        AssignmentAccessDto assignmentAccessDto = new AssignmentAccessDto(submissions, assignments);
        AssignmentAccessResponse assignmentAccessResponse = opaWebClient.accessAssignment(assignmentAccessDto);
    }
}
