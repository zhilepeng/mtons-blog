/**
 * 
 */
package mblog.persist.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * @author langhsu
 * 
 */
@Entity
@Table(name = "mto_posts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed(index = "posts")
public class PostPO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	private long id;

	@Column(name = "group_", length = 5)
	private int group;

	@Field(name = "title", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	@Column(name = "title", length = 64)
	private String title; // 标题
	
	@Field(name = "summary", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private String summary; // 摘要

	@Field(name = "tags", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private String tags; // 标签
	
	private String editor; // 编辑器
	
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")
	private String content; // 内容
	
    @Lob
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")
	private String markdown; // markdown 内容
	
	@Column(name = "last_image_id")
	private long lastImageId;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "author_id")
	private long authorId; // 作者

	private int images; // 图片统计
	private int featured; // 推荐
	private int favors; // 喜欢数
	private int comments; // 评论数
	private int views; // 阅读数
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getLastImageId() {
		return lastImageId;
	}

	public void setLastImageId(long lastImageId) {
		this.lastImageId = lastImageId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFeatured() {
		return featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

	public int getFavors() {
		return favors;
	}

	public void setFavors(int favors) {
		this.favors = favors;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getImages() {
		return images;
	}

	public void setImages(int images) {
		this.images = images;
	}
}