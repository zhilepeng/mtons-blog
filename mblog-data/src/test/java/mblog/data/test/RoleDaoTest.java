package mblog.data.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mblog.persist.dao.RoleDao;
import mblog.persist.entity.RolePO;
import mtons.modules.pojos.Paging;

public class RoleDaoTest extends SpringTransactionalContextTests {
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void testRoleList(){
		Paging paging = new Paging();
		paging.setCount(true);
		paging.setMaxResults(10);
		paging.setPageNo(1);
		List<RolePO> roles = roleDao.paging(paging, null);
		Assert.assertNotNull(roles);
	}

}
