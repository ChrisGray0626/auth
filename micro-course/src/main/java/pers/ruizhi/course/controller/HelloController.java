package pers.ruizhi.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ruizhi.common.Response;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Response hello() {
        return Response.success("hello");
    }

}
