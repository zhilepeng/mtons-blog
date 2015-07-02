/**
 * 
 */
package mblog.persist.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mtons.modules.lang.EntityStatus;
import mtons.modules.pojos.Paging;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import mblog.data.Attach;
import mblog.data.Post;
import mblog.data.Tag;
import mblog.lang.Consts;
import mblog.persist.dao.AttachDao;
import mblog.persist.dao.PostDao;
import mblog.persist.dao.UserDao;
import mblog.persist.entity.PostPO;
import mblog.persist.service.AttachService;
import mblog.persist.service.PostService;
import mblog.persist.service.TagService;
import mblog.utils.BeanMapUtils;
import mblog.utils.PreviewTextUtils;

/**
 * @author langhsu
 *
 */
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AttachService attachService;
	@Autowired
	private TagService tagService;
	
	@Override
	@Transactional(readOnly = true)
	public void paging(Paging paging, String ord) {
		List<PostPO> list = postDao.paging(paging, ord);
		List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			rets.add(BeanMapUtils.copy(po, 0));
		}
		paging.setResults(rets);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void pagingByUserId(Paging paging, long userId) {
		List<PostPO> list = postDao.pagingByUserId(paging, userId);
		List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			rets.add(BeanMapUtils.copy(po ,0));
		}
		paging.setResults(rets);
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Post> search(Paging paging, String q) throws InterruptedException, IOException, InvalidTokenOffsetsException {
		FullTextSession fullTextSession = Search.getFullTextSession(postDao.getSession());
//	    fullTextSession.createIndexer().startAndWait();
	    SearchFactory sf = fullTextSession.getSearchFactory();
	    QueryBuilder qb = sf.buildQueryBuilder().forEntity(PostPO.class).get();
	    org.apache.lucene.search.Query luceneQuery  = qb.keyword().onFields("title","summary","tags").matching(q).createQuery();
	    FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery);
	    query.setFirstResult(paging.getFirstResult());
	    query.setMaxResults(paging.getMaxResults());
	   
	    StandardAnalyzer standardAnalyzer = new StandardAnalyzer(); 
	    SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
        QueryScorer queryScorer = new QueryScorer(luceneQuery);
        Highlighter highlighter = new Highlighter(formatter, queryScorer);
        
	    List<PostPO> list = query.list();
	    int resultSize = query.getResultSize();
	    
	    List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			Post m = BeanMapUtils.copy(po ,0);
			String title = highlighter.getBestFragment(standardAnalyzer, "title", m.getTitle());
			String summary = highlighter.getBestFragment(standardAnalyzer, "summary", m.getSummary());
			String tags = highlighter.getBestFragment(standardAnalyzer, "tags", m.getTags());
			if (StringUtils.isNotEmpty(title)) {
				m.setTitle(title);
			}
			if (StringUtils.isNotEmpty(summary)) {
				m.setSummary(summary);
			}
			if (StringUtils.isNotEmpty(tags)) {
				m.setTags(tags);
			}
			rets.add(m);
		}
		paging.setTotalCount(resultSize);
		paging.setResults(rets);
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Post> searchByTag(Paging paigng, String tag) throws InterruptedException, IOException, InvalidTokenOffsetsException {
		FullTextSession fullTextSession = Search.getFullTextSession(postDao.getSession());
	    SearchFactory sf = fullTextSession.getSearchFactory();
	    QueryBuilder qb = sf.buildQueryBuilder().forEntity(PostPO.class).get();
	    org.apache.lucene.search.Query luceneQuery  = qb.keyword().onFields("tags").matching(tag).createQuery();
	    FullTextQuery query = fullTextSession.createFullTextQuery(luceneQuery);
	    query.setFirstResult(paigng.getFirstResult());
	    query.setMaxResults(paigng.getMaxResults());

	    List<PostPO> list = query.list();
	    int resultSize = query.getResultSize();
	    
	    List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			Post m = BeanMapUtils.copy(po ,0);
			rets.add(m);
		}
		paigng.setTotalCount(resultSize);
		paigng.setResults(rets);
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Post> findRecents(int maxResutls, long ignoreUserId) {
		List<PostPO> list = postDao.findRecents(maxResutls, ignoreUserId);
		List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			rets.add(BeanMapUtils.copy(po, 0));
		}
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Post> findHots(int maxResutls, long ignoreUserId) {
		List<PostPO> list = postDao.findHots(maxResutls, ignoreUserId);
		List<Post> rets = new ArrayList<Post>();
		for (PostPO po : list) {
			rets.add(BeanMapUtils.copy(po, 0));
		}
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, Post> findByIds(Set<Long> ids) {
		List<PostPO> list = postDao.findByIds(ids);
		Map<Long, Post> rets = new HashMap<Long, Post>();
		for (PostPO po : list) {
			rets.put(po.getId(), BeanMapUtils.copy(po, 0));
		}
		return rets;
	}
	
	@Override
	@Transactional
	public void post(Post post) {
		PostPO po = postDao.get(post.getId());
		if (po != null) {
			po.setUpdated(new Date());
			po.setTitle(post.getTitle());
			po.setContent(post.getContent());
			po.setMarkdown(post.getMarkdown());
			po.setEditor(post.getEditor());
			po.setSummary(trimSummary(post.getContent()));
			po.setTags(post.getTags());
		} else {
			po = new PostPO();
			
			po.setAuthor(userDao.get(post.getAuthorId()));
			po.setCreated(new Date());
			po.setStatus(EntityStatus.ENABLED);
			
			// content
			po.setType(post.getType());
			po.setTitle(post.getTitle());
			po.setContent(post.getContent());
			po.setMarkdown(post.getMarkdown());
			po.setEditor(post.getEditor());
			po.setSummary(trimSummary(post.getContent())); // summary handle
			po.setTags(post.getTags());
			
			postDao.save(po);
		}
		
		// attach handle
		if (post.getAlbums() != null) {
			for (int i = 0; i < post.getAlbums().size(); i++) {
				Attach a = post.getAlbums().get(i);
				a.setToId(po.getId());
				long id = attachService.add(a);
				if (i == 0) {
					po.setSnapshot(attachDao.get(id));
				}
			}
		}
		
		// tag handle
		if (StringUtils.isNotBlank(post.getTags())) {
			List<Tag> tags = new ArrayList<Tag>();
			String[] ts = StringUtils.split(post.getTags(), Consts.SEPARATOR);
			
			for (String t : ts) {
				Tag tag = new Tag();
				tag.setName(t);
				tag.setLastPostId(po.getId());
				tag.setPosts(1);
				tags.add(tag);
			}
			
			tagService.batchPost(tags);
		}
	}
	
	@Override
	@Transactional
	public Post get(long id) {
		PostPO po = postDao.get(id);
		Post d = null;
		if (po != null) {
			d = BeanMapUtils.copy(po, 1);
			
			List<Attach> albs = attachService.list(d.getId());
			d.setAlbums(albs);
		}
		return d;
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		PostPO po = postDao.get(id);
		if (po != null) {
			attachService.deleteByToId(id);
			postDao.delete(po);
		}
	}
	
	@Override
	@Transactional
	public void delete(long id, long authorId) {
		PostPO po = postDao.get(id);
		if (po != null) {
			Assert.isTrue(po.getAuthor().getId() == authorId, "认证失败");
			attachService.deleteByToId(id);
			postDao.delete(po);
		}
	}
	
	@Override
	@Transactional
	public void identityViews(long id) {
		PostPO po = postDao.get(id);
		if (po != null) {
			po.setViews(po.getViews() + Consts.IDENTITY_STEP);
		}
	}

	@Override
	@Transactional
	public void identityHearts(long id) {
		PostPO po = postDao.get(id);
		if (po != null) {
			po.setFavors(po.getFavors() + Consts.IDENTITY_STEP);
		}
	}
	
	@Override
	@Transactional
	public void identityComments(long id) {
		PostPO po = postDao.get(id);
		if (po != null) {
			po.setComments(po.getComments() + Consts.IDENTITY_STEP);
		}
	}
	
	/**
     * 截取文章内容
     * @param text
     * @return
     */
    private String trimSummary(String text){
        return PreviewTextUtils.truncateText(text, 126);
    }
    
    /**
	 * 更新文章方法
	 * @param p
	 */
    @Override
	@Transactional
	public void update(Post p){
    	PostPO po = postDao.get(p.getId());
    	
    	if (po != null) {
	    	po.setTitle(p.getTitle());//标题
	    	po.setSummary(trimSummary(p.getContent()));
	    	po.setContent(p.getContent());//内容
	    	po.setTags(p.getTags());//标签
    	}
	}
}
