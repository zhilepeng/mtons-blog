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
import mtons.modules.persist.Dao;

import java.util.List;

/**
 * @author langhsu
 *
 */
public interface GroupDao extends Dao<GroupPO> {
	List<GroupPO> findAll();
	GroupPO getByKey(String key);
}
