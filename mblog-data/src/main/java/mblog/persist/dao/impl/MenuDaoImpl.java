/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import mblog.persist.dao.MenuDao;
import mblog.persist.entity.MenuPO;
import mtons.modules.persist.impl.DaoImpl;

/**
 * @author langhsu
 *
 */
public class MenuDaoImpl extends DaoImpl<MenuPO> implements MenuDao {
	private static final long serialVersionUID = 2564594993409371310L;

	public MenuDaoImpl() {
		super(MenuPO.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MenuPO> findAll() {
		Criteria c = createCriteria();
		c.addOrder(Order.desc("weight"));
		return c.list();
	}
}
