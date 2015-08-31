package mblog.persist.service.impl;

import mblog.persist.dao.FavorDao;
import mblog.persist.entity.FavorPO;
import mblog.persist.service.FavorService;
import mtons.modules.pojos.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * @author langhsu on 2015/8/31.
 */
public class FavorServiceImpl implements FavorService {
    @Autowired
    private FavorDao favorDao;

    @Override
    @Transactional
    public void add(long userId, long postId) {
        FavorPO po = favorDao.find(userId, postId);

        Assert.isNull(po, "已经喜欢过此文章了");

        // 如果没有喜欢过, 则添加记录
        po = new FavorPO();
        po.setOwnId(userId);
        po.setPostId(postId);
        po.setCreated(new Date());

        favorDao.save(po);
    }

    @Override
    @Transactional
    public void delete(long userId, long postId) {
        FavorPO po = favorDao.find(userId, postId);
        Assert.notNull(po, "还没有喜欢过此文章");
        favorDao.delete(po);
    }

    @Override
    @Transactional(readOnly = true)
    public void pagingByOwnId(Paging paging, long ownId) {
        //TODO: 暂时不提供查询, 下一个版本跟进
    }
}
