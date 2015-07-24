/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.commons.persist.dao;

import java.util.List;

import mblog.commons.persist.entity.MenuPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface MenuDao extends Dao<MenuPO> {
	List<MenuPO> findAll();
}
