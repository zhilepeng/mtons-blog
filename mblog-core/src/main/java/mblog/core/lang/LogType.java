/**
 * 
 */
package mblog.core.lang;

/**
 * @author langhsu
 *
 */
public enum LogType {
	LOGIN(1, "logined"),
	LIKED(2, "liked"),
	POSTED(3, "posted"),
	COMMENT(4, "comment");
	
	private int index;
	private String text;
	
	private LogType(int index, String text) {
		this.index = index;
		this.text = text;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
