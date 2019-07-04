package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杰威力
 * 2019/7/4 01:14
 * 在页面获取登录人姓名
 */

@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping("/name")
    public Map<String,Object> name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loginName", name);
        return map;
    }

}
