import java.io.File;

public class ImageRun {
    private static final int COUNT_CORES = Runtime.getRuntime().availableProcessors();
    private static final int SIZE_IMAGE = 800;
    private static String srcFolder = "yourPathSRC";
    private static String dstFolder = "yourPathDST";

    public static void main(String[] args) {
        File srcFile = new File(srcFolder);
        long time = System.currentTimeMillis();
        File[] files = srcFile.listFiles();

        if (files != null) {
            int sizeNew = files.length / COUNT_CORES;
            File[] halfFiles = new File[sizeNew];
            int i = 0;

            for (File file : files) {
                halfFiles[i] = file;
                i++;
                if (i == sizeNew) {
                    ImageResize resize = new ImageResize(halfFiles, dstFolder, SIZE_IMAGE, time);
                    new Thread(resize).start();
                    halfFiles = new File[sizeNew];
                    i = 0;
                }
            }
        }
    }
}
