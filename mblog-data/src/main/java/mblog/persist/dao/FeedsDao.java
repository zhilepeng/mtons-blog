/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao;

import mblog.data.Feeds;
import mblog.persist.entity.FeedsPO;
import mtons.modules.persist.Dao;
import mtons.modules.pojos.Paging;

import java.util.List;

/**
 * @author langhsu
 *
 */
public interface FeedsDao extends Dao<FeedsPO> {
	/**
	 * 添加动态, 同时会分发给粉丝
	 * 
	 * @param feeds
	 * @return
	 */
	int batchAdd(Feeds feeds);
	
	int deleteByAuthorId(long ownId, long authorId);
	
	List<FeedsPO> findUserFeeds(Paging paging, long ownId, long authorId, long ignoreId);
	
	void deleteByTarget(long postId);
}
