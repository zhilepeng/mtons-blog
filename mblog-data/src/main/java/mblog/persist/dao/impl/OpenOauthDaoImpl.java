package mblog.persist.dao.impl;

import mblog.persist.dao.OpenOauthDao;
import mblog.persist.entity.OpenOauthPO;
import mtons.modules.persist.impl.DaoImpl;

/**
 * @author langhsu on 2015/8/12.
 */
public class OpenOauthDaoImpl extends DaoImpl<OpenOauthPO> implements OpenOauthDao {
    public OpenOauthDaoImpl() {
        super(OpenOauthPO.class);
    }

    @Override
    public OpenOauthPO getOauthToken(String accessToken) {
        return findUniqueBy("accessToken", accessToken);
    }

    @Override
    public OpenOauthPO getOauthToken(long userId) {
        return findUniqueBy("userId", userId);
    }

}
