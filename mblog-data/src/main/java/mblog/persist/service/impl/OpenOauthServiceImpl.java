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

import mblog.data.OpenOauth;
import mblog.data.User;
import mblog.persist.dao.OpenOauthDao;
import mblog.persist.dao.UserDao;
import mblog.persist.entity.OpenOauthPO;
import mblog.persist.entity.UserPO;
import mblog.persist.service.OpenOauthService;
import mblog.persist.utils.BeanMapUtils;
import mtons.modules.utils.MD5Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方登录授权管理
 * @author langhsu on 2015/8/12.
 */
public class OpenOauthServiceImpl implements OpenOauthService {
    @Autowired
    private OpenOauthDao openOauthDao;
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getUserByOauthToken(String oauth_token) {
        OpenOauthPO thirdToken = openOauthDao.getOauthToken(oauth_token);
        UserPO userPO = userDao.get(thirdToken.getId());
        return BeanMapUtils.copy(userPO);
    }

    @Override
    @Transactional
    public OpenOauth getOauthByToken(String oauth_token) {
        OpenOauthPO po = openOauthDao.getOauthToken(oauth_token);
        OpenOauth vo = null;
        if (po != null) {
            vo = new OpenOauth();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    @Transactional
    public OpenOauth getOauthByUid(long userId) {
        OpenOauthPO po = openOauthDao.getOauthToken(userId);
        OpenOauth vo = null;
        if (po != null) {
            vo = new OpenOauth();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    @Transactional
    public boolean checkIsOriginalPassword(long userId) {
        OpenOauthPO po = openOauthDao.getOauthToken(userId);
        if (po != null) {
            UserPO upo = userDao.get(userId);

            String pwd = MD5Helper.md5(po.getAccessToken());
            // 判断用户密码 和 登录状态
            if (pwd.equals(upo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void saveOauthToken(OpenOauth oauth) {
        OpenOauthPO po = new OpenOauthPO();
        BeanUtils.copyProperties(oauth, po);
        openOauthDao.save(po);
    }

	@Override
	@Transactional
	public OpenOauth getOauthByOauthUserId(String oauthUserId) {
		OpenOauthPO po = openOauthDao.getOauthUserId(oauthUserId);
        OpenOauth vo = null;
        if (po != null) {
            vo = new OpenOauth();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
	}

}
