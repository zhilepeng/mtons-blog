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

	@Column(name = "type", length = 18)
	private String type;

	@Field(name = "title", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	@Column(name = "title", length = 64)
	private String title; // 标题
	
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")
	@Field(name = "summary", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private String summary; // 摘要

	@Field(name = "tags", index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	private String tags; // 标签
	
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")
	private String content; // 内容
	
    @Lob
	@Basic(fetch = FetchType.LAZY) 
	@Type(type="text")
	private String markdown; // markdown 内容
	
	private String editor; // 编辑器
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "snapshot_id")
	private AttachPO snapshot; // 图片快照

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private UserPO author; // 作者

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public AttachPO getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(AttachPO snapshot) {
		this.snapshot = snapshot;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public UserPO getAuthor() {
		return author;
	}

	public void setAuthor(UserPO author) {
		this.author = author;
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

}