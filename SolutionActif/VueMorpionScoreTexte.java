/** But : Afficher les scores en s'appuyant sur une affichage texte.
  * VueMorpionScoreTexte :
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

public class VueMorpionScoreTexte extends VueMorpionClasse {
	private int scoreCroix;
	private int scoreRond;
	private int scoreNul;
	private int scoreAbandon;
	private boolean estCommencee;	// est-ce que la partie est commencée

	public VueMorpionScoreTexte(ModeleMorpion modele) {
		super(modele);
		this.scoreCroix = 0;
		this.scoreRond = 0;
		this.scoreNul = 0;
		this.scoreAbandon = 0;
		this.estCommencee = false;
	}

	public void majCase(int x, int y, ModeleMorpion.Etat contenu) {
		if (contenu != ModeleMorpion.Etat.VIDE) {
			this.estCommencee = true;
		}
	}

	public void majJoueur(ModeleMorpion.Etat joueur) {
		// RIEN À FAIRE
	}

	/** vider le damier du Morpion. */
	public void vider() {
		if (estCommencee) {
			// C'est donc une demande d'annulation
			this.scoreAbandon++;
		}
		this.estCommencee = false;
		afficher();
	}

	/** Afficher que la partie s'est terminée par un match nul. */
	public void proclamerMatchNul() {
		this.scoreNul++;
		this.estCommencee = false;
		afficher();
   }

	public void proclamerVainqueur(ModeleMorpion.Etat joueur) {
		switch (joueur) {
			case CROIX:
				this.scoreCroix++;
				break;
			case ROND:
				this.scoreRond++;
				break;
		}
		this.estCommencee = false;
		afficher();
	}

	public void terminer() {
		System.out.println("Arrêt de VueMorpionScoreTexte");
		super.terminer();
	}

	/** Afficher les scores. */
	private void afficher() {
		System.out.println("Victoires X : " + scoreCroix);
		System.out.println("Victoires O : " + scoreRond);
		System.out.println("Match nuls  : " + scoreNul);
		System.out.println("Abandons    : " + scoreAbandon);
	}
}

