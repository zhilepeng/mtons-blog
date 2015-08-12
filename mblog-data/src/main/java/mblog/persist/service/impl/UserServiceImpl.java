package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import mblog.data.AccountProfile;
import mblog.data.AuthMenu;
import mblog.data.User;
import mblog.data.UserFull;
import mblog.persist.dao.UserDao;
import mblog.persist.entity.AuthMenuPO;
import mblog.persist.entity.RolePO;
import mblog.persist.entity.UserExtendPO;
import mblog.persist.entity.UserPO;
import mblog.persist.service.UserService;
import mblog.persist.utils.BeanMapUtils;
import mtons.modules.lang.EntityStatus;
import mtons.modules.pojos.Paging;
import mtons.modules.utils.MD5Helper;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public AccountProfile login(String username, String password) {
		UserPO po = userDao.get(username);
		AccountProfile u = null;

		Assert.notNull(po, "账户不存在");

		if (StringUtils.equals(po.getPassword(), password)) {
			po.setLastLogin(Calendar.getInstance().getTime());

			u = BeanMapUtils.copyPassport(po);

			// FIXME: 兼容代码 1.3 之前版本的注册用户
			if (po.getExtend() == null) {
				// 保存扩展
				UserExtendPO extend = new UserExtendPO();
				extend.setUser(po);

				po.setExtend(extend);
			}
		}
		return u;
	}

	@Override
	@Transactional
	public void register(User user) {
		Assert.notNull(user, "Parameter user can not be null!");
		
		Assert.hasLength(user.getUsername(), "用户名不能为空!");
		Assert.hasLength(user.getPassword(), "密码不能为空!");
		
		UserPO check = userDao.get(user.getUsername());
		Assert.isNull(check, "用户名已经存在!");
		
		UserPO po = new UserPO();
		
		BeanUtils.copyProperties(user, po);
		
		Date current = Calendar.getInstance().getTime();
		po.setPassword(MD5Helper.md5(user.getPassword()));
		po.setStatus(EntityStatus.ENABLED);
		po.setCreated(current);
		po.setUpdated(current);
		
		// 保存扩展
		UserExtendPO extend = new UserExtendPO();
		extend.setUser(po);

		po.setExtend(extend);
		
		userDao.save(po);
	}

	@Override
	@Transactional
	public AccountProfile update(User user) {
		UserPO po = userDao.get(user.getId());
		if (null != po) {
			po.setEmail(user.getEmail());
			po.setName(user.getName());
		}
		
		return BeanMapUtils.copyPassport(po);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User get(long id) {
		UserPO po = userDao.get(id);
		User ret = null;
		if (po != null) {
			ret = BeanMapUtils.copy(po);
		}
		return ret;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User get(String username) {
		UserPO po = userDao.get(username);
		User ret = null;
		if (po != null) {
			ret = BeanMapUtils.copy(po);
		}
		return ret;
	}

	@Override
	@Transactional(readOnly = true)
	public UserFull getUserFull(long id) {
		UserPO po = userDao.get(id);
		UserFull ret;

		Assert.notNull(po, "用户数据获取失败");

		ret = new UserFull();
		BeanUtils.copyProperties(po, ret, BeanMapUtils.USER_IGNORE);

		if (po.getExtend() != null) {
			BeanUtils.copyProperties(po.getExtend(), ret);
		}
		return ret;
	}

	@Override
	@Transactional
	public AccountProfile updateAvatar(long id, String path) {
		UserPO po = userDao.get(id);
		if (po != null) {
			po.setAvatar(path);
			po.setUpdated(new Date());
		}
		return BeanMapUtils.copyPassport(po);
	}
	
	@Override
	@Transactional
	public void updatePassword(long id, String newPassword) {
		UserPO po = userDao.get(id);
		
		Assert.hasLength(newPassword, "密码不能为空!");
		
		if (null != po) {
			po.setPassword(MD5Helper.md5(newPassword));
		}
	}
	
	@Override
	@Transactional
	public void updatePassword(long id, String oldPassword, String newPassword) {
		UserPO po = userDao.get(id);
		
		Assert.hasLength(newPassword, "密码不能为空!");
		
		if (po != null) {
			Assert.isTrue(MD5Helper.md5(oldPassword).equals(po.getPassword()), "当前密码不正确");
			po.setPassword(MD5Helper.md5(newPassword));
		}
	}
	
	@Override
	@Transactional
	public void updateStatus(long id, int status) {
		UserPO po = userDao.get(id);
		
		if (po != null) {
			po.setStatus(status);
		}
	}
	
	@Override
	@Transactional
	public void updateRole(long id, int roleId) {
		UserPO po = userDao.get(id);
		
		if (po != null) {
			po.setRoleId(roleId);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public void paging(Paging paging, String key) {
		List<UserPO> list = userDao.paging(paging, key);
		List<User> rets = new ArrayList<>();
		
		for (UserPO po : list) {
			User u = BeanMapUtils.copy(po);
			rets.add(u);
		}
		paging.setResults(rets);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Long, User> findMapByIds(Set<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyMap();
		}
		List<UserPO> list = userDao.findByIds(ids);
		Map<Long, User> ret = new HashMap<>();

		list.forEach(po -> {
			ret.put(po.getId(), BeanMapUtils.copy(po));
		});
		return ret;
	}

	@Override
	public void updateExtend(User user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<AuthMenu> getMenuList(long id) {
		List<AuthMenu> menus = new ArrayList<AuthMenu>();
		UserPO userPO = userDao.get(id);
		List<RolePO> roles = userPO.getRoles();
		for(RolePO role : roles){
			List<AuthMenuPO> menuPOs = role.getAuthMenus();
			for(AuthMenuPO menuPO : menuPOs){
				AuthMenu menu = BeanMapUtils.copy(menuPO);
				if(!menus.contains(menu)){
					menus.add(menu);
				}
			}
		}
		return menus;
	}

}
