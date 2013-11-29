package pointillism;

import java.awt.Point;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
public class TSPImage extends AbstractParser{
	public int width;
	public int height;
	private int offsetX;
	private int offsetY;
	private double noise;
	private int tol;
	public TSPImage(String s){
		super(s);
		offsetX=5;
		offsetY=5;
		noise=.2;
		tol=25;
	}
	public TSPImage(String s,int offsetX,int offsetY,double noise){
		super(s);
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		this.noise=noise;
		tol=10;
	}
	public List<Point> parse(){
		List<Point> list=new LinkedList<Point>();
		String line;
		String [] data;
		BufferedImage in;
		Random random=new Random();
		int pixel,gray,a,b,red,green,blue;
		int [] pixels;	
		try{
			in=ImageIO.read(file);
			width=in.getWidth();
			height=in.getHeight();
			for(int i=0;i<height;i+=offsetX){
				for (int j=0;j<width;j+=offsetY){
					gray=0;
					for(int k=0;k<offsetX;k++){
						for (int l=0;l<offsetY;l++){
							if(i+k<height && j+l<width){
								pixel=in.getRGB(j+l,i+k);
								red   = (pixel & 0x00ff0000) >> 16;
								green = (pixel & 0x0000ff00) >> 8;
								blue  =  (pixel & 0x000000ff);
								
								gray += (red+green+blue);
							}
						}
					}
					int numPixels =(int)((offsetX*offsetY - gray/(255*3))*noise) ;
					if(numPixels>offsetX*offsetY/tol){
						for(int q=0;q<numPixels;q++)
						{
							do{
								a=random.nextInt(offsetX);
							 	b=random.nextInt(offsetY);
							}while(j+a>width && i+b>height);
							list.add(new Point(j+a,i+b));
						}	
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();	
		}
		return list;			
	}
	public static void main(String args[]){
		TSPImage p=new TSPImage(args[0]);
		System.out.println(p);
		
		LinkedList<Point> list=(LinkedList<Point>)p.parse();
		int i=0;
		while(list.size()>0)
		{
			Point po=list.removeFirst();
			System.out.println((++i)+" "+po.getX()+" "+po.getY()+"   ");
		}
	}
}
