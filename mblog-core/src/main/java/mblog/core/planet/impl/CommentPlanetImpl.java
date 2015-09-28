/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.planet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import mblog.core.data.Comment;
import mblog.core.persist.service.CommentService;
import mblog.core.planet.CommentPlanet;
import mtons.modules.pojos.Paging;

/**
 * 评论
 * @author langhsu
 *
 */
@Service
public class CommentPlanetImpl implements CommentPlanet {
	@Autowired
	private CommentService commentService;
	
	@Override
	@Cacheable(value = "commentsCaches", key = "'lt_' + #toId + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging paging(Paging paging, long toId) {
		commentService.paging(paging, toId);
		return paging;
	}

	@Override
	@Cacheable(value = "commentsCaches", key = "'lth_' + #authorId + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging paging4Home(Paging paging, long authorId) {
		commentService.paging4Home(paging, authorId);
		return paging;
	}

	@Override
	@CacheEvict(value = "commentsCaches", allEntries = true)
	public long post(Comment comment) {
		return commentService.post(comment);
	}

	@Override
	@CacheEvict(value = "commentsCaches", allEntries = true)
	public void delete(List<Long> ids) {
		commentService.delete(ids);
	}

	@Override
	@CacheEvict(value = "commentsCaches", allEntries = true)
	public void delete(long id, long authorId) {
		commentService.delete(id, authorId);
	}

}
