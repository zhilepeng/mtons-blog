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

import mblog.persist.entity.VerifyPO;
import mblog.persist.service.VerifyService;
import mtons.modules.persist.BaseRepository;

/**
 * @author langhsu on 2015/8/14.
 */
public interface VerifyDao extends BaseRepository<VerifyPO> {
    VerifyPO get(long userId, int type);
    VerifyPO getByUserId(long userId);
}
