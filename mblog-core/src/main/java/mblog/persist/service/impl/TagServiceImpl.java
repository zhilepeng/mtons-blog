/**
 * 
 */
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mtons.modules.pojos.Paging;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Post;
import mblog.data.Tag;
import mblog.lang.Consts;
import mblog.persist.dao.TagDao;
import mblog.persist.entity.TagPO;
import mblog.persist.service.PostService;
import mblog.persist.service.TagService;
import mblog.utils.BeanMapUtils;

/**
 * @author langhsu
 *
 */
public class TagServiceImpl implements TagService {
	@Autowired
	private TagDao tagDao;
	@Autowired
	private PostService postService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tag> topTags(int maxResutls, boolean loadPost) {
		List<TagPO> list = tagDao.tops(maxResutls);
		List<Tag> rets = new ArrayList<Tag>();
		
		Set<Long> postIds = new HashSet<Long>();
		for (TagPO po : list) {
			rets.add(BeanMapUtils.copy(po));
			postIds.add(po.getLastPostId());
		}
		
		if (loadPost && postIds.size() > 0) {
			Map<Long, Post> posts = postService.findByIds(postIds);
			
			for (Tag t : rets) {
				Post p = posts.get(t.getLastPostId());
				t.setPost(p);
			}
		}
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Tag get(long id) {
		TagPO po = tagDao.get(id);
		Tag ret = null;
		if (po != null) {
			ret = BeanMapUtils.copy(po);
		}
		return ret;
	}
	
	@Override
	@Transactional
	public void batchPost(List<Tag> tags) {
		if (tags == null || tags.size() == 0) {
			return;
		}
		
		for (Tag t : tags) {
			if (StringUtils.isBlank(t.getName())) {
				continue;
			}
			TagPO po = tagDao.getByName(t.getName());
			if (po != null) {
				
				// 如果不锁定则更新文章ID
				if (po.getLocked() != Consts.status_locked) {
					po.setLastPostId(t.getLastPostId());
				}
				po.setPosts(po.getPosts() + 1);
			} else {
				po = new TagPO();
				BeanUtils.copyProperties(t, po);
				tagDao.save(po);
			}
		}
	}

	@Override
	@Transactional
	public void identityHots(String name) {
		TagPO po = tagDao.getByName(name);
		if (po != null) {
			po.setHots(po.getHots() + Consts.IDENTITY_STEP);
		}
	}

	@Override
	@Transactional
	public void identityHots(long id) {
		TagPO po = tagDao.get(id);
		if (po != null) {
			po.setHots(po.getHots() + Consts.IDENTITY_STEP);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public void paging(Paging paging, String key, String order) {
		List<TagPO> list = tagDao.paging(paging, key, order);
		List<Tag> rets = new ArrayList<Tag>();
		
		Set<Long> postIds = new HashSet<Long>();
		for (TagPO po : list) {
			rets.add(BeanMapUtils.copy(po));
			postIds.add(po.getLastPostId());
		}
		paging.setResults(rets);
	}

	@Override
	@Transactional
	public void updateFeatured(long id, int status) {
		TagPO po = tagDao.get(id);
		if (po != null) {
			if (po.getFeatured() == Consts.status_normal) {
				po.setFeatured(Consts.status_featured);
			} else {
				po.setFeatured(Consts.status_normal);
			}
		}
	}
	
	@Override
	@Transactional
	public void updateLock(long id, int status) {
		TagPO po = tagDao.get(id);
		if (po != null) {
			po.setLocked(status);
		}
	}

	@Override
	@Transactional
	public void update(Tag tag) {
		TagPO po = tagDao.get(tag.getId());
		
		if (po != null) {
			po.setLastPostId(tag.getLastPostId());
			po.setLocked(tag.getLocked());
		}
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		tagDao.deleteById(id);
	}

}
