package org.etu.practice.sort.visualizer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Canvas extends JComponent {
    private Image image;
    private int barCount;
    private int arrayMax;
    private int[] array;
    private final List<Integer> accessed;
    public Canvas() {
        image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        barCount = 100;
        arrayMax=0;
        accessed = new ArrayList<>();
        array = new int[barCount];
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();
                image = new BufferedImage(newSize.width,newSize.height,BufferedImage.TYPE_INT_ARGB);
                super.componentResized(e);
                repaint();
            }
        });
    }

    private void drawBar(int height,int index) {
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);

        float barWidth = (float)image.getWidth(null) / barCount;
        int heightInPixels = (int)((image.getHeight(null) - 15) * height * 1f / arrayMax);
        int verticalPosition = image.getHeight(null) - heightInPixels;
        if (barWidth > 15) {
            String value = String.valueOf(height);
            g.drawString(value, (int) (barWidth * (index + 0.5f) - 4 * value.length()), verticalPosition - 2);
        }
        int actualWidth = (int)(barWidth * (index + 1)) - (int)(barWidth * index);
        if (accessed.contains(index))
            g.setColor(Color.RED);
        else
            g.setColor(Color.BLUE);
        g.fillRect((int)(barWidth * index), verticalPosition,
                actualWidth, heightInPixels);
        g.setColor(Color.BLACK);
        if(barWidth > 4) {
            g.drawRect((int) (barWidth * index), verticalPosition,
                    actualWidth, heightInPixels);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        Random random = new Random();
        super.paintComponent(g);
        for(int i = 0; i < barCount; i++) {
            drawBar(array[i], i);
        }
        g.drawImage(image,0,0,null);

    }
    public void updateCanvas(int[] array) {
        barCount = array.length;
        this.array = array;
        accessed.clear();
        arrayMax = 0;
        for(int e : array) {
            arrayMax = Math.max(arrayMax, e);
        }
        Graphics g = image.getGraphics();
        g.setColor(this.getBackground());
        g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
        repaint();
    }

    public void markAccessed(int index) {
        accessed.add(index);
        repaint();
    }

}
