package pers.ruizhi.course.controller;

import org.springframework.web.bind.annotation.*;
import pers.ruizhi.course.domain.Assignment;
import pers.ruizhi.course.domain.AssignmentFindAllVo;
import pers.ruizhi.course.domain.Submission;
import pers.ruizhi.course.domain.SubmissionDto;
import pers.ruizhi.course.service.AssignmentService;

import javax.annotation.Resource;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Resource
    private AssignmentService assignmentService;

    @PostMapping("/submit")
    public Submission submit(@RequestBody SubmissionDto submissionDto) {
        return assignmentService.submit(submissionDto);
    }

    @GetMapping("/assignments")
    public AssignmentFindAllVo findAll(@RequestParam Integer courseId) {

        return assignmentService.findAll(courseId);
    }

    @GetMapping("/assignment")
    public Assignment findItem(@RequestParam Integer assignmentId) {

        return assignmentService.findItem(assignmentId);
    }

}
