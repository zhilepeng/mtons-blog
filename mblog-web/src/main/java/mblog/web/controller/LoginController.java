/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mblog.web.controller.desk.Views;
import mtons.modules.utils.MD5Helper;

/**
 * 
 * @author langhsu
 *
 */
@Controller
public class LoginController extends BaseController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String view() {
		return getView(Views.LOGIN);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, ModelMap model) {
		String ret = getView(Views.LOGIN);
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ret;
        }
		
		AuthenticationToken token = createToken(username, password);
        if (token == null) {
        	model.put("message", "用户名或密码错误");
            return ret;
        }
        
        try {
            SecurityUtils.getSubject().login(token);
            
            ret = "redirect:" + Views.HOME;
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
            	model.put("message", "用户不存在");
            } else if (e instanceof LockedAccountException) {
            	model.put("message", "用户被禁用");
            } else {
            	model.put("message", "用户认证失败");
            }
        }
        return ret;
	}

	protected AuthenticationToken createToken(String username, String password) {
        return new UsernamePasswordToken(username, MD5Helper.md5(password));
    }
}
