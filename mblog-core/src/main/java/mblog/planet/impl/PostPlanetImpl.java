/**
 * 
 */
package mblog.planet.impl;

import java.util.Collection;
import java.util.List;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import mblog.planet.PostPlanet;
import mblog.data.Post;
import mblog.persist.service.PostService;

/**
 * @author langhsu
 * 
 */
public class PostPlanetImpl implements PostPlanet {
	@Autowired
	private PostService postsService;

	@Override
	@Cacheable(value = "postsCaches", key = "'list_' + #ord + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging paging(Paging paging, String ord) {
		postsService.paging(paging, ord);
		return paging;
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'uhome' + #uid + '_' + #paging.getPageNo()")
	public Paging pagingByUserId(Paging paging, long uid) {
		postsService.pagingByUserId(paging, uid);
		return paging;
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'view_' + #id")
	public Post getPost(long id) {
		return postsService.get(id);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void post(Post post) {
		postsService.post(post);
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'post_recents'")
	public List<Post> findRecents(int maxResutls, long ignoreUserId) {
		return postsService.findRecents(maxResutls, ignoreUserId);
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'post_hots'")
	public List<Post> findHots(int maxResutls, long ignoreUserId) {
		return postsService.findHots(maxResutls, ignoreUserId);
	}
	
	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(long id, long authorId) {
		postsService.delete(id, authorId);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(Collection<Long> ids) {
		for (Long id : ids) {
			postsService.delete(id);
		}
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public boolean cacheFlush() {
		return Boolean.TRUE;
	}

}
