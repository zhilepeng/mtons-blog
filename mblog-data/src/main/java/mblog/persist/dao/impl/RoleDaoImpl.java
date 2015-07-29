package mblog.persist.dao.impl;

import mblog.persist.dao.RoleDao;
import mblog.persist.entity.RolePO;
import mtons.modules.persist.impl.DaoImpl;

public class RoleDaoImpl extends DaoImpl<RolePO> implements RoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleDaoImpl() {
		super(RolePO.class);
	}

	

}
