package com.spf.core.exception;

import com.alibaba.fastjson.JSON;
import com.spf.model.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明： 全局异常处理类
 * @date 2018-07-09 10:55
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");

        StringBuilder sb = new StringBuilder();
       for (String key : params.keySet()) {
           sb.append(key).append("=").append(params.get(key)).append("&");
       }

       String paramsKey = sb.toString();
       if (sb.length() > 0) {
            paramsKey = "?"+paramsKey.substring(0, sb.length() - 1);
       }

        logger.error("URL: " + request.getRequestURL() + paramsKey + ", params: " + JSON.toJSONString(params));

        ResultJson json = null;
        if (e instanceof ErrorException || e instanceof TimeStampException) {
            json = new ResultJson().fail( e.getMessage());
        } else {
            json = new ResultJson().fail(-1,"调用失败");
        }

        // todo 此处可以添加到数据库错误日志表

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().append(JSON.toJSONString(json));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

}
