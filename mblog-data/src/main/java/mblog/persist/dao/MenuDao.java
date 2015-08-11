/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.dao;

import java.util.List;

import mblog.persist.entity.MenuPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu
 *
 */
public interface MenuDao extends Dao<MenuPO> {
	List<MenuPO> findAll();
	
}
