package org.pointillism;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.lang.Math;


public class Main2
{
    public static void main(String args[])
    {
        try
        {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File("dataset.csv")));
            ImageUtils image = new ImageUtils(args[0]);
            Random random=new Random(System.currentTimeMillis());
            for(Color color:image.toList())
            {   
                bf.write(color.getRed()+",");
                bf.write(color.getGreen()+",");
                bf.write(color.getBlue()+"\n");
            }
            
            bf.flush();
            bf.close();
            bf = new BufferedWriter(new FileWriter(new File("centroids.csv")));
            
            for(int i=0;i<4;i++ )
            {
                Color color=image.toList().get(random.nextInt(image.toList().size()));
                bf.write(color.getRed()+",");
                bf.write(color.getGreen()+",");
                bf.write(color.getBlue()+"\n");        
            }
            bf.flush();
            bf.close();

            Process p = Runtime.getRuntime().exec("R -f kmeans.R");
            p.waitFor();
            
            BufferedImage bi;    
            String line;
BufferedReader br=new BufferedReader(new FileReader(new File("centers.csv")));     
            bi=new BufferedImage(500, 800, BufferedImage.TYPE_INT_RGB);
            Graphics2D gf=bi.createGraphics();
            gf.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);            
            int i=0;
            while ((line = br.readLine()) != null) {
				String tokens[]=line.split(" ");
                int r,g,b;
                r=(int)Math.round(Double.parseDouble(tokens[0]));
                g=(int)Math.round(Double.parseDouble(tokens[1]));
                b=(int)Math.round(Double.parseDouble(tokens[2]));
                
                gf.setColor(new Color(r,g,b));                                
                gf.fillRect(100*i,0, 100, 800);
                i++;
			}   
            ImageIO.write(bi, "png", new File("pallete.png"));
        }catch(Exception e)
        {
            e.printStackTrace();    
        }
    }
}
