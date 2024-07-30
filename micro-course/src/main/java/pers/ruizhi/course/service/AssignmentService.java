package pers.ruizhi.course.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.ruizhi.course.Constant;
import pers.ruizhi.course.dao.AssignmentRepo;
import pers.ruizhi.course.dao.CourseRepo;
import pers.ruizhi.course.dao.StudentRepo;
import pers.ruizhi.course.dao.SubmissionRepo;
import pers.ruizhi.course.domain.*;
import pers.ruizhi.course.exception.EntityNotFoundException;
import pers.ruizhi.course.util.RequestUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static pers.ruizhi.course.util.Util.generateRandom10;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@Service
public class AssignmentService {

    @Resource
    private AssignmentRepo assignmentRepo;
    @Resource
    private CourseRepo courseRepo;
    @Resource
    private StudentRepo studentRepo;
    @Resource
    private SubmissionRepo submissionRepo;


    public Submission submit(SubmissionDto submissionDto) {
        // Check if the assignment exists
        Integer assignmentId = submissionDto.getAssignmentId();
        if (!assignmentRepo.existsById(assignmentId)) {
            throw new EntityNotFoundException(Constant.ENTITY_ASSIGNMENT, assignmentId);
        }
        Submission submission = new Submission();
        BeanUtils.copyProperties(submissionDto, submission);
        // Get student
        Student student = (Student) RequestUtil.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);
        assert student != null;
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

    public List<AssignmentFindAllVo> findAll(Integer courseId) {
        // Check if the course exists
        if (!courseRepo.existsById(courseId)) {
            throw new EntityNotFoundException(Constant.ENTITY_COURSE, courseId);
        }
        // Get student
        Student student = (Student) RequestUtil.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);
        assert student != null;
        // TODO findAllAssignmentByStudent
        return assignmentRepo.findAllByCourseId(courseId);
    }

    public Assignment findOne(Integer assignmentId, Student student) {
        // TODO Check if the student enrolled in the course of the assignment
        return null;
    }

    private double mark(String content) {
        return generateRandom10();
    }
}
