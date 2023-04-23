package com.ilongli.satoken.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ilongli
 * @date 2023/4/23 9:30
 */
@RestController
@RequestMapping("/role/")
public class RoleController {

    @RequestMapping("list")
    public String list() {
        return "角色列表：" + StpUtil.getRoleList();
    }

    @RequestMapping("check")
    public String check(String role) {
        StpUtil.checkRole(role);
        return "拥有角色：" + role;
    }

}
