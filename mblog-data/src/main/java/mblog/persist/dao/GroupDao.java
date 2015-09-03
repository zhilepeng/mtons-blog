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

import mblog.persist.entity.GroupPO;
import mtons.modules.persist.BaseRepository;

import java.util.List;

/**
 * @author langhsu
 *
 */
public interface GroupDao extends BaseRepository<GroupPO> {
	List<GroupPO> findAll();
	GroupPO getByKey(String key);
}
