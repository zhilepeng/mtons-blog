/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mblog.data.AccountProfile;
import mblog.data.Attach;
import mblog.data.Comment;
import mblog.data.Group;
import mblog.data.Post;
import mblog.data.Tag;
import mblog.data.User;
import mblog.lang.Consts;
import mblog.persist.entity.AttachPO;
import mblog.persist.entity.CommentPO;
import mblog.persist.entity.GroupPO;
import mblog.persist.entity.PostPO;
import mblog.persist.entity.TagPO;
import mblog.persist.entity.UserPO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author langhsu
 *
 */
public class BeanMapUtils {
	public static String[] USER_IGNORE = new String[]{"password"};

	public static String[] COMMENT_IGNORE = new String[]{"author"};

	public static String[] POST_IGNORE = new String[]{"author"};
	public static String[] POST_IGNORE_LIST = new String[]{"author", "markdown", "content"};
	
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
		return passport;
	}
	
	public static Comment copy(CommentPO po) {
		Comment ret = new Comment();
		BeanUtils.copyProperties(po, ret, COMMENT_IGNORE);
		
		if (po.getAuthor() != null) {
			ret.setAuthor(copy(po.getAuthor()));
		}
		return ret;
	}
	
	public static Post copy(PostPO po, int level) {
		Post d = new Post();
		if (level > 0) {
			BeanUtils.copyProperties(po, d, POST_IGNORE);
		} else {
			BeanUtils.copyProperties(po, d, POST_IGNORE_LIST);
		}
		
		if (po.getAuthor() != null) {
			User u = new User();
			u.setId(po.getAuthor().getId());
			u.setUsername(po.getAuthor().getUsername());
			u.setName(po.getAuthor().getName());
			u.setAvatar(po.getAuthor().getAvatar());
			d.setAuthor(u);
		}
		return d;
	}
	
	public static Attach copy(AttachPO po) {
		Attach ret = new Attach();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}
	
	public static Tag copy(TagPO po) {
		Tag ret = new Tag();
		BeanUtils.copyProperties(po, ret);
		return ret;
	}
	
	public static List<Tag> convertTags(long postId, String tags) {
		if (StringUtils.isBlank(tags)) {
			return Collections.emptyList();
		}
		List<Tag> ret = new ArrayList<Tag>();
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
}
