package pers.ruizhi.course.controller;

import org.springframework.web.bind.annotation.*;
import pers.ruizhi.course.Constant;
import pers.ruizhi.course.domain.AssignmentFindAllVo;
import pers.ruizhi.course.domain.Student;
import pers.ruizhi.course.domain.Submission;
import pers.ruizhi.course.domain.SubmissionDto;
import pers.ruizhi.course.service.AssignmentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Author Chris
 * @Date 2024/7/29
 */
@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Resource
    private AssignmentService assignmentService;

    @PostMapping("/submit")
    public Submission submit(@RequestBody SubmissionDto submissionDto, HttpServletRequest request) {
        Student student = (Student) request.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);

        return assignmentService.submit(submissionDto, student);
    }

    @GetMapping("/assignments")
    public List<AssignmentFindAllVo> findAll(@RequestParam Integer courseId, HttpServletRequest request) {
        Student student = (Student) request.getAttribute(Constant.ATTRIBUTE_KEY_STUDENT);

        return assignmentService.findAll(courseId, student);
    }

}
