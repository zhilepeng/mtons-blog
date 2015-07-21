/**
 * 
 */
package mblog.persist.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mblog.data.Attach;

/**
 * @author langhsu
 *
 */
public interface AttachService {
	/**
	 * 查询文章的附件列表
	 * @param toId
	 * @return
	 */
	List<Attach> findByTarget(long toId);
	
	/**
	 * 批量查询附件
	 * 
	 * @param toIds 目录对象Id列表
	 * @return Map<toId, List<Attach>>
	 */
	Map<Long, List<Attach>> findByTarget(List<Long> toIds);
	
	/**
	 * 批量查询
	 * @param ids
	 * @return Map<attachId, List<Attach>>
	 */
	Map<Long, Attach> findByIds(Collection<Long> ids);
	
	/**
	 * 添加附件
	 * @param album
	 * @return
	 */
	long add(Attach album);
	
	/**
	 * batch add
	 * @param album
	 * @return last id
	 */
	long batchAdd(long toId, List<Attach> albums);
	
	/**
	 * 删除文章附件
	 * @param toId
	 */
	void deleteByToId(long toId);
}
