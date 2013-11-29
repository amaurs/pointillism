
package pointillism;

public class Main
{
	public static void main(String args[])
	{
		int pixel = Integer.parseInt(args[2]);
		int pixelout = Integer.parseInt(args[3]);
		ImageUtils p = new ImageUtils(args[0], pixel, pixel);
		int n = p.getRows();
		int m = p.getColumns();
		Painter painter = new Painter(args[1], m, n, pixelout);
		painter.paint(p.toMatrix(), m, n);
		System.out.println("Done");
	}
}
