/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.web.controller.desk;

/**
 * 
 * 返回页面配置
 * 
 * @author langhsu
 *
 */
public interface Views {
	String LOGIN = "/login";
	String REG = "/reg";
	String REG_RESULT = "/reg_result";
	
	String INDEX = "/index";
	String HOME = "/home";
	String TA_HOME = "/ta/home";
	String ACCOUNT_AVATAR = "/account/avatar";
	String ACCOUNT_PASSWORD = "/account/password";
	String ACCOUNT_PROFILE = "/account/profile";
	
	String BLOG_POST = "/post/post_";
	
	String BLOG_UPLOAD = "/post/upload";
	
	String TAGS_INDEX = "/tags/index";
	String TAGS_TAG = "/tags/tag";
	
	String BROWSE_GALLERY = "/browse/gallery";
	String BROWSE_GALLERY_SNIPPET = "/browse/snippet";
	String BROWSE_SEARCH = "/browse/search";
	String BROWSE_DETAIL = "/browse/detail";
	
	String ROUTE_POST_PUBLISH = "/group/%s/post";
	String ROUTE_POST_INDEX = "/group/%s/index";
	String ROUTE_POST_VIEW = "/group/%s/view";
}
