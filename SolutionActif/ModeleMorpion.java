/** Définition du « modèle » du jeu du Morpion.
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  * @composed "*" "" "*" VueMorpion
  * @composed "*" "" "*" ControleurMorpion
  */
public interface ModeleMorpion {

	// Modéliser (coder !) le contenu d'une case
	enum Etat { VIDE, CROIX, ROND };

	int TAILLE = 3;	// taille du jeu de Morpion

// Définition des événements qui vont influencer le modèle.
// Ils sont envoyés aux contrôleurs

// Remarque personnelle : Il faut distinguer :
//	- le modèle : la structure de données et les opérations associées
//	- la présentation du modèle (ou d'une partie) aux utilisateurs
//	- la saisie des actions de l'utilisateur (événements qui permettront de
//	  faire évoluer le modèle)
//	- le contrôle : la description de l'impact des événements sur le modèle
//	  (décrit par un diagramme d'états)

	/** Quitter le jeu. */
	void quitter();

	/** Recommencer une nouvelle partie.*/
	void recommencer();

	/** Cocher la case (x,y). */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	void cocher(int x, int y) throws CaseOccupeeException;

// Requêtes sur le modèle

	/** Est-ce que la partie est terminée ? */
	boolean estTerminee();

	/** Est-ce qu'il y a un gagnant ? */
	boolean estGagnee();

	/** Obtenir le joueur dont c'est le tour de jouer. */
	Etat getJoueur();

	/** Obtenir le contenu d'une case.
	 * @param x colonne de la case
	 * @param y ligne de la case
	 */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	Etat getValeur(int x, int y);

// Gestion des relations entre modèles

	/** Ajouter un nouveau controleur au modèle */
	void ajouterControleur(ControleurMorpion controleur);

	/** Supprimer un controleur */
	void supprimerControleur(ControleurMorpion controleur);

	/** Ajouter une nouvelle vue */
	void ajouterVue(VueMorpion vue);

	/** Supprimer une vue */
	void supprimerVue(VueMorpion vue);

}
