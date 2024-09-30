package com.grainacademy.backend.eduservice.controller;

import com.grainacademy.backend.commonutils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administration/user")
@Tag(name = "AdminUserController", description = "操作后台管理者信息的接口")
@CrossOrigin
public class AdminUserController {
    @PostMapping("login")
    @Operation(summary="Login", description = "Login a administrator")
    @Parameter(name = "id", description = "The id that need to be deleted")
    @ApiResponse(description = "return a JWT token of the current")
    public Result Login(){
        return Result.succeed().data("token", "admin");
    }

    @GetMapping("info")
    public Result Get(){
        return Result.succeed().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("logout")
    public Result Logout(){
        return Result.failed();
    }

}
