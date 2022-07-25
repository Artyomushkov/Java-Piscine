package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgToChar {
    private BufferedImage img;
    private char[] arrChar;

    public ImgToChar(BufferedImage img) {
        this.img = img;
    }

    public char[] formCharArray() {
        int height = img.getHeight();
        int width = img.getWidth();
        arrChar = new char[height * width + height];
        int i = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int color = img.getRGB(w, h);
                if (color == Color.BLACK.getRGB()) {
                    arrChar[i] = '0';
                }
                if (color == Color.WHITE.getRGB()) {
                    arrChar[i] = '.';
                }
                i++;
            }
            arrChar[i] = '\n';
            i++;
        }
        return arrChar;
    }
}
