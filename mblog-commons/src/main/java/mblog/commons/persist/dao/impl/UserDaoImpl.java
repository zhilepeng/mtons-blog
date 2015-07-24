/**
 * 
 */
package mblog.commons.persist.dao.impl;

import java.util.List;

import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import mblog.commons.persist.dao.UserDao;
import mblog.commons.persist.entity.UserPO;

/**
 * @author langhsu
 *
 */
public class UserDaoImpl extends DaoImpl<UserPO> implements UserDao {
	private static final long serialVersionUID = -3396151113305189145L;

	public UserDaoImpl() {
		super(UserPO.class);
	}
	
	@Override
	public UserPO get(String username) {
		return findUniqueBy("username", username);
	}

	@Override
	public List<UserPO> paging(Paging paging, String key) {
		PagingQuery<UserPO> q = pagingQuery(paging);
		if (StringUtils.isNotBlank(key)) {
			q.add(Restrictions.or(
				Restrictions.like("username", key, MatchMode.ANYWHERE),
				Restrictions.like("name", key, MatchMode.ANYWHERE)
			));
		}
		q.desc("id");
		return q.list();
	}

}
