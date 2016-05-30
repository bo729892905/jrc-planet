package com.jrcplanet.web.handler;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * controller默认异常处理
 * Created by rxb on 2016/1/29.
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    /**
     * 未授权异常处理
     * @return ModelAndView
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthorizedException(NativeWebRequest request, UnauthorizedException e) {
        return new ModelAndView("redirect:/errors/403.jsp");
    }
}
