/**
 * 
 */
package mblog.planet;

import java.util.Collection;
import java.util.List;

import mtons.modules.pojos.Paging;
import mblog.data.Post;

/**
 * 保卫层(cache), 根据业务需求调用
 * 
 * @author langhsu
 *
 */
public interface PostPlanet {
	/**
	 * 分页查询文章
	 * 
	 * @param paging
	 * @param group
	 * @param ord
	 * @return
	 */
	Paging paging(Paging paging, int group, String ord);
	
	/**
	 * 查询指定用户的文章
	 * 
	 * @param paging
	 * @param uid
	 * @return
	 */
	Paging pagingByUserId(Paging paging, long uid);
	
	/**
	 * 走廊查询 - (只返回一张图片)
	 * 
	 * @param paging
	 * @param group
	 * @param ord
	 * @return
	 */
	Paging gallery(Paging paging, int group, String ord);
	
	/**
	 * 从缓存中获取文章
	 * 
	 * @param id
	 * @return
	 */
	Post getPost(long id);
	
	/**
	 * 发布文章, 并清除缓存
	 * 
	 * @param post
	 */
	void post(Post post);
	
	List<Post> findRecents(int maxResutls, long ignoreUserId);
	List<Post> findHots(int maxResutls, long ignoreUserId);
	
	void delete(long id, long authorId);
	void delete(Collection<Long> ids);
	
	boolean cacheFlush();
}
