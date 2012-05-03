package org.pointillism;

import java.awt.Point;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;
public class ImageUtils extends AbstractParser{
	public int width;
	public int height;
	private int offsetX;
	private int offsetY;
	private float mFloat;
	private float nFloat;
	private int mInt;
	private int nInt;
	private double noise;
	private int tol;
	private BufferedImage in;
  private float stepX;
  private float stepY;
  private int[][][] matrix;
private List<Color> list=null;
	public ImageUtils(String s,int offsetX,int offsetY){
		super(s);
		this.offsetX=offsetX;
	  this.offsetY=offsetY;
		
		this.noise=.2;
		this.tol=25;
		
		try{
		  this.in=ImageIO.read(file);
		  this.width=this.in.getWidth();
			this.height=this.in.getHeight();
			this.mFloat=((float)this.width)/offsetX;
			this.nFloat=((float)this.height)/offsetY;
		  this.mInt=Math.round(this.mFloat);
		  this.nInt=Math.round(this.nFloat);
		  this.stepX=(float)this.width/this.mInt;
		  this.stepY=(float)this.height/this.nInt;
		  this.matrix= new int[this.mInt][this.nInt][2];
		}catch(Exception e)
		{
		  e.printStackTrace();
    }
	}
	
		public ImageUtils(String s){
		super(s);
		this.offsetX=5;
	    this.offsetY=5;
		
	
		try{
		  this.in=ImageIO.read(file);
		  this.width=this.in.getWidth();
		  this.height=this.in.getHeight();
		  this.mFloat=((float)this.width)/offsetX;
		  this.nFloat=((float)this.height)/offsetY;
		  this.mInt=Math.round(this.mFloat);
		  this.nInt=Math.round(this.nFloat);
		  this.stepX=(float)this.width/this.mInt;
		  this.stepY=(float)this.height/this.nInt;
		  this.matrix= new int[this.mInt][this.nInt][2];
		}catch(Exception e)
		{
		  e.printStackTrace();
    }
	}
	
	public int[][][] toMatrix()
	{
	 
	  int gray,blue=0,red=0,green=0,pixel=0;
	  int redAcum,blueAcum,greenAcum;

			for(int i=0;i<this.nInt;i++){
				for (int j=0;j<this.mInt;j++){
					gray=0;blueAcum=0;redAcum=0;greenAcum=0;
					
					for(int k=0;k<offsetX;k++){
						for (int l=0;l<offsetY;l++){
							try{
							 // System.out.println(i+","+j);
								pixel=in.getRGB(j*offsetX+k,i*offsetY+l);
								
								red   = (pixel & 0x00ff0000) >> 16;
								green = (pixel & 0x0000ff00) >> 8;
								blue  =  (pixel & 0x000000ff);
								gray += (red+green+blue);
								redAcum += red;
								greenAcum += green;
								blueAcum += blue;
							}catch(Exception e){};
						}
					}
					redAcum=redAcum/(offsetX*offsetY*3);
					greenAcum=greenAcum/(offsetX*offsetY*3);
					blueAcum=blueAcum/(offsetX*offsetY*3);
					this.matrix[j][i][1] = pixel;
					this.matrix[j][i][0] = gray/(offsetX*offsetY*3);

				}
			}
		
	  return this.matrix;
	}
	
	public String printMatrix(){
    String message="";
    
    for(int i=0;i<this.nInt;i++)
    {
				for (int j=0;j<this.mInt;j++)
				{
				  message+=" "+this.matrix[j][i][0]+" "+(this.matrix[j][i][0]<10?"  ":this.matrix[j][i][0]<100?" ":"");
				  
				}
				message+=this.mInt+"\n";
		}
		return message;
  }
	
	public int getRows()
	{
	  return nInt;
	}
	
	public int getColumns()
	{
	  return mInt;
	}	

    public List<Color> toList()
    {
         
        
        if(this.list==null)
        {            
            this.list=new ArrayList<Color>();
            for(int i=0;i<this.width;i++)
            {
			    for (int j=0;j<this.height;j++)
			    {
			        list.add(new Color(this.in.getRGB(i,j)));
			    }
		    }
        }
        return this.list;
    }
    
	public static void main(String args[])
    {
		ImageUtils p=new ImageUtils(args[0]);
	}
}
