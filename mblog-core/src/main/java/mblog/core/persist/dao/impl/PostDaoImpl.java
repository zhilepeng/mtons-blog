/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.persist.dao.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import mblog.base.lang.Consts;
import mblog.base.lang.EnumPrivacy;
import mblog.core.persist.dao.PostDao;
import mblog.core.persist.entity.PostPO;
import mtons.modules.annotation.Repository;
import mtons.modules.lang.Const;
import mtons.modules.persist.impl.BaseRepositoryImpl;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
@Repository(entity = PostPO.class)
public class PostDaoImpl extends BaseRepositoryImpl<PostPO> implements PostDao {
	private static final long serialVersionUID = -8144066308316359853L;
	
	@Override
	public Session getSession() {
		return super.session();
	}

	@Override
	public List<PostPO> paging(Paging paging, int group, String ord) {
		PagingQuery<PostPO> q = pagingQuery(paging);
		
		if (group > Const.ZERO) {
			q.add(Restrictions.eq("group", group));
		}
		
		if (Consts.order.HOTTEST.equals(ord)) {
			q.desc("views");
		}

		q.add(Restrictions.eq("privacy", EnumPrivacy.OPEN.getIndex()));
		q.desc("featured");
		q.desc("created");
		return q.list();
	}

	@Override
	public List<PostPO> paging4Admin(Paging paging, long id, String title, int group) {
		PagingQuery<PostPO> q = pagingQuery(paging);

		if (group > Const.ZERO) {
			q.add(Restrictions.eq("group", group));
		}

		if (StringUtils.isNotBlank(title)) {
			q.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		}

		if (id > Const.ZERO) {
			q.add(Restrictions.eq("id", id));
		}
		q.desc("featured");
		q.desc("created");
		return q.list();
	}

	@Override
	public List<PostPO> pagingByAuthorId(Paging paging, long userId, EnumPrivacy privacy) {
		PagingQuery<PostPO> q = pagingQuery(paging);
		if (userId > 0) {
			q.add(Restrictions.eq("authorId", userId));
		}

		if (privacy != null && privacy != EnumPrivacy.ALL) {
			q.add(Restrictions.eq("privacy", privacy.getIndex()));
		}
		q.desc("created");
		return q.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PostPO> findLatests(int maxResults, long ignoreUserId) {
		Criteria c = createCriteria();
		
		c.add(Restrictions.neOrIsNotNull("title", ""));
		if (ignoreUserId > 0) {
			c.add(Restrictions.ne("authorId", ignoreUserId));
		}
		c.addOrder(Order.desc("created"));
		c.setMaxResults(maxResults);
		return c.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PostPO> findHots(int maxResults, long ignoreUserId) {
		Criteria c = createCriteria();
		
		c.add(Restrictions.neOrIsNotNull("title", ""));
//		if (ignoreUserId > 0) {
//			q.add(Restrictions.ne("author.id", ignoreUserId));
//		}
		c.addOrder(Order.desc("views"));
		c.setMaxResults(maxResults);
		return c.list();
	}

	@Override
	public List<PostPO> findByIds(Collection<Long> ids) {
		return find(Restrictions.in("id", ids));
	}

	@Override
	public int maxFeatured() {
		Criteria c = createCriteria();
		c.setProjection(Projections.max("featured"));
		Number num = (Number) c.uniqueResult();
		if (num != null) {
			return num.intValue();
		}
		return 0;
	}

}
