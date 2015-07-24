/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.commons.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.commons.data.Menu;
import mblog.commons.persist.dao.MenuDao;
import mblog.commons.persist.entity.MenuPO;
import mblog.commons.persist.service.MenuService;

/**
 * @author langhsu
 *
 */
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findAll() {
		List<MenuPO> list = menuDao.findAll();
		List<Menu> rets = new ArrayList<Menu>();
		for (MenuPO po : list) {
			Menu m = new Menu();
			BeanUtils.copyProperties(po, m);
			rets.add(m);
		}
		return rets;
	}
	
}
