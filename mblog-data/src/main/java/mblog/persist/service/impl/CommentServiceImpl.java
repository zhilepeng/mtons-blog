/**
 * 
 */
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Comment;
import mblog.persist.dao.CommentDao;
import mblog.persist.dao.UserDao;
import mblog.persist.entity.CommentPO;
import mblog.persist.service.CommentService;
import mblog.persist.utils.BeanMapUtils;
import mtons.modules.pojos.Paging;

/**
 * @author langhsu
 *
 */
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public void paging4Admin(Paging paging, String key) {
		List<CommentPO> list = commentDao.paging(paging, key);
		List<Comment> rets = new ArrayList<Comment>();
		for (CommentPO po : list) {
			rets.add(BeanMapUtils.copy(po));
		}
		paging.setResults(rets);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void paging(Paging paging, long toId) {
		List<CommentPO> list = commentDao.paging(paging, toId, true);
		
		List<Comment> rets = new ArrayList<Comment>();
		List<Long> pids = new ArrayList<>();

		for (CommentPO po : list) {
			Comment c = BeanMapUtils.copy(po);

			if (c.getPid() > 0) {
				pids.add(c.getPid());
			}
			rets.add(c);
		}

		if (!pids.isEmpty()) {
			Map<Long, Comment> pm = findByIds(pids);

			rets.forEach(c -> {
				if (c.getPid() > 0) {
					c.setParent(pm.get(c.getPid()));
				}
			});
		}
		paging.setResults(rets);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Long, Comment> findByIds(List<Long> ids) {
		List<CommentPO> list = commentDao.findByIds(ids);
		Map<Long, Comment> ret = new HashMap<Long, Comment>();

		list.forEach(po -> ret.put(po.getId(), BeanMapUtils.copy(po)));
		return ret;
	}

	@Override
	@Transactional
	public long post(Comment comment) {
		CommentPO po = new CommentPO();
		
		po.setAuthor(userDao.get(comment.getAuthorId()));
		po.setToId(comment.getToId());
		po.setContent(comment.getContent());
		po.setCreated(new Date());
		po.setPid(comment.getPid());
		commentDao.save(po);
		
		return po.getId();
	}

	@Override
	@Transactional
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			commentDao.deleteById(id);
		}
	}

}
