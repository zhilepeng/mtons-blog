/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.dao;

import java.util.List;

import mtons.modules.persist.BaseRepository;
import mtons.modules.pojos.Paging;
import mblog.persist.entity.TagPO;

/**
 * @author langhsu
 *
 */
public interface TagDao extends BaseRepository<TagPO> {
	TagPO getByName(String name);
	List<TagPO> tops(int maxResutls);
	List<TagPO> paging(Paging paging, String key, String order);
}
