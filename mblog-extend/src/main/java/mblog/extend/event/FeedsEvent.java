package mblog.extend.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author langhsu on 2015/8/18.
 */
public class FeedsEvent extends ApplicationEvent {
    private long authorId;
    private long postId;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public FeedsEvent(Object source) {
        super(source);
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }
}
