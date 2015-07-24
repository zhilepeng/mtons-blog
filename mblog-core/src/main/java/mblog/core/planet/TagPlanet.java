/**
 * 
 */
package mblog.core.planet;

import java.util.List;

import mblog.commons.data.Tag;

/**
 * 保卫层(cache), 根据业务需求调用
 * 
 * @author langhsu
 *
 */
public interface TagPlanet {
	List<Tag> topTags(int maxResutls, boolean loadPost);
	void delete(long id);
	boolean cacheFlush();
}
