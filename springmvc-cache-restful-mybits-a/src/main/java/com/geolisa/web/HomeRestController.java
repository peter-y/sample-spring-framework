package com.geolisa.web;

import com.geolisa.vo.DefultResult;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.LastModified;

// restController 是一个组合注解
@RestController
@RequestMapping(value = "/home")
public class HomeRestController implements LastModified {

    private final Logger logger = LoggerFactory.getLogger(HomeRestController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public DefultResult getHomeIndex() {
        return new DefultResult("success");
    }

    //requestMapping的快捷方法

    /**
     * 获取当前请求的cookie信息.
     * @param request 预定义对应，可直接获取使用
     * @return cookie信息
     */
    @GetMapping(value = "/cookie")
    public Map getInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, String> result = new HashMap<>();
        for (Cookie c : cookies) {
            result.put(c.getName(), c.getValue());
        }
        return result;
    }

    //请求中最后修改时间的支持
    @Override
    public long getLastModified(HttpServletRequest request) {
        return System.currentTimeMillis();
    }
}
