/**
 * 
 */
package mblog.persist.service;

import java.util.List;
import java.util.Map;

import mtons.modules.pojos.Paging;
import mblog.data.Comment;

/**
 * @author langhsu
 *
 */
public interface CommentService {
	void paging4Admin(Paging paging, String key);
	
	/**
	 * 查询评论列表
	 * @param page
	 * @param toId
	 */
	void paging(Paging paging, long toId);

	Map<Long, Comment> findByIds(List<Long> ids);
	
	/**
	 * 发表评论
	 * @param comment
	 * @return
	 */
	long post(Comment comment);
	
	void delete(List<Long> ids);
}
