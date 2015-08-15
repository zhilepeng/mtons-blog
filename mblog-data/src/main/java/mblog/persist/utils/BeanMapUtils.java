/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import mblog.data.AccountProfile;
import mblog.data.Attach;
import mblog.data.AuthMenu;
import mblog.data.Comment;
import mblog.data.Group;
import mblog.data.Post;
import mblog.data.Role;
import mblog.data.Tag;
import mblog.data.User;
import mblog.lang.Consts;
import mblog.persist.entity.AttachPO;
import mblog.persist.entity.AuthMenuPO;
import mblog.persist.entity.CommentPO;
import mblog.persist.entity.GroupPO;
import mblog.persist.entity.PostPO;
import mblog.persist.entity.RolePO;
import mblog.persist.entity.TagPO;
import mblog.persist.entity.UserPO;

/**
 * @author langhsu
 *
 */
public class BeanMapUtils {
	public static String[] USER_IGNORE = new String[]{"password", "extend", "roles"};

	public static String[] POST_IGNORE_LIST = new String[]{"markdown", "content"};
	
	public static User copy(UserPO po) {
		if (po == null) {
			return null;
		}
		User ret = new User();
		BeanUtils.copyProperties(po, ret, USER_IGNORE);
		return ret;
	}
	
	public static AccountProfile copyPassport(UserPO po) {
		AccountProfile passport = new AccountProfile(po.getId(), po.getUsername());
		passport.setName(po.getName());
		passport.setEmail(po.getEmail());
		passport.setAvatar(po.getAvatar());
		passport.setLastLogin(po.getLastLogin());
		passport.setRoleId(po.getRoleId());
		passport.setStatus(po.getStatus());
		passport.setActiveEmail(po.getActiveEmail());

		List<AuthMenu> menus = new ArrayList<AuthMenu>();
		List<RolePO> rolePOs = po.getRoles();
		List<Role> roles = new ArrayList<Role>();
		for(RolePO rolePo :rolePOs){
			Role role = copy(rolePo);
			roles.add(role);
		}
		for(Role role : roles){
			List<AuthMenu> authMenus = role.getAuthMenus();
			menus.addAll(authMenus);
		}
		passport.setAuthMenus(menus);
		return passport;
	}

	public static Comment copy(CommentPO po) {
		Comment ret = new Comment();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}
	
	public static Post copy(PostPO po, int level) {
		Post d = new Post();
		if (level > 0) {
			BeanUtils.copyProperties(po, d);
		} else {
			BeanUtils.copyProperties(po, d, POST_IGNORE_LIST);
		}
		return d;
	}
	
	public static Attach copy(AttachPO po) {
		Attach ret = new Attach();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}
	
	public static Tag copy(TagPO po) {
		if (po == null) {
			return null;
		}
		Tag ret = new Tag();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}
	
	public static List<Tag> convertTags(long postId, String tags) {
		if (StringUtils.isBlank(tags)) {
			return Collections.emptyList();
		}
		List<Tag> ret = new ArrayList<>();
		String[] ts = StringUtils.split(tags, Consts.SEPARATOR);
		
		for (String t : ts) {
			Tag tag = new Tag();
			tag.setName(t);
			tag.setLastPostId(postId);
			tag.setPosts(1);
			ret.add(tag);
		}
		
		return ret;
	}
	
	public static Group copy(GroupPO po) {
		Group r = new Group();
		BeanUtils.copyProperties(po, r);
		return r;
	}
	
	public static AuthMenu copy(AuthMenuPO po,String... ignore){
		AuthMenu am = new AuthMenu();
		List<AuthMenu> children = new ArrayList<AuthMenu>();
		BeanUtils.copyProperties(po, am,"parent","children");
		List<String> ignoreList = Arrays.asList(ignore);
		if(po.getParent()!=null && !ignoreList.contains("parent")){
			am.setParent(BeanMapUtils.copy(po.getParent()));
		}
		for(AuthMenuPO child :po.getChildren()){
			AuthMenu childAuthMenu = BeanMapUtils.copy(child,"parent");
			childAuthMenu.setParent(am);
			children.add(childAuthMenu);
		}
		am.setChildren(children);
		return am;
	}
	
	public static Role copy(RolePO po){
		Role r = new Role();
		BeanUtils.copyProperties(po, r,"users","authMenus");
		List<AuthMenu> authMenus = new ArrayList<AuthMenu>();
		for(AuthMenuPO authMenuPO : po.getAuthMenus()){
			AuthMenu authMenu = BeanMapUtils.copy(authMenuPO);
			authMenus.add(authMenu);
		}
		r.setAuthMenus(authMenus);
		return r;
	}
}
