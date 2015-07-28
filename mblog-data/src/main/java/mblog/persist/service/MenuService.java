/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.service;

import java.util.List;

import mblog.data.Menu;

/**
 * TODO: 暂时添加修改都在数据库操作
 * 
 * @author langhsu
 *
 */
public interface MenuService {
	List<Menu> findAll();
}
