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

import mblog.commons.persist.dao.CommentDao;
import mblog.commons.persist.entity.CommentPO;

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
	public List<CommentPO> paging(Paging paging,long toId, long pid, boolean desc) {
		PagingQuery<CommentPO> q = pagingQuery(paging);
		q.add(Restrictions.eq("toId", toId));
		q.add(Restrictions.eq("status", 0));
		
		q.add(Restrictions.eq("pid", pid));
		
		if (desc) {
			q.desc("created");
		} else {
			q.asc("created");
		}
		return q.list();
	}
	
}
