package mblog.persist.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import mblog.persist.dao.AuthMenuDao;
import mblog.persist.dao.MenuDao;
import mblog.persist.entity.AuthMenuPO;
import mblog.persist.entity.MenuPO;
import mtons.modules.persist.impl.DaoImpl;

public class AuthMenuDaoImpl extends DaoImpl<AuthMenuPO> implements AuthMenuDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthMenuDaoImpl(){
		super(AuthMenuPO.class);
	}




}
