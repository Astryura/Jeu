package generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static Map<Integer, List<Case>> labyrinthe = new HashMap<Integer, List<Case>>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez la taille du labyrinthe : ");
		int taille = sc.nextInt();
		sautDeLigne(taille);
		sc.close();
		Map<Integer, List<Case>> labyrinthe = initLaby(taille);
		Random rand = new Random();
		while(!testerLabyrinthe(taille)) {
			int x = rand.nextInt(taille*taille);
			System.out.println("Chiffre � trouver :               " + x);
			int hauteur = (int) Math.floor(x/taille);
			int largeur = (x - (hauteur * taille));
			Case caseX = getCase(x, taille);
			System.out.println("VALEUR            :               " + caseX.getNumero());
			int y = taille*taille;
			Case caseY = new Case();
			int test = rand.nextInt(2);
			if( test == 0 && hauteur != taille-1) {
				y = x + taille;
				caseY = getCase(y, taille);
				if(caseX.getNumero() != caseY.getNumero()) {
					comparerCase(caseX, caseY);
					caseX.b = true;
				}
			} else if (test == 1 && largeur != taille-1) {
				y = x + 1;
				caseY = getCase(y, taille);
				if(caseX.getNumero() != caseY.getNumero()) {
					comparerCase(caseX, caseY);
					caseX.d = true;
				}
			}
			for(int ligne = 0 ; ligne < taille ; ligne++ ) {
				for( int colone = 0 ; colone < taille ; colone++ ) {
					System.out.print(labyrinthe.get(ligne).get(colone).afficher());
				}
				System.out.print("\n");
			}
			
		}
		System.out.println("                                    ");
		System.out.println("            RESULTAT FINAL          ");
		for(int ligne = 0 ; ligne < taille ; ligne++ ) {
			for( int colone = 0 ; colone < taille ; colone++ ) {
				System.out.print(labyrinthe.get(ligne).get(colone).afficher());
			}
			System.out.print("\n");
		}
	}
	
	public static void comparerCase(Case x, Case y) {
		if( x.getNumero() < y.getNumero() ) {
			System.out.println("La case " + y.getNumero() + " devient : " + x.getNumero());
			y.setNumero(x.getNumero());
			x.listCase.addAll(y.listCase);
		} else {
			System.out.println("La case " + x.getNumero() + " devient : " + y.getNumero());
			x.setNumero(y.getNumero());
			y.listCase.addAll(x.listCase);
		}
	}
	
	public static Case getCase(int numeroCase, int taille) {
		int ligne = (int) Math.floor(numeroCase / taille);
		int colone = ( numeroCase - ( ligne * taille));
		return  labyrinthe.get(ligne).get(colone);
	}

	private static boolean testerLabyrinthe(int taille) {

		boolean valide = true;
		for(int ligne = 0 ; ligne < taille ; ligne++ ) {
			for( int colone = 0 ; colone < taille ; colone++ ) {
				if(labyrinthe.get(ligne).get(colone).getNumero() != 0){
					return false;
				}
			}
		}
		return valide;
	}

	static public Map<Integer, List<Case>> initLaby(int taille) {
		for(int h = 0 ; h < taille ; h++ ) {
			List<Case> lignes = new ArrayList<Case>();
			for( int l = 0 ; l < taille ; l++ ) {
				lignes.add(new Case(h, l));
				System.out.print(lignes.get(l).afficher());
			}
			System.out.print("\n");
			labyrinthe.put(h, lignes);
		}
		return labyrinthe;
	}
	
	static public void sautDeLigne(int taille) {
		for(int i = 0 ; i < taille ; i++ ) {
			System.out.print("_ ");
		}
		System.out.print("\n");
	}

}
