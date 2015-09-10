package mblog.persist.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import mblog.persist.dao.RoleDao;
import mblog.persist.entity.RolePO;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;

public class RoleDaoImpl extends DaoImpl<RolePO> implements RoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleDaoImpl() {
		super(RolePO.class);
	}
	
	@Override
	public List<RolePO> paging(Paging paging, String key) {
		PagingQuery<RolePO> q = pagingQuery(paging);
		if (StringUtils.isNotBlank(key)) {
			q.add(
				Restrictions.like("name", key, MatchMode.ANYWHERE)
			);
		}
		q.desc("id");
		return q.list();
	}

	

}
