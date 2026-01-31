package hr.algebra.photoapp.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


// Utility/Helper Pattern + Strategy Pattern (different filter methods)
// Provides image processing capabilities (resize, filters, format conversion)
public class ImageProcessor {

    // dimensions of image
    public static BufferedImage resize(BufferedImage original, int width, int height) {
        if (width <= 0 && height <= 0) {
            return original;
        }

        if (width <= 0) {
            width = (int) (original.getWidth() * ((double) height / original.getHeight()));
        }
        if (height <= 0) {
            height = (int) (original.getHeight() * ((double) width / original.getWidth()));
        }

        Image tmp = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resized;
    }

    // sepia
    public static BufferedImage applySepia(BufferedImage original) {
        BufferedImage sepia = new BufferedImage(
                original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                int rgb = original.getRGB(x, y);
                Color color = new Color(rgb);

                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                tr = Math.min(255, tr);
                tg = Math.min(255, tg);
                tb = Math.min(255, tb);

                Color sepiaColor = new Color(tr, tg, tb);
                sepia.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return sepia;
    }

    // blur
    public static BufferedImage applyBlur(BufferedImage original) {
        float[] matrix = {
                1f/9f, 1f/9f, 1f/9f,
                1f/9f, 1f/9f, 1f/9f,
                1f/9f, 1f/9f, 1f/9f
        };

        Kernel kernel = new Kernel(3, 3, matrix);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        return op.filter(original, null);
    }

    // format
    public static byte[] convertFormat(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // Is format valid
        String outputFormat = format.toLowerCase();
        if (!outputFormat.equals("jpg") && !outputFormat.equals("jpeg") && 
            !outputFormat.equals("png") && !outputFormat.equals("bmp")) {
            outputFormat = "jpg";
        }
        
        // For JPEG, ensure RGB color model
        if (outputFormat.equals("jpg") || outputFormat.equals("jpeg")) {
            BufferedImage rgbImage = new BufferedImage(
                    image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = rgbImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            image = rgbImage;
        }
        
        ImageIO.write(image, outputFormat, baos);
        return baos.toByteArray();
    }

    // load image
    public static BufferedImage loadImage(byte[] imageData) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(imageData));
    }

    // filters
    public static BufferedImage applyFilters(BufferedImage original, 
                                            Integer width, Integer height,
                                            boolean sepia, boolean blur) {
        BufferedImage processed = original;

        // Resize first if requested
        if (width != null && width > 0 || height != null && height > 0) {
            int w = width != null ? width : 0;
            int h = height != null ? height : 0;
            processed = resize(processed, w, h);
        }

        // Apply filters
        if (sepia) {
            processed = applySepia(processed);
        }
        if (blur) {
            processed = applyBlur(processed);
        }

        return processed;
    }

    // image dimensions
    public static Dimension getImageDimensions(byte[] imageData) throws IOException {
        BufferedImage image = loadImage(imageData);
        return new Dimension(image.getWidth(), image.getHeight());
    }
}
