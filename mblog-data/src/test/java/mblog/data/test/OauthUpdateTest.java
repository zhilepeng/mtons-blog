/**
 * 
 */
package mblog.data.test;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import mblog.persist.dao.OpenOauthDao;
import mblog.persist.entity.OpenOauthPO;
import mblog.persist.entity.UserPO;
import mblog.persist.service.UserService;
import mtons.modules.utils.MD5Helper;

/**
 * 第三方登录用户-升级代码
 * 
 * @author langhsu
 *
 */
public class OauthUpdateTest extends SpringTransactionalContextTests {
	@Autowired
    private OpenOauthDao openOauthDao;
	@Autowired
	private UserService userService;
	
//	@Test
//	public void testUpgrade(){
//		List<OpenOauthPO> all = openOauthDao.list();
//		
//		for (OpenOauthPO po : all) {
//			userService.updatePassword(po.getUserId(), MD5Helper.md5(po.getOauthUserId()));
//			System.out.println(po.getUserId());
//		}
//	}
}
