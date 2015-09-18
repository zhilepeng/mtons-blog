/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.planet.impl;

import java.util.*;

import mblog.data.Attach;
import mblog.data.Post;
import mblog.extend.event.FeedsEvent;
import mblog.extend.planet.PostPlanet;
import mblog.extend.upload.FileRepo;
import mblog.lang.EnumPrivacy;
import mblog.persist.service.AttachService;
import mblog.persist.service.FeedsService;
import mblog.persist.service.PostService;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;

/**
 * @author langhsu
 * 
 */
public class PostPlanetImpl implements PostPlanet {
	@Autowired
	private PostService postService;
	@Autowired
	private AttachService attachService;
	@Autowired
	private FeedsService feedsService;
	@Autowired
	private FileRepo fileRepo;
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 分页查询文章, 带缓存
	 * - 缓存key规则: list_分组ID排序方式_页码_每页条数
	 * @param paging
	 * @param group
	 * @param ord
	 * @return
	 */
	@Override
	@Cacheable(value = "postsCaches", key = "'list_' + #group + #ord + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging paging(Paging paging, int group, String ord) {
		postService.paging(paging, group, ord, true);
		return paging;
	}
	
	@Override
	@Cacheable(value = "postsCaches", key = "'uhome' + #uid + '_' + #privacy.getIndex() + '_' + #paging.getPageNo()")
	public Paging pagingByAuthorId(Paging paging, long uid, EnumPrivacy privacy) {
		postService.pagingByAuthorId(paging, uid, privacy);
		return paging;
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'gallery_' + #group + #ord + '_' + #paging.getPageNo() + '_' + #paging.getMaxResults()")
	public Paging gallery(Paging paging, int group, String ord) {
		postService.paging(paging, group, ord, false);
		
		// 查询图片, 这里只加载文章的最后一张图片
		List<Post> results = (List<Post>) paging.getResults();
		Set<Long> imageIds = new HashSet<Long>();

		results.forEach(p -> {
			if (p.getLastImageId() > 0) {
				imageIds.add(p.getLastImageId());
			}
		});

		if (!imageIds.isEmpty()) {
			Map<Long, Attach> ats = attachService.findByIds(imageIds);

			results.forEach(p -> {
				if (p.getLastImageId() > 0) {
					p.setAlbum(ats.get(p.getLastImageId()));
				}
			});
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
		long id = postService.post(post);

		FeedsEvent event = new FeedsEvent("feedsEvent");
		event.setPostId(id);
		event.setAuthorId(post.getAuthorId());
		event.setPrivacy(post.getPrivacy());
		applicationContext.publishEvent(event);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void updateFeatured(long id, int featured) {
		postService.updateFeatured(id, featured);
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'post_recents'")
	public List<Post> findRecents(int maxResutls, long ignoreUserId) {
		return postService.findLatests(maxResutls, ignoreUserId);
	}

	@Override
	@Cacheable(value = "postsCaches", key = "'post_hots'")
	public List<Post> findHots(int maxResutls, long ignoreUserId) {
		return postService.findHots(maxResutls, ignoreUserId);
	}
	
	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(long id, long authorId) {
		List<Attach> atts = attachService.findByTarget(id);
		postService.delete(id, authorId);

		// 时刻保持清洁, 物理删除图片
		if (!atts.isEmpty()) {
			atts.forEach(a -> {
				fileRepo.deleteFile(a.getPreview());
				fileRepo.deleteFile(a.getOriginal());
				fileRepo.deleteFile(a.getScreenshot());
			});
		}

		feedsService.deleteByTarget(id);
	}

	@Override
	@CacheEvict(value = "postsCaches", allEntries = true)
	public void delete(Collection<Long> ids) {
		for (Long id : ids) {
			List<Attach> atts = attachService.findByTarget(id);
			postService.delete(id);

			// 时刻保持清洁, 物理删除图片
			if (!atts.isEmpty()) {
				atts.forEach(a -> {
					fileRepo.deleteFile(a.getPreview());
					fileRepo.deleteFile(a.getOriginal());
					fileRepo.deleteFile(a.getScreenshot());
				});
			}

			feedsService.deleteByTarget(id);
		}
	}

	@Override
	@CacheEvict(value = "postsCaches", key = "'view_' + #postId")
	public void favor(long userId, long postId) {
		postService.favor(userId, postId);
	}

	@Override
	@CacheEvict(value = "postsCaches", key = "'view_' + #postId")
	public void unfavor(long userId, long postId) {
		postService.unfavor(userId, postId);
	}

}
