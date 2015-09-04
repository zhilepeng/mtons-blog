package mblog.utils;

import mtons.modules.utils.GMagickUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author langhsu on 2015/9/4.
 */
public class ImageUtils extends GMagickUtils {

    public static boolean truncateImage(String ori, String dest, int width, int height) throws IOException, InterruptedException, IM4JavaException {
        File oriFile = new File(ori);

        BufferedImage src = ImageIO.read(oriFile); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();

        int min = Math.min(w, h);
        int side = Math.min(width, height);

        IMOperation op = new IMOperation();
        op.addImage(ori);

        if (w <= width && h <= height) {
            // Don't do anything
        } else if (min < side) {
            op.gravity("center").extent(width, height);
        } else {
            op.resize(width, height, '^').gravity("center").extent(width, height);
        }

        op.addImage(dest);
        ConvertCmd convert = new ConvertCmd(true);
        convert.run(op);
        return true;
    }
}
