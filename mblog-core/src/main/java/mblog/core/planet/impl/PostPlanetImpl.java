/**
 * 
 */
package mblog.core.planet.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mblog.core.planet.PostPlanet;
import mblog.data.Attach;
import mblog.data.Post;
import mblog.persist.service.AttachService;
import mblog.persist.service.PostService;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author langhsu
 * 
 */
public class PostPlanetImpl implements PostPlanet {
	@Autowired
	private PostService postService;
	@Autowired
	private AttachService attachService;
	
	@Override
	@Cacheable(value = "postsCaches", key = "'list_' + #group + #ord + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging paging(Paging paging, int group, String ord) {
		postService.paging(paging, group, ord, true);
		return paging;
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'uhome' + #uid + '_' + #paging.getPageNo()")
	public Paging pagingByUserId(Paging paging, long uid) {
		postService.pagingByUserId(paging, uid);
		return paging;
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'gallery_' + #group + #ord + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging gallery(Paging paging, int group, String ord) {
		postService.paging(paging, group, ord, false);
		
		// 查询图片
		List<Post> results = (List<Post>) paging.getResults();
		List<Long> imageIds = new ArrayList<Long>();
		
		for (Post p : results) {
			if (p.getLastImageId() > 0) {
				imageIds.add(p.getLastImageId());
			}
		}
		
		Map<Long, Attach> ats = attachService.findByIds(imageIds);
		
		for (Post p : results) {
			if (p.getLastImageId() > 0) {
				Attach a = ats.get(p.getLastImageId());
				p.setAlbums(Collections.singletonList(a));
			}
		}
		return paging;
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'view_' + #id")
	public Post getPost(long id) {
		return postService.get(id);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void post(Post post) {
		postService.post(post);
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'post_recents'")
	public List<Post> findRecents(int maxResutls, long ignoreUserId) {
		return postService.findRecents(maxResutls, ignoreUserId);
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'post_hots'")
	public List<Post> findHots(int maxResutls, long ignoreUserId) {
		return postService.findHots(maxResutls, ignoreUserId);
	}
	
	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(long id, long authorId) {
		postService.delete(id, authorId);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(Collection<Long> ids) {
		for (Long id : ids) {
			postService.delete(id);
		}
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public boolean cacheFlush() {
		return Boolean.TRUE;
	}

}
