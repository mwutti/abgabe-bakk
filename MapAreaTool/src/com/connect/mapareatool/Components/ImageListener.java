package com.connect.mapareatool.Components;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.connect.mapareatool.model.MapArea;
import com.connect.mapareatool.model.MapAreas;

public class ImageListener implements MouseListener {
	private Component c;
	private CustomImage b;
	private MapAreas areas;
	public ImageListener(Component c, CustomImage b, MapAreas areas){
		this.c = c;
		this.b = b;
		this.areas = areas;
	}	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("x: "+ arg0.getX() + " y: "+arg0.getY() +"\n");
		
		//Input Dialog
		
		String s = (String)JOptionPane.showInputDialog(
		                    c,
		                    "Enter \"id,nr\".",
		                    "Enter Data",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    null,
		                    null);		
		
		Graphics g = b.getImg().createGraphics();			
		g.setColor( Color.RED );
		g.fillRect( arg0.getX(),arg0.getY(), 17, 17);
		//add new MapArea
		String args[] = s.split(",");
		int id = Integer.valueOf(args[0]);
		int number = Integer.valueOf(args[1]);
		
		MapArea area = new MapArea(id, number, arg0.getX(), arg0.getY());
		areas.addArea(area);
		areas.areasToString();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
