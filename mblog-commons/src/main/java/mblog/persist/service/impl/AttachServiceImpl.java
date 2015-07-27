/**
 * 
 */
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mblog.data.Attach;
import mblog.persist.dao.AttachDao;
import mblog.persist.entity.AttachPO;
import mblog.persist.service.AttachService;
import mblog.utils.BeanMapUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
		List<Attach> rets = new ArrayList<Attach>();
		list.forEach(po -> rets.add(BeanMapUtils.copy(po)));
		return rets;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, List<Attach>> findByTarget(List<Long> toIds) {
		if (toIds == null || toIds.isEmpty()) {
			return Collections.emptyMap();
		}

		List<AttachPO> list = attachDao.findByTarget(toIds);
		Map<Long, List<Attach>> ret = new HashMap<Long, List<Attach>>();

		list.forEach(po -> {
			Attach a = BeanMapUtils.copy(po);

			List<Attach> ats = ret.get(a.getToId());

			if (ats == null) {
				ats = new ArrayList<Attach>();
				ret.put(a.getToId(), ats);
			}
			ats.add(a);
		});
		
		return ret;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<Long, Attach> findByIds(Collection<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyMap();
		}

		List<AttachPO> list = attachDao.findByIds(ids);
		Map<Long, Attach> ret = new HashMap<Long, Attach>();

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
	public long batchAdd(long toId, List<Attach> albums) {
		long ret = 0;

		for (Attach a : albums) {
			a.setToId(toId);
			ret = add(a);
		}
		return ret;
	}
	
	@Override
	@Transactional
	public void deleteByToId(long toId) {
		List<AttachPO> list = attachDao.findByTarget(toId);
		
		for (AttachPO po : list) {
			//TODO: remove file
			attachDao.delete(po);
		}
	}

}
