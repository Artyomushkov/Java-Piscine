package edu.school21.printer.app;

import edu.school21.printer.logic.ImgToChar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(ImgToChar.class.getResource("/resources/image.bmp"));
        ImgToChar imgToChar = new ImgToChar(img);
        char[] charArr = imgToChar.formCharArray();
        for (char c : charArr) {
            System.out.print(c);
        }
    }
}
