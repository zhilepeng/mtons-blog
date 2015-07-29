/**
 * 
 */
package mblog.persist.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mblog.data.AccountProfile;
import mblog.data.AuthMenu;
import mblog.data.User;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public interface UserService {
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	AccountProfile login(String username, String password);
	
	/**
	 * 注册
	 * @param user
	 */
	void register(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	AccountProfile update(User user);
	
	/**
	 * 查询单个用户
	 * @param id
	 * @return
	 */
	User get(long id);
	
	User get(String username);
	
	/**
	 * 修改头像
	 * @param id
	 * @param path
	 * @return
	 */
	AccountProfile updateAvatar(long id, String path);
	
	/**
	 * 修改密码
	 * @param id
	 * @param newPassword
	 */
	void updatePassword(long id, String newPassword);
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 */
	void updatePassword(long id, String oldPassword, String newPassword);
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param status
	 */
	void updateStatus(long id, int status);
	
	void updateRole(long id, int roleId);
	
	/**
	 * 分页查询
	 * @param paging
	 */
	void paging(Paging paging, String key);

	Map<Long, User> findMapByIds(Set<Long> ids);
	
	void updateExtend(User user);
	
	List<AuthMenu> getMenuList(long id);
}