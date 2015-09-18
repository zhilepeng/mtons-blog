package test;

import mblog.utils.ImageUtils;
import org.im4java.core.IM4JavaException;

import java.io.IOException;

/**
 * @author langhsu on 2015/9/4.
 */
public class ImageUtilsTest {
    public static void main(String[] args) throws InterruptedException, IOException, IM4JavaException {
        String ori = "F:\\data\\thumbnail.png";
        String dest = "F:\\data\\image.png";
        ImageUtils.truncateImage(ori, dest, 225, 140);
    }
}
