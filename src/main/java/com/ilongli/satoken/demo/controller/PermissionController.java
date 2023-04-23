package com.ilongli.satoken.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ilongli
 * @date 2023/4/23 9:30
 */
@RestController
@RequestMapping("/permission/")
public class PermissionController {

    @RequestMapping("list")
    public String list() {
        return "权限列表：" + StpUtil.getPermissionList();
    }

    @RequestMapping("check")
    public String check(String permission) {
        StpUtil.checkPermission(permission);
        return "拥有权限：" + permission;
    }

}
