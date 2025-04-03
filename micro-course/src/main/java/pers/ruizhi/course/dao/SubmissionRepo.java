package pers.ruizhi.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pers.ruizhi.course.domain.Submission;

import java.util.List;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Repository
public interface SubmissionRepo extends JpaRepository<Submission, Integer> {

    Submission findByAssignmentIdAndStudentId(Integer assignmentId, Integer studentId);

    @Query(value = "select s from Submission s left join Assignment a on s.assignmentId = a.id where a.courseId = ?1 and s.studentId = ?2")
    List<Submission> findAllByCourseIdAndStudentId(Integer courseId, Integer studentId);
}
