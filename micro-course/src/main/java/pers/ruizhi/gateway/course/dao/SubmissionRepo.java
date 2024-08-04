package pers.ruizhi.gateway.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.ruizhi.gateway.course.domain.Submission;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface SubmissionRepo extends JpaRepository<Submission, Integer> {

    Submission findByAssignmentIdAndStudentId(Integer assignmentId, Integer studentId);
}
