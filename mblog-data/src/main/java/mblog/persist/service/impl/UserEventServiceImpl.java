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

import mblog.persist.dao.UserDao;
import mblog.persist.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author langhsu on 2015/8/6.
 */
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void identityPost(List<Long> userId, long postId, boolean identity) {
        userDao.identityPost(userId, identity);
    }

    @Override
    @Transactional
    public void identityComment(List<Long> userId, long commentId, boolean identity) {
        userDao.identityComment(userId, identity);
    }

    @Override
    @Transactional
    public void identityFollow(List<Long> userId, long followId, boolean identity) {
        userDao.identityFollow(userId, identity);
    }

    @Override
    @Transactional
    public void identityFans(List<Long> userId, long fansId, boolean identity) {
        userDao.identityFans(userId, identity);
    }

    @Override
    @Transactional
    public void identityFavors(List<Long> userId, boolean identity, int targetType, long targetId) {
        userDao.identityFavors(userId, identity);
    }
}
