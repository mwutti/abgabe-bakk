package com.connect.mapareatool.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.connect.mapareatool.model.MapArea;
import com.connect.mapareatool.model.MapAreas;

public class ExportListener implements MouseListener {
	
	private MapAreas areas;
	private BufferedWriter csvWriter;
	private BufferedWriter htmlWriter;

	public ExportListener(MapAreas areas){
		this.areas = areas;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		File csvOutput = new File("staende.csv");
		File htmlOutput = new File("areas.txt");
		try {
			csvWriter = new BufferedWriter(new FileWriter(csvOutput));
			htmlWriter = new BufferedWriter(new FileWriter(htmlOutput));
			
			writeCSV();
			writeHTML();
			
			csvWriter.close();
			htmlWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

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
	
	private void writeCSV(){
		for(MapArea a : areas.getAreas()){
			try {
				csvWriter.append(a.getNumber()+","+a.getX() + "," + a.getY() + "," +(a.getX()+17) + "," + (a.getY()+17)+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void writeHTML() {
		for(MapArea a : areas.getAreas()){
			try {
				htmlWriter.append("<area id=\"" + a.getId() +"\" shape=\"rect\" coords=\"" + a.getX() +"," + a.getY() + ","
						+ (a.getX()+17) + "," + (a.getY()+17)+"\" onClick=\"showAusstellerPreview(id)\">\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
