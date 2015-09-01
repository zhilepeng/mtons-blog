/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.dao;

import mblog.persist.entity.GroupPO;
import mtons.modules.persist.Dao;

import java.util.List;

/**
 * @author langhsu
 *
 */
public interface GroupDao extends Dao<GroupPO> {
	List<GroupPO> findAll();
	GroupPO getByKey(String key);
}
