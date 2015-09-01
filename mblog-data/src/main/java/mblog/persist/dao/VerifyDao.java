/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao;

import mblog.persist.entity.VerifyPO;
import mblog.persist.service.VerifyService;
import mtons.modules.persist.Dao;

/**
 * @author langhsu on 2015/8/14.
 */
public interface VerifyDao extends Dao<VerifyPO> {
    VerifyPO get(long userId, int type);
    VerifyPO getByUserId(long userId);
}
