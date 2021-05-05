/**
 * Modèle comprenant la liste des formes déjà dessinées
 */
package application;

import java.util.Vector;

import javafx.scene.paint.Color;

public class Modele {
	private Vector<FormeGeo> tab;
	
	public Modele(){
		tab = new Vector<FormeGeo>();
	}
	
	
	public FormeGeo get(int i) {return tab.get(i);}
	
	public int getSize() {return tab.size();}
	
	public void add(FormeGeo f) {tab.add(f);}
	
	public void remove(FormeGeo f) {tab.remove(f);}
	
	public void setSelected(FormeGeo f ) {f.selected=true; f.setColor(Color.LIGHTGRAY);}
	public void setUnSelected(FormeGeo f ) {f.selected=false; f.setColor(f.getMem());}
}
