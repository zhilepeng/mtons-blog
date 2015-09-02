/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.extend.planet;

import java.util.List;

import mblog.data.Tag;

/**
 * 标签保卫层, 带缓存策略
 *
 * - 如果不使用缓存, 则直接调用Service
 *
 * @author langhsu
 *
 */
public interface TagPlanet {
	List<Tag> topTags(int maxResutls, boolean loadPost);
	void delete(long id);
	boolean cacheFlush();
}
