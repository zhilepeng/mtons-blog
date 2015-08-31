package mblog.persist.dao;

import mblog.persist.entity.FavorPO;
import mtons.modules.persist.Dao;

/**
 * @author langhsu on 2015/8/31.
 */
public interface FavorDao extends Dao<FavorPO> {
    /**
     * 指定查询
     * @param ownId
     * @param postId
     * @return
     */
    FavorPO find(long ownId, long postId);
}
