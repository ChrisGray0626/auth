package pers.ruizhi.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ruizhi.server.domain.Response;

import java.security.Principal;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/3
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Response hello() {
        return Response.success("hello");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public Response admin() {
        return Response.success("admin");
    }

    @GetMapping("/user")
    public Response user(Principal principal) {
        return Response.success(principal);
    }
}
