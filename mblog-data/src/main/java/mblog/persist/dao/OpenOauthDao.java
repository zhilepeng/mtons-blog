/*********************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *********************************************************************/
package mblog.persist.dao;

import mblog.persist.entity.OpenOauthPO;
import mtons.modules.persist.Dao;

/**
 * 第三方开发授权登录
 * @author langhsu on 2015/8/12.
 */
public interface OpenOauthDao extends Dao<OpenOauthPO> {
    OpenOauthPO getOauthToken(String accessToken);

    OpenOauthPO getOauthToken(long userId);
}
