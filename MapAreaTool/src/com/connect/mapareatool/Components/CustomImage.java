package com.connect.mapareatool.Components;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class CustomImage extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BufferedImage getImg() {
		return img;
	}

	private BufferedImage img;
	
	public CustomImage( Image img ){
		this.img = (BufferedImage) img;
		
		int w = img.getWidth( this );
		int h = img.getHeight( this );
		
		setPreferredSize( new Dimension( w, h ) );
	}
	
	protected void paintComponent( Graphics g ) {
		g.drawImage( img, 0, 0, this );		
	}
}