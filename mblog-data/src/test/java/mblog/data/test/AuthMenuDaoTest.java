package mblog.data.test;

import java.util.List;

import mblog.data.AuthMenu;
import mblog.persist.service.AuthMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mblog.persist.dao.AuthMenuDao;
import mblog.persist.entity.AuthMenuPO;

public class AuthMenuDaoTest extends SpringTransactionalContextTests{
	
	@Autowired
	private AuthMenuDao authMenuDao;

	@Autowired
	private AuthMenuService authMenuService;
	
	@Test
	public void testFindByParentId(){
		List<AuthMenuPO> list = authMenuDao.findByParentId(2L);
		System.out.println(list);
	}

	@Test
	public void testTree(){
		List<AuthMenu> list = authMenuService.tree(1L);
		System.out.println(list);
	}

}
