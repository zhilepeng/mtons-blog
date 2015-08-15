/**
 * 
 */
package test;

import junit.framework.TestCase;
import mblog.data.User;
import mblog.persist.entity.UserPO;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author langhsu
 *
 */
public class UserServiceTest extends TestCase {
	
	@Test
	public void testReg() {
		User user = new User();
		Assert.notNull(user, "Parameter user can not be null!");
		
		UserPO check = null;
		Assert.isNull(check, "Username already exists!");

		Assert.hasLength(null, "缺少必要的参数");
		System.out.println("end");
	}
}
