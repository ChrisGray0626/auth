package pers.ruizhi.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ruizhi.common.domain.Response;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */

@RestController
public class HelloController {

    @GetMapping("/server/hello")
    public Response hello() {
        return Response.success("hello");
    }

}
