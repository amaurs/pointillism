
package pointillism;

import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import java.awt.BasicStroke;
import java.awt.Stroke;

public class Painter
{
	private File file;

	private String filename;

	private int m;

	private int n;

	private int size;

	private int width;

	private int height;


	public Painter(String filename, int m, int n, int size)
	{
		this.filename = filename;
		this.m = m;
		this.n = n;
		this.size = size;
		this.width = this.size * this.m;
		this.height = this.size * this.n;
		try
		{
			this.file = new File(this.filename);
		}
		catch (Exception e)
		{
		}
	}


	public void paint(int[][][] matrix, int columns, int rows)
	{
		BufferedImage bi;
		float diamFloat;
		int diamInt;// offset para circulo
		try
		{
			bi = new BufferedImage(this.width, this.height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, this.width, this.height);

			for (int i = 0; i < columns; i++)
			{
				for (int j = 0; j < rows; j++)
				{
					g.setColor(new Color(matrix[i][j][1]));
					diamFloat = ((float) matrix[i][j][0] / 255);
					if (diamFloat < .9)
					{
						diamInt = (int) ((diamFloat * (float) this.size) / 2);
						g.fillOval(i * this.size + diamInt, j
								* this.size + diamInt, this.size
								- diamInt * 2, this.size - diamInt * 2);
					}
				}
			}
			ImageIO.write(bi, "png", this.file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String args[])
	{
		Painter painter = new Painter(args[0], 3, 2, 5);

		System.out.println("This is the Painter class");
		int[][][] matrix = new int[3][2][2];
		matrix[0][0][-1] = 56;
		matrix[0][1][-1] = 156;
		matrix[1][0][-1] = 20;
		matrix[1][1][-1] = 230;
		matrix[2][0][-1] = 210;
		matrix[2][1][-1] = 89;
		painter.paint(matrix, 3, 2);
	}

}
