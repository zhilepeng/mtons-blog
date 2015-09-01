/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.dao.impl;

import mblog.persist.dao.GroupDao;
import mblog.persist.entity.GroupPO;
import mtons.modules.lang.Const;
import mtons.modules.lang.EntityStatus;
import mtons.modules.persist.impl.DaoImpl;

import java.util.List;

/**
 * @author langhsu
 *
 */
public class GroupDaoImpl extends DaoImpl<GroupPO> implements GroupDao {
	private static final long serialVersionUID = -3510165157507261158L;

	public GroupDaoImpl() {
		super(GroupPO.class);
	}

	@Override
	public List<GroupPO> findAll() {
		return findBy("status", Const.STATUS_NORMAL);
	}

	@Override
	public GroupPO getByKey(String key) {
		return findUniqueBy("key", key);
	}

}
