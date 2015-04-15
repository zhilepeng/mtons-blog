/**
 * 
 */
package mblog.modules.account.utils;

import mblog.modules.account.persist.entity.UserPO;
import mblog.modules.account.pojos.User;
import mtons.modules.pojos.UserProfile;

import org.springframework.beans.BeanUtils;

/**
 * @author langhsu
 *
 */
public class AccountBeanUtils {
	private static String[] USER_IGNORE = new String[]{"password"};
	
	public static User copy(UserPO po) {
		if (po == null) {
			return null;
		}
		User ret = new User();
		BeanUtils.copyProperties(po, ret, USER_IGNORE);
		return ret;
	}
	
	public static UserProfile copyPassport(UserPO po) {
		UserProfile passport = new UserProfile(po.getId(), po.getUsername());
		passport.setName(po.getName());
		passport.setEmail(po.getEmail());
		passport.setAvatar(po.getAvatar());
		passport.setLastLogin(po.getLastLogin());
		passport.setStatus(po.getStatus());
		return passport;
	}
	
}
