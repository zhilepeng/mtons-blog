/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.commons.persist.dao;

import mblog.commons.persist.entity.GroupPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface GroupDao extends Dao<GroupPO> {
	GroupPO getByKey(String key);
}
