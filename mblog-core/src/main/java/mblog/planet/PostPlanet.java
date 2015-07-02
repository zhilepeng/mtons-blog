/**
 * 
 */
package mblog.planet;

import java.util.Collection;
import java.util.List;

import mtons.modules.pojos.Paging;
import mblog.data.Post;

/**
 * @author langhsu
 *
 */
public interface PostPlanet {
	Paging paging(Paging paging, String ord);
	Paging pagingByUserId(Paging paging, long uid);
	Post getPost(long id);
	void post(Post post);
	
	List<Post> findRecents(int maxResutls, long ignoreUserId);
	List<Post> findHots(int maxResutls, long ignoreUserId);
	
	void delete(long id, long authorId);
	void delete(Collection<Long> ids);
	
	boolean cacheFlush();
}
