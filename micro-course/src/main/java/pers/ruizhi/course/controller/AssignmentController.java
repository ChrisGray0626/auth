package pers.ruizhi.course.controller;

import org.springframework.web.bind.annotation.*;
import pers.ruizhi.course.domain.AssignmentFindAllVo;
import pers.ruizhi.course.domain.Submission;
import pers.ruizhi.course.domain.SubmissionDto;
import pers.ruizhi.course.service.AssignmentService;

import javax.annotation.Resource;
import java.util.List;

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
    public List<AssignmentFindAllVo> findAll(@RequestParam Integer courseId) {

        return assignmentService.findAll(courseId);
    }

}
