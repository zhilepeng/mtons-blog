/**
 * 
 */
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Comment;
import mblog.lang.Consts;
import mblog.persist.dao.CommentDao;
import mblog.persist.dao.UserDao;
import mblog.persist.entity.CommentPO;
import mblog.persist.service.CommentService;
import mblog.utils.BeanMapUtils;

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
		List<CommentPO> list = commentDao.paging(paging, toId, Consts.COMMENT_TOP_PID, true);
		
		List<Comment> rets = new ArrayList<Comment>();
		for (CommentPO po : list) {
			Comment c = BeanMapUtils.copy(po);
			
			// 加载回复
			c.setChildren(findChidren(toId, c.getId()));
			
			rets.add(c);
		}
		
		paging.setResults(rets);
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
	
	private List<Comment> findChidren(long toId, long pid) {
		List<Comment> rets = new ArrayList<Comment>();
		
		if (pid == 0) {
			return rets;
		}
		
		Paging paging = new Paging(0);
		List<CommentPO> list = commentDao.paging(paging, toId, pid, false);
		
		for (CommentPO po : list) {
			Comment c = BeanMapUtils.copy(po);
			rets.add(c);
		}
		return rets;
	}

}
