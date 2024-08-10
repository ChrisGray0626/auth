package pers.ruizhi.course.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.ruizhi.course.Constant;
import pers.ruizhi.course.dao.AssignmentRepo;
import pers.ruizhi.course.dao.CourseRepo;
import pers.ruizhi.course.dao.StudentRepo;
import pers.ruizhi.course.dao.SubmissionRepo;
import pers.ruizhi.course.domain.*;
import pers.ruizhi.course.exception.AccessDeniedException;
import pers.ruizhi.course.exception.EntityNotFoundException;
import pers.ruizhi.course.util.OpaWebClient;
import pers.ruizhi.course.util.RequestUtil;
import pers.ruizhi.course.util.Util;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description Assignment Service
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
    @Resource
    private OpaWebClient client;


    public Submission submit(SubmissionDto submissionDto) {
        Integer assignmentId = submissionDto.getAssignmentId();
        Assignment assignment = assignmentRepo
                .findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException(Constant.ENTITY_ASSIGNMENT, assignmentId));
        // Check the access to the assignment
        if (!this.checkAssignmentAccess(assignmentId, assignment.getCourseId())) {
            throw new AccessDeniedException();
        }
        Integer studentId = RequestUtil
                .getStudent()
                .getId();
        // Check if the submission exists
        Submission submission = new Submission();
        BeanUtils.copyProperties(submissionDto, submission);
        submission.setStudentId(studentId);
        // Mark the assignment
        double grade = this.mark(submission.getContent());
        submission.setGrade(grade);
        // Set Submit time
        LocalDateTime submitTime = LocalDateTime.now();
        submission.setSubmitTime(submitTime);
        // Save the submission
        submission = submissionRepo.save(submission);

        return submission;
    }

    public AssignmentFindAllVo findAll(Integer courseId) {
        courseRepo
                .findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(Constant.ENTITY_COURSE, courseId));
        List<AssignmentSimpleVo> assignments = assignmentRepo.findAllByCourseId(courseId);
        List<Integer> accessAssignments = this.getAccessAssignments(courseId);

        return new AssignmentFindAllVo(assignments, accessAssignments);
    }

    public Assignment findItem(Integer assignmentId) {
        Assignment assignment = assignmentRepo
                .findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException(Constant.ENTITY_ASSIGNMENT, assignmentId));
        Integer courseId = assignment.getCourseId();
        // Check the access to the assignment
        if (this.checkAssignmentAccess(assignmentId, courseId)) {
            return assignment;
        } else {
            throw new AccessDeniedException();
        }
    }

    private List<Integer> getAccessAssignments(Integer courseId) {
        Integer studentId = RequestUtil
                .getStudent()
                .getId();
        List<Submission> submissions = submissionRepo.findAllByCourseIdAndStudentId(courseId, studentId);
        List<AssignmentSimpleVo> assignments = assignmentRepo.findAllByCourseId(courseId);
        return client
                .accessAssignment(new AssignmentAccessDto(submissions, assignments))
                .getResult()
                .getAccessAssignments();
    }

    public boolean checkAssignmentAccess(Integer assignmentId, Integer courseId) {
        List<Integer> accessAssignments = this.getAccessAssignments(courseId);
        // Filter the assignments that the student has submitted
        return accessAssignments.contains(assignmentId);
    }

    private double mark(String content) {
        return Util.generateRandom10();
    }
}
