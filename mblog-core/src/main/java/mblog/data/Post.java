/**
 * 
 */
package mblog.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import mblog.lang.Consts;

/**
 * @author langhsu
 * 
 */
public class Post implements Serializable {
	private static final long serialVersionUID = -1144627551517707139L;

	private long id;
	private String type;
	private String title;
	private String summary;
	private String content;
	private String markdown; // markdown 内容
	private String editor; // 编辑器
	private String tags;
	private Attach snapshot;
	private Date created;
	private Date updated;
	private User author;
	private int featured; // 推荐状态
	private int favors; // 喜欢
	private int comments;
	private int views; // 阅读
	private int status;

	// extends
	private List<Attach> albums;
	private long authorId;
	
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

	public Attach getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(Attach snapshot) {
		this.snapshot = snapshot;
	}

	public String getTags() {
		return tags;
	}
	
	public String[] getTagsArray() {
		if (StringUtils.isNotBlank(tags)) {
			return tags.split(Consts.SEPARATOR);
		}
		return null;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Attach> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Attach> albums) {
		this.albums = albums;
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

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
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
