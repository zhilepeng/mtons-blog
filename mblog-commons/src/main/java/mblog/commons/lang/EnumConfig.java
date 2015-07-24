/**
 * 
 */
package mblog.commons.lang;

/**
 * 系统常量配置
 * 
 * @author langhsu
 *
 */
public enum EnumConfig {
	/**
	 * 站点类配置
	 */
	SITE_NAME("site_name", "站点名称"),
	SITE_VERSION("site_version", "站点版本"),
	SITE_WELCOMES("site_welcomes", "欢迎语"),
	SITE_DOMAIN("site_domain", "站点域名"),
	SITE_KEYWORDS("site_keywords", "keywords"),
	SITE_DESCRIPTION("site_description", "description"),
	
	/**
	 * 系统类配置
	 */
	SETTING_EDITOR("setting_editor", "文本编辑器");
	
	private EnumConfig(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	private String key;
	private String name;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
