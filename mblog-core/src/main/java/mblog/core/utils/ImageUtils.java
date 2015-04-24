/**
 * 
 */
package mblog.core.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @author langhsu
 *
 */
public class ImageUtils {
	private static Logger log = Logger.getLogger(ImageUtils.class);
	
	private static void validate(File ori, File dest) throws IOException {
		if (ori == null) {
            throw new NullPointerException("Source must not be null");
        }
        if (dest == null) {
            throw new NullPointerException("Destination must not be null");
        }
        if (ori.exists() == false) {
            throw new FileNotFoundException("Source '" + ori + "' does not exist");
        }
        if (ori.isDirectory()) {
            throw new IOException("Source '" + ori + "' exists but is a directory");
        }
        if (ori.getCanonicalPath().equals(dest.getCanonicalPath())) {
            throw new IOException("Source '" + ori + "' and destination '" + dest + "' are the same");
        }
		if (dest.getParentFile() != null && dest.getParentFile().exists() == false) {
            if (dest.getParentFile().mkdirs() == false) {
                throw new IOException("Destination '" + dest + "' directory cannot be created");
            }
        }
        if (dest.exists() && dest.canWrite() == false) {
            throw new IOException("Destination '" + dest + "' exists but is read-only");
        }
	}
	/**
	 * 图片压缩
	 * @param ori  原图位置
	 * @param dest  目标位置
	 * @param maxSize  指定压缩后最大边长
	 * @throws IOException
	 */
	public static boolean scaleImage(File ori, File dest, int maxSize) throws IOException {
		validate(ori, dest);
        
		BufferedImage src = ImageIO.read(ori); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();
        
        log.debug("origin with/height " + w + "/"+ h);

        int size = (int) Math.max(w, h);
        int tow = w;
        int toh = h;
        
        if (size > maxSize) {
        	if (w > h) {
        		tow = maxSize;
        		toh = h * maxSize / w;
        	} else {
        		tow = w * maxSize / h;
        		toh = maxSize;
        	}
        }
        
        
        log.debug("scaled with/height : " +  tow + "/"+ toh);
        
        Image image = src.getScaledInstance(tow, toh, Image.SCALE_DEFAULT);
        BufferedImage tag = new BufferedImage(tow, toh, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        ImageIO.write(tag, "JPG", dest);// 输出到文件流
        
        return true;
	}
	
	public static void scaleImage(String ori, String dest, int maxSize) throws IOException {
		scaleImage(new File(ori), new File(dest), maxSize);
	}
	
	public static void scaleImage(File ori, String dest, int maxSize) throws IOException {
		scaleImage(ori, new File(dest), maxSize);
	}
	
	
	/**
     * 自定义宽高缩放图片
	 * @throws IOException 
     */
    public static boolean scaleImage(File ori, File dest, int width, int height) throws IOException {

        if(dest.exists()){
            org.apache.commons.io.FileUtils.deleteQuietly(dest);
        }

    	validate(ori, dest);
    	
    	BufferedImage src = ImageIO.read(ori); // 读入文件
        int w = src.getWidth();
        int h = src.getHeight();
        
    	int tow, toh;
    	
		if (width < w && height < h) {
			tow = width;
			toh = height;
		} else {
			tow = w;
			toh = h;
		}
		
		// Image.SCALE_SMOOTH 的缩略算法  生成缩略图片的平滑度的,优先级比速度高 生成的图片质量比较好 但速度慢
		Image image = src.getScaledInstance(tow, toh, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(tow, toh, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        ImageIO.write(tag, "JPEG", dest);// 输出到文件流
        
		return true;
    }
	
}
