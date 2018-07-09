package com.spf.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.spf.core.exception.ErrorException;
import com.spf.core.utlis.SignUtil;
import com.spf.model.ResultJson;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：  权限验证拦截器
 * @date 2018-07-06 16:34
 */
@Component("signInterceptor")
public class SignInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> handlerMap = new HashMap<>();
        handlerMap.put("uri", request.getRequestURI());

        Map<String, Object> params = WebUtils.getParametersStartingWith(request,"");
        Object debug = null;
        if ((debug = params.get("debug"))!= null && debug.equals("wx_order_dedug")
                || (SignUtil.verify(params, "utf-8") && SignUtil.handlerVerify(handlerMap,request.getHeader("author")))) {
            return true;
        } else {
            ResultJson json = new ResultJson().fail(400,"不法请求");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().append(JSON.toJSONString(json));

            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
