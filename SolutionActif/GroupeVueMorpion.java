import java.util.List;
import java.util.ArrayList;

/**
  * Une vue multiple est composée de plusieurs vues.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  * @has "*" "" "*" VueMorpion
  */

public class GroupeVueMorpion implements VueMorpion {

	private List<VueMorpion> vues;

	public GroupeVueMorpion() {
		vues = new ArrayList<VueMorpion>();
	}

	public void ajouter(VueMorpion vue) {
		vues.add(vue);
	}

	public void supprimer(VueMorpion vue) {
		assert vues.contains(vue) : "Vue non inscrite :" + vue;

		vues.remove(vue);
	}

// Propagation vers les sous-vues des opérations de la vue !

	public void majCase(int x, int y, ModeleMorpion.Etat contenu) {
		for (VueMorpion vue : this.vues) {
			vue.majCase(x, y, contenu);
		}
	}

	public void vider() {
		for (VueMorpion vue : this.vues) {
			vue.vider();
		}
	}

	public void majJoueur(ModeleMorpion.Etat joueur) {
		for (VueMorpion vue : this.vues) {
			vue.majJoueur(joueur);
		}
	}

	public void proclamerMatchNul() {
		for (VueMorpion vue : this.vues) {
			vue.proclamerMatchNul();
		}
	}

	public void proclamerVainqueur(ModeleMorpion.Etat joueur) {
		for (VueMorpion vue : this.vues) {
			vue.proclamerVainqueur(joueur);
		}
	}

	public void terminer() {
		// Attention : On ne peut pas utiliser un itérateur car lorsqu'une vue
		// se termine, elle doit se supprimer des vues du modèle.  Ceci
		// provoquerait une exception de type ConcurrentModificationException.
		while (! vues.isEmpty()) {
			VueMorpion vue = (VueMorpion) vues.get(0);
			vue.terminer();
		}
	}

}
