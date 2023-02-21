/**
  * ControleurMorpion est le contrôleur du jeu de Morpion
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

public interface ControleurMorpion {

	/** Activer le contrôleur : le contrôleur devient opérationnel et pilote le
	  * modèle et les vues.
	  */
	void activer();

	/** Désactiver le contrôleur : le cotrôleur devient inopérationnel. */
	void desactiver();

	/** Terminer le contrôleur (et le faire disparaître).  */
	void terminer();

}
