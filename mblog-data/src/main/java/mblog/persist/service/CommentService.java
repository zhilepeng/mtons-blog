/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mtons.modules.pojos.Paging;
import mblog.data.Comment;

/**
 * @author langhsu
 *
 */
public interface CommentService {
	void paging4Admin(Paging paging, String key);

	void paging4Home(Paging paging, long authorId);

	/**
	 * 查询评论列表
	 * @param paging
	 * @param toId
	 */
	void paging(Paging paging, long toId);

	Map<Long, Comment> findByIds(Set<Long> ids);
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	long post(Comment comment);
	
	void delete(List<Long> ids);

	/**
	 * 带作者验证的删除
	 * @param id
	 * @param authorId
	 */
	void delete(long id, long authorId);
}
