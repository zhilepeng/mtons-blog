/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.persist.service.impl;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Attach;
import mblog.persist.dao.AttachDao;
import mblog.persist.entity.AttachPO;
import mblog.persist.service.AttachService;
import mblog.persist.utils.BeanMapUtils;

/**
 * @author langhsu
 *
 */
public class AttachServiceImpl implements AttachService {
	@Autowired
	private AttachDao attachDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Attach> findByTarget(long toId) {
		List<AttachPO> list = attachDao.findByTarget(toId);
		List<Attach> rets = new ArrayList<>();
		list.forEach(po -> rets.add(BeanMapUtils.copy(po)));
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, List<Attach>> findByTarget(Set<Long> toIds) {
		if (toIds == null || toIds.isEmpty()) {
			return Collections.emptyMap();
		}

		List<AttachPO> list = attachDao.findByTarget(toIds);
		Map<Long, List<Attach>> ret = new HashMap<>();

		list.forEach(po -> {
			Attach a = BeanMapUtils.copy(po);

			List<Attach> ats = ret.get(a.getToId());

			if (ats == null) {
				ats = new ArrayList<>();
				ret.put(a.getToId(), ats);
			}
			ats.add(a);
		});
		
		return ret;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, Attach> findByIds(Set<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyMap();
		}

		List<AttachPO> list = attachDao.findByIds(ids);
		Map<Long, Attach> ret = new HashMap<>();

		list.forEach(po -> ret.put(po.getId(), BeanMapUtils.copy(po)));
		return ret;
	}

	
	@Override
	@Transactional
	public long add(Attach album) {
		AttachPO po = new AttachPO();
		BeanUtils.copyProperties(album, po);
		attachDao.save(po);
		return po.getId();
	}
	
	@Override
	@Transactional
	public long batchPost(long toId, List<Attach> albums) {
		long lastId = 0;

		for (Attach d : albums) {
			d.setToId(toId);
			lastId = add(d);
		};
//		attachDao.batchAdd(albums);
		return lastId;
	}
	
	@Override
	@Transactional
	public void deleteByToId(long toId) {
		attachDao.deleteByToId(toId);
	}

	@Override
	@Transactional
	public void delete(long id) {
		attachDao.deleteById(id);
	}

}
