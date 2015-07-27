/**
 * 
 */
package mblog.persist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 用户扩展信息
 * 
 * @author langhsu
 *
 */
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="mto_users_extend")
public class UserExtendPO {
	@Id
	@GeneratedValue(generator="pkGenerator")
	@GenericGenerator(name = "pkGenerator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "user"))   
	private long id;
	
	@OneToOne(mappedBy="extend")
	@JoinColumn(name="id", referencedColumnName="id")
	private UserPO user;
	
	private int married; // 婚姻状况
	
	private String signature; // 个性签名

	private int posts; // 文章数
	
	private int comments; // 发布评论数
	
	private int follows; // 关注人数
	
	private int favors; // 收到的喜欢数
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserPO getUser() {
		return user;
	}

	public void setUser(UserPO user) {
		this.user = user;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public int getFollows() {
		return follows;
	}

	public void setFollows(int follows) {
		this.follows = follows;
	}

	public int getMarried() {
		return married;
	}

	public void setMarried(int married) {
		this.married = married;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}


	public int getFavors() {
		return favors;
	}

	public void setFavors(int favors) {
		this.favors = favors;
	}
}
