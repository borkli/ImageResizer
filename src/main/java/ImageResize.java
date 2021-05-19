import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResize implements Runnable {

    private File[] files;
    private String dst;
    private int size;
    private long time;

    public ImageResize(File[] files, String dst, int size, long time) {
        this.files = files;
        this.dst = dst;
        this.size = size;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage oldPhoto = ImageIO.read(file);
                BufferedImage newPhoto = Scalr.resize(oldPhoto, Scalr.Method.QUALITY,
                        Scalr.Mode.AUTOMATIC, size, Scalr.OP_ANTIALIAS);
                File dstFile = new File(dst + "/" + file.getName());
                ImageIO.write(newPhoto, "png", dstFile);
            }
            System.out.println(System.currentTimeMillis() - time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
