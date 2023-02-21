/**
  * Définir les opérations qui permettent (au modèlé) de mettre à jour une vue
  * du jeu de Morpion.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

public interface VueMorpion {

	/** Mettre à jour le contenu de la case de coordonnées (x,y). */
	//@ requires contenu == ModeleMorpion.Etat.CROIX
	//@		|| contenu == ModeleMorpion.Etat.ROND
	//@		|| contenu == ModeleMorpion.Etat.VIDE;
	//@ requires x >= 0 && x < ModeleMorpion.TAILLE;
	//@ requires x >= 0 && x < ModeleMorpion.TAILLE;
	void majCase(int x, int y, ModeleMorpion.Etat contenu);

	/** Désigner le prochain joueur. */
	//@ requires joueur != ModeleMorpion.Etat.VIDE;
	void majJoueur(ModeleMorpion.Etat joueur);

	/** vider le damier du Morpion. */
	void vider();

	/** Afficher que la partie s'est terminée par un match nul. */
	void proclamerMatchNul();

	/** Proclamer le vainqueur. */
	//@ requires joueur == ModeleMorpion.Etat.CROIX || joueur == ModeleMorpion.Etat.ROND;
	void proclamerVainqueur(ModeleMorpion.Etat joueur);

	/** terminer la vue.  La vue doit nécessairement se désinscrire du modèle.
	 */
	void terminer();

}
