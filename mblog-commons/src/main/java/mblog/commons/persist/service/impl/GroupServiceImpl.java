/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.commons.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.commons.data.Group;
import mblog.commons.persist.dao.GroupDao;
import mblog.commons.persist.entity.GroupPO;
import mblog.commons.persist.service.GroupService;
import mblog.commons.utils.BeanMapUtils;

/**
 * @author langhsu
 *
 */
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;

	@Override
	@Transactional(readOnly = true)
	public List<Group> findAll() {
		List<GroupPO> list = groupDao.list();
		List<Group> rets = new ArrayList<Group>();
		for (GroupPO po : list) {
			rets.add(BeanMapUtils.copy(po));
		}
		return rets;
	}

	@Override
	@Transactional(readOnly = true)
	public Group getById(int id) {
		return BeanMapUtils.copy(groupDao.get(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Group getByKey(String key) {
		return BeanMapUtils.copy(groupDao.getByKey(key));
	}
	
}
