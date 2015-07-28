/**
 * 
 */
package mblog.lang;


/**
 * @author langhsu
 *
 */
public interface Consts {
	/**
	 * 默认头像
	 */
	String AVATAR = "/assets/images/ava/default.png";
	
	/**
	 * 文件目录步进
	 */
	int FILE_PATH_SEED = 997;
	
	String TYPE_TEXT = "text";
	String TYPE_IMAGE = "image";
	
	String SEPARATOR = ",";
	
	String UPLOAD_ROOT = "/store";
	
	int IDENTITY_STEP = 1;
	
	int TIME_MIN = 1000;
	
	/* 状态 */
	int status_normal = 0;
	
	int status_featured = 1;
	
	int status_locked = 1;
	
	String order_featured = "featured";
	
	String order_newest = "newest";
	
	String order_hottest = "hottest";
	
	long COMMENT_TOP_PID = 0;
	
	int ATTACH_STORE_LOCAL = 0;
	
	int ATTACH_STORE_NETWORK = 1;
}
