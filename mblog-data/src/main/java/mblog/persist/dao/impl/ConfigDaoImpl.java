/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao.impl;

import mtons.modules.persist.impl.DaoImpl;
import mblog.persist.dao.ConfigDao;
import mblog.persist.entity.ConfigPO;

/**
 * @author langhsu
 *
 */
public class ConfigDaoImpl extends DaoImpl<ConfigPO> implements ConfigDao {
	private static final long serialVersionUID = 1661965983527190778L;

	public ConfigDaoImpl() {
		super(ConfigPO.class);
	}

	@Override
	public ConfigPO findByName(String key) {
		return findUniqueBy("key", key);
	}
	
}
