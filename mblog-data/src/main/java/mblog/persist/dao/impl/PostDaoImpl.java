/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.dao.impl;

import java.util.Collection;
import java.util.List;

import mblog.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import mblog.lang.Consts;
import mblog.lang.EnumPrivacy;
import mblog.persist.dao.PostDao;
import mblog.persist.entity.PostPO;
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
	public List<PostPO> findLatests(int maxResutls, long ignoreUserId) {
		TopQuery<PostPO> q = topQuery(maxResutls);
		q.add(Restrictions.neOrIsNotNull("title", ""));
		if (ignoreUserId > 0) {
			q.add(Restrictions.ne("authorId", ignoreUserId));
		}
		q.desc("created");
		return q.list();
	}
	
	@Override
	public List<PostPO> findHots(int maxResutls, long ignoreUserId) {
		TopQuery<PostPO> q = topQuery(maxResutls);
		q.add(Restrictions.neOrIsNotNull("title", ""));
//		if (ignoreUserId > 0) {
//			q.add(Restrictions.ne("author.id", ignoreUserId));
//		}
		q.desc("views");
		return q.list();
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
