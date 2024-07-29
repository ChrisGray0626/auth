package pers.ruizhi.course.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.ruizhi.course.Constant;
import pers.ruizhi.course.dao.AssignmentRepo;
import pers.ruizhi.course.dao.StudentRepo;
import pers.ruizhi.course.dao.SubmissionRepo;
import pers.ruizhi.course.domain.Student;
import pers.ruizhi.course.domain.Submission;
import pers.ruizhi.course.domain.SubmissionDto;
import pers.ruizhi.course.exception.EntityNotFoundException;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static pers.ruizhi.course.util.Util.generateRandom10;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/29
 */
@Service
public class AssignmentService {

    @Resource
    private AssignmentRepo assignmentRepo;
    @Resource
    private StudentRepo studentRepo;
    @Resource
    private SubmissionRepo submissionRepo;


    public Submission submit(SubmissionDto submissionDto, Student student) {
        // Check if the assignment exists
        Integer assignmentId = submissionDto.getAssignmentId();
        if (!assignmentRepo.existsById(assignmentId)) {
            throw new EntityNotFoundException(Constant.ENTITY_ASSIGNMENT, assignmentId);
        }
        Submission submission = new Submission();
        BeanUtils.copyProperties(submissionDto, submission);
        Integer studentId = student.getId();
        submission.setStudentId(studentId);
        // Mark the assignment
        double grade = this.mark(submissionDto.getContent());
        submission.setGrade(grade);
        // Set Submit time
        LocalDateTime submitTime = LocalDateTime.now();
        submission.setSubmitTime(submitTime);
        // Save the submission
        submission = submissionRepo.save(submission);

        return submission;
    }

    private double mark(String content) {
        return generateRandom10();
    }
}
