/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.planet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mblog.data.Comment;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public interface CommentPlanet {
	Paging paging(Paging paging, long toId);

	Paging paging4Home(Paging paging, long authoId);

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
