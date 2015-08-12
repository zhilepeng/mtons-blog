package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mblog.data.AuthMenu;
import mblog.persist.dao.AuthMenuDao;
import mblog.persist.entity.AuthMenuPO;
import mblog.persist.service.AuthMenuService;
import mblog.persist.utils.BeanMapUtils;

public class AuthMenuServiceImpl implements AuthMenuService {
	
	@Autowired
	private AuthMenuDao authMenuDao;

	@Override
	public List<AuthMenu> findByParentId(long parentId) {
		// TODO Auto-generated method stub
		List<AuthMenu> authMenus = new ArrayList<AuthMenu>();
		List<AuthMenuPO> authMenuPOs = authMenuDao.findByParentId(parentId);
		if(authMenuPOs!=null){
			for(AuthMenuPO po : authMenuPOs){
				AuthMenu authMenu = BeanMapUtils.copy(po);
				authMenus.add(authMenu);
			}
		}
		return authMenus;
	}

}
