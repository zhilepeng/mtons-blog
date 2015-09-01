package mblog.persist.service.impl;

import mblog.data.Comment;
import mblog.data.Notify;
import mblog.data.Post;
import mblog.data.User;
import mblog.persist.dao.NotifyDao;
import mblog.persist.entity.NotifyPO;
import mblog.persist.service.NotifyService;
import mblog.persist.service.PostService;
import mblog.persist.service.UserService;
import mblog.persist.utils.BeanMapUtils;
import mtons.modules.pojos.Paging;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author langhsu on 2015/8/31.
 */
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Override
    @Transactional(readOnly = true)
    public void findByOwnId(Paging paging, long ownId) {
        List<NotifyPO> list = notifyDao.findByOwnId(paging, ownId);
        List<Notify> rets = new ArrayList<>();

        Set<Long> postIds = new HashSet<>();
        Set<Long> fromUserIds = new HashSet<>();

        // 筛选
        list.forEach(po -> {
            Notify no = BeanMapUtils.copy(po);

            rets.add(no);

            if (no.getPostId() > 0) {
                postIds.add(no.getPostId());
            }
            if (no.getFromId() > 0) {
                fromUserIds.add(no.getFromId());
            }

        });

        // 加载
        Map<Long, Post> posts = postService.findSingleMapByIds(postIds);
        Map<Long, User> fromUsers = userService.findMapByIds(fromUserIds);

        rets.forEach(n -> {
            if (n.getPostId() > 0) {
                n.setPost(posts.get(n.getPostId()));
            }
            if (n.getFromId() > 0) {
                n.setFrom(fromUsers.get(n.getFromId()));
            }
        });

        paging.setResults(rets);

    }

    @Override
    @Transactional
    public void send(Notify notify) {
        if (notify == null || notify.getOwnId() <=0 || notify.getFromId() <= 0) {
            return;
        }

        NotifyPO po = new NotifyPO();
        BeanUtils.copyProperties(notify, po);
        po.setCreated(new Date());

        notifyDao.save(po);
    }

    @Override
    @Transactional(readOnly = true)
    public int unread4Me(long ownId) {
        return notifyDao.unread4Me(ownId);
    }

    @Override
    @Transactional
    public void readed4Me(long ownId) {
        notifyDao.readed4Me(ownId);
    }
}
