/** Vue textuelle pour le morpion.
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

public class VueMorpionTexte extends VueMorpionClasse {

	/** La zone de jeu */
	private ModeleMorpion.Etat[][] damier;

	public VueMorpionTexte(ModeleMorpion modele) {
		super(modele);
		damier = new ModeleMorpion.Etat[ModeleMorpion.TAILLE][ModeleMorpion.TAILLE];
	}

	public void majCase(int x, int y, ModeleMorpion.Etat contenu) {
		damier[x][y] = contenu;
	}

	public void vider() {
		for (int i = 0; i < damier.length; i++) {
			for (int j = 0; j < damier[i].length; j++) {
				damier[i][j] = ModeleMorpion.Etat.VIDE;
			}
		}
	}

	public void majJoueur(ModeleMorpion.Etat joueur) {
		afficher();
		System.out.println();
		System.out.print(symboleTexte(joueur) + " joue : ");
	}

	public void proclamerMatchNul() {
		System.out.println("Pas de vainqueur !");
	}

	public void proclamerVainqueur(ModeleMorpion.Etat joueur) {
		System.out.println(symboleTexte(joueur) + " gagne !");
	}

	private static String symboleTexte(ModeleMorpion.Etat valeur) {
		String resultat = null;
		switch (valeur) {
			case VIDE:
				resultat = " ";
				break;
			case CROIX:
				resultat = "X";
				break;
			case ROND:
				resultat = "O";
				break;
		}
		return resultat;
	}

	/** Afficher le jeu de Morpion */
	public void afficher() {
		System.out.println();
		// XXX : Ne marche que pour un jeu de dimension 3
		System.out.println("     1   2   3  ");
		System.out.println("   +---+---+---+");
		for (int i = 0; i < damier.length; i++) {
			char lettre = (char)('A'+i);
			System.out.print(" " + lettre + " ");
			for (int j = 0; j < damier[i].length; j++) {
				System.out.print("| " + symboleTexte(damier[i][j]) + " ");
			}
			System.out.println("| " + lettre);
			System.out.println("   +---+---+---+");
		}
		System.out.println("     1   2   3  ");
		System.out.println();
	}

}
