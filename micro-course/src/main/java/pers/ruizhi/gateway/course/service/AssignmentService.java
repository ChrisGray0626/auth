package pers.ruizhi.gateway.course.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.ruizhi.gateway.course.Constant;
import pers.ruizhi.gateway.course.dao.AssignmentRepo;
import pers.ruizhi.gateway.course.dao.CourseRepo;
import pers.ruizhi.gateway.course.dao.StudentRepo;
import pers.ruizhi.gateway.course.dao.SubmissionRepo;
import pers.ruizhi.course.domain.*;
import pers.ruizhi.gateway.course.domain.*;
import pers.ruizhi.gateway.course.exception.EntityNotFoundException;
import pers.ruizhi.gateway.course.util.RequestUtil;
import pers.ruizhi.gateway.course.util.Util;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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
        // Get student
        Student student = (Student) RequestUtil.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);
        Integer studentId = student.getId();
        // Check if the submission exists
        Submission submission = submissionRepo.findByAssignmentIdAndStudentId(assignmentId, studentId);
        if (ObjectUtils.isEmpty(submission)) {
            submission = new Submission();
            submission.setStudentId(studentId);
        }
        BeanUtils.copyProperties(submissionDto, submission);
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
        // TODO findAllAssignmentByStudent
        return assignmentRepo.findAllByCourseId(courseId);
    }

    public Assignment findOne(Integer assignmentId, Student student) {
        // TODO Check if the student enrolled in the course of the assignment
        return null;
    }

    private double mark(String content) {
        return Util.generateRandom10();
    }
}
