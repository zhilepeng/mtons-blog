package mblog.persist.dao.impl;

import java.util.List;

import org.hibernate.Query;

import mblog.persist.dao.AuthMenuDao;
import mblog.persist.entity.AuthMenuPO;
import mtons.modules.persist.impl.DaoImpl;

public class AuthMenuDaoImpl extends DaoImpl<AuthMenuPO> implements AuthMenuDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthMenuDaoImpl(){
		super(AuthMenuPO.class);
	}

	@Override
	public List<AuthMenuPO> findByParentId(Long parentId) {
		// TODO Auto-generated method stub
		Query query = createQuery("from AuthMenuPO am where am.parent.id = ? order by am.sort");
		query.setLong(0, parentId);
		return query.list();
	}

}
