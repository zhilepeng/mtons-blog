/**
 * 
 */
package mblog.commons.data;


/**
 * 用户扩展信息
 * 
 * @author langhsu
 *
 */
public class UserFull extends User {
	private static final long serialVersionUID = -2824943524392184921L;

	private int married; // 婚姻状况
	
	private String signature; // 个性签名

	private int posts; // 文章数
	
	private int comments; // 发布评论数
	
	private int follows; // 关注人数
	
	private int favors; // 被赞数
	
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
