package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Role;
import mblog.persist.dao.RoleDao;
import mblog.persist.entity.RolePO;
import mblog.persist.service.RoleService;
import mblog.persist.utils.BeanMapUtils;
import mtons.modules.pojos.Paging;

public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional(readOnly = true)
	public void paging(Paging paging, String key) {
		List<RolePO> rolePOs = roleDao.paging(paging, key);
		List<Role> roles = new ArrayList<Role>();
		rolePOs.forEach(po -> {
			roles.add(BeanMapUtils.copy(po));
		});

		paging.setResults(roles);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Role get(Long id) {
		RolePO po = roleDao.get(id);
		Role role = BeanMapUtils.copy(po);
		return role;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void save(Role role){
		RolePO rolePO = new RolePO();
		BeanUtils.copyProperties(role, rolePO);
		roleDao.saveOrUpdate(rolePO);
	}

	@Override
	@Transactional(readOnly=false)
	public void delete(Long id) {
		roleDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Role> getAll() {
		List<RolePO> list = roleDao.list();
		List<Role> roles = new ArrayList<Role>();
		list.forEach(po -> {
			roles.add(BeanMapUtils.copy(po));
		});
		return roles;
	}

}
