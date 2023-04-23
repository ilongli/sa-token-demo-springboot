package com.ilongli.satoken.demo.exception.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.NotSafeException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ilongli
 * @date 2023/4/23 9:21
 */
@RestControllerAdvice(value = "com.ilongli.satoken.demo.controller")
public class GlobalExceptionHandler {

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public SaResult handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        }
        else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        }
        else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        }
        else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        }
        else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        }
        else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return SaResult.error(message);
    }

    /**
     * 权限异常全局拦截器
     * @param npe
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handlerNotPermissionException(NotPermissionException npe)
            throws Exception {
        String permission = npe.getPermission();
        return SaResult.error("权限不足：" + permission);
    }

    /**
     * 角色异常全局拦截器
     * @param nre
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NotRoleException.class)
    public SaResult handlerNotPermissionException(NotRoleException nre)
            throws Exception {
        String role = nre.getRole();
        return SaResult.error("缺少角色：" + role);
    }

    /**
     * 二级认证失败全局拦截器
     * @param nse
     * @return
     * @throws Exception
     */
    @ExceptionHandler(NotSafeException.class)
    public SaResult handlerNotSafeException(NotSafeException nse)
            throws Exception {
        Object service = nse.getService();
        return SaResult.error("需要二级认证：" + service);
    }



    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }



}
