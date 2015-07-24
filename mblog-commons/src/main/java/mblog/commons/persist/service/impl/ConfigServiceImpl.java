/**
 * 
 */
package mblog.commons.persist.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.commons.data.Config;
import mblog.commons.persist.dao.ConfigDao;
import mblog.commons.persist.entity.ConfigPO;
import mblog.commons.persist.service.ConfigService;

/**
 * @author langhsu
 *
 */
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;

	@Override
	@Transactional(readOnly = true)
	public List<Config> findAll() {
		List<ConfigPO> list = configDao.list();
		List<Config> rets = new ArrayList<Config>();
		
		for (ConfigPO po : list) {
			Config r = new Config();
			BeanUtils.copyProperties(po, r);
			rets.add(r);
		}
		return rets;
	}

	@Override
	@Transactional
	public void update(List<Config> settings) {
		if (settings == null) {
			return;
		}
		
		for (Config st :  settings) {
			ConfigPO entity = configDao.findByName(st.getKey());
			if (entity != null) {
				entity.setValue(st.getValue());
				configDao.update(entity);
			} else {
				entity = new ConfigPO();
				BeanUtils.copyProperties(st, entity);
				configDao.save(entity);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Config> findAll2Map() {
		List<Config> list = findAll();
		Map<String, Config> ret = new LinkedHashMap<String, Config>();
		
		for (Config c : list) {
			ret.put(c.getKey(), c);
		}
		return ret;
	}
	
	
}
