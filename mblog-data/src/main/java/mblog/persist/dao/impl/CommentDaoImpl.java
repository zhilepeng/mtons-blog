/**
 * 
 */
package mblog.persist.dao.impl;

import mblog.persist.dao.CommentDao;
import mblog.persist.entity.CommentPO;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public class CommentDaoImpl extends DaoImpl<CommentPO> implements CommentDao {
	private static final long serialVersionUID = 1023552695901348149L;

	public CommentDaoImpl() {
		super(CommentPO.class);
	}

	@Override
	public List<CommentPO> paging(Paging paging, String key) {
		PagingQuery<CommentPO> q = pagingQuery(paging);
		if (StringUtils.isNotBlank(key)) {
			q.add(Restrictions.like("content", key, MatchMode.ANYWHERE));
		}
		q.desc("created");
		return q.list();
	}
	
	@Override
	public List<CommentPO> paging(Paging paging,long toId, boolean desc) {
		PagingQuery<CommentPO> q = pagingQuery(paging);
		q.add(Restrictions.eq("toId", toId));
		q.add(Restrictions.eq("status", 0));
		
		if (desc) {
			q.desc("created");
		} else {
			q.asc("created");
		}
		return q.list();
	}

	@Override
	public List<CommentPO> findByIds(Set<Long> ids) {
		return find(Restrictions.in("id", ids));
	}

	@Override
	public int deleteByIds(Collection<Long> ids) {
		Query query = createQuery("delete from CommentPO where id in (:ids)");
		query.setParameterList("ids", ids);
		return query.executeUpdate();
	}


}
