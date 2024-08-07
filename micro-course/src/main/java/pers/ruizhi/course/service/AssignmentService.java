package pers.ruizhi.course.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.ruizhi.common.Response;
import pers.ruizhi.common.ResponseEnum;
import pers.ruizhi.course.Constant;
import pers.ruizhi.course.dao.AssignmentRepo;
import pers.ruizhi.course.dao.CourseRepo;
import pers.ruizhi.course.dao.StudentRepo;
import pers.ruizhi.course.dao.SubmissionRepo;
import pers.ruizhi.course.domain.*;
import pers.ruizhi.course.exception.EntityNotFoundException;
import pers.ruizhi.course.util.OpaWebClient;
import pers.ruizhi.course.util.RequestUtil;
import pers.ruizhi.course.util.Util;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Resource
    private OpaWebClient client;


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

    public AssignmentFindAllVo findAll(Integer courseId) {
        // Check if the course exists
        if (!courseRepo.existsById(courseId)) {
            throw new EntityNotFoundException(Constant.ENTITY_COURSE, courseId);
        }
        // Get student
        Student student = (Student) RequestUtil.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);
        Integer studentId = student.getId();
        // Filter the assignments that the student has submitted
        List<Submission> submissions = submissionRepo.findAllByCourseIdAndStudentId(courseId, studentId);
        List<Assignment> assignments = assignmentRepo.findAllByCourseId(courseId);
        List<Integer> accessAssignments = client
                .accessAssignment(new AssignmentAccessDto(submissions, assignments))
                .getResult()
                .getAccessAssignments();

        return new AssignmentFindAllVo(assignments, accessAssignments);
    }

    public Response findItem(Integer assignmentId) {
        Optional<Assignment> assignmentOptional = assignmentRepo.findById(assignmentId);
        if (assignmentOptional.isEmpty()) {
            throw new EntityNotFoundException(Constant.ENTITY_ASSIGNMENT, assignmentId);
        }
        Assignment assignment = assignmentOptional.get();
        Integer courseId = assignment.getCourseId();
        // Get student
        Student student = (Student) RequestUtil.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);
        Integer studentId = student.getId();
        // Filter the assignments that the student has submitted
        List<Submission> submissions = submissionRepo.findAllByCourseIdAndStudentId(courseId, studentId);
        List<Assignment> assignments = assignmentRepo.findAllByCourseId(courseId);
        List<Integer> accessAssignments = client
                .accessAssignment(new AssignmentAccessDto(submissions, assignments))
                .getResult()
                .getAccessAssignments();
        if (accessAssignments.contains(assignmentId)) {
            return Response.success(assignment);
        } else {
            return new Response(ResponseEnum.DENIED_ACCESS_ERROR);
        }
    }

    private double mark(String content) {
        return Util.generateRandom10();
    }
}
