package com.connect.mapareatool.model;

import java.util.ArrayList;

public class MapAreas {
	
	private ArrayList<MapArea> areas = null;
	
	public MapAreas(){
		this.areas = new ArrayList<MapArea>();
	}
	
	public ArrayList<MapArea> getAreas(){
		return this.areas;
	}
	public void addArea(MapArea area){
		this.areas.add(area);
	}
	
	public void areasToString(){
		for(MapArea a: areas){
			System.out.println("Id: " + a.getId() + " Nr: " + a.getNumber() + " x: " + a.getX() + " y: " + a.getY() + "\n");
		}
	}

}
