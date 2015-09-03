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

import mblog.data.Attach;
import mblog.persist.entity.AttachPO;
import mtons.modules.persist.BaseRepository;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public interface AttachDao extends BaseRepository<AttachPO> {
	List<AttachPO> findByTarget(long toId);
	List<AttachPO> findByTarget(Set<Long> toIds);
	List<AttachPO> findByIds(Set<Long> ids);

	void batchAdd(List<Attach> datas);
	void deleteByToId(long toId);
}
