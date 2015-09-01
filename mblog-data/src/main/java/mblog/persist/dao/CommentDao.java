/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao;

import mblog.persist.entity.CommentPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public interface CommentDao extends Dao<CommentPO> {
	List<CommentPO> paging(Paging paging, String key);
	List<CommentPO> paging(Paging paging, long toId, long authorId, boolean desc);
	List<CommentPO> findByIds(Set<Long> ids);

	int deleteByIds(Collection<Long> ids);
}
