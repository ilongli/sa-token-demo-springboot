package com.ilongli.satoken.demo.controller;

import cn.dev33.satoken.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ilongli
 * @date 2023/4/23 9:39
 */
@RestController
@RequestMapping("/auth/")
public class AuthController {

    // 登录校验：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 角色校验：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("add1")
    public String add1() {
        return "用户增加";
    }

    // 权限校验：必须具有指定权限才能进入该方法
    @SaCheckPermission("user-add")
    @RequestMapping("add2")
    public String add2() {
        return "用户增加";
    }

    // 二级认证校验：必须二级认证之后才能进入该方法
    @SaCheckSafe()
    @RequestMapping("add3")
    public String add3() {
        return "用户增加";
    }

    // Http Basic 校验：只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("add4")
    public String add4() {
        return "用户增加";
    }

    // 校验当前账号是否被封禁 comment 服务，如果已被封禁会抛出异常，无法进入方法
    @SaCheckDisable("comment")
    @RequestMapping("send")
    public String send() {
        return "查询用户信息";
    }

    /**
     * @SaIgnore拥有最高优先级
     * @return
     */
    @SaIgnore
    @SaCheckLogin
    @RequestMapping("test")
    public String test() {
        return "test";
    }
}
