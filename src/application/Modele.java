/**
 * Modèle comprenant la liste des formes déjà dessinées
 */
package application;

import java.util.Vector;

public class Modele {
	private Vector<FormeGeo> tab;
	
	public Modele(){
		tab = new Vector<FormeGeo>();
	}
	
	public FormeGeo get(int i) {return tab.get(i);}
	
	public int getSize() {return tab.size();}
	
	public void add(FormeGeo f) {tab.add(f);}
}
