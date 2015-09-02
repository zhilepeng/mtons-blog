/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import mblog.data.Menu;
import mblog.persist.dao.MenuDao;
import mblog.persist.entity.MenuPO;
import mblog.persist.service.MenuService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
