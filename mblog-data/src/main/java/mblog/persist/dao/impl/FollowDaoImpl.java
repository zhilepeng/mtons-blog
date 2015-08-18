/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.dao.impl;

import mblog.persist.dao.FollowDao;
import mblog.persist.entity.FollowPO;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * @author langhsu
 *
 */
public class FollowDaoImpl extends DaoImpl<FollowPO> implements FollowDao {
	private static final long serialVersionUID = 2809599067409589271L;

	public FollowDaoImpl() {
		super(FollowPO.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public FollowPO checkFollow(long userId, long followId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("user.id", userId));
		c.add(Restrictions.eq("follow.id", followId));
		
		List<FollowPO> list = c.list();
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<FollowPO> following(Paging paging, long userId) {
		PagingQuery<FollowPO> q = pagingQuery(paging);
		q.add(Restrictions.eq("user.id", userId));
		q.desc("id");
		return q.list();
	}

	@Override
	public List<FollowPO> fans(Paging paging, long userId) {
		PagingQuery<FollowPO> q = pagingQuery(paging);
		q.add(Restrictions.eq("follow.id", userId));
		q.desc("id");
		return q.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkCrossFollow(long userId, long targetUserId) {
		String hql = "from mto_follows where user_id = :userId and follow_id = :followId and " +
				"user_id in (select follow_id from mto_follows where user_id = :followId)";
		Query q = createSQLQuery(hql);
		q.setLong("userId", userId);
		q.setLong("followId", targetUserId);
		
		q.setResultTransformer(Transformers.aliasToBean(FollowPO.class));

		List<FollowPO> list = q.list();
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int unfollow(long userId, long followId) {
		Query q = createSQLQuery("delete from mto_follows where user_id = :userId and follow_id = :followId");
		q.setLong("userId", userId);
		q.setLong("followId", followId);
		return q.executeUpdate();
	}
}
