package com.ilongli.satoken.demo.controller;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ilongli
 * @date 2023/4/23 9:39
 */
@RestController
@RequestMapping("/basic/")
public class HttpBasicController {

//    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("test1")
    public SaResult test3() {
        SaBasicUtil.check("sa:123456");
        // ... 其它代码
        return SaResult.ok();
    }


}
