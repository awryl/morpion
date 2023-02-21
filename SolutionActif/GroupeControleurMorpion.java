import java.util.List;
import java.util.ArrayList;

/**
  * GroupeControleurMorpion :
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.3 $
  * @has "*" "" "*" ControleurMorpion
  */
public class GroupeControleurMorpion implements ControleurMorpion {

	/** Les contrôleurs */
	private List<ControleurMorpion> controleurs;

	public GroupeControleurMorpion() {
		controleurs = new ArrayList<ControleurMorpion>();
	}

	public void ajouter(ControleurMorpion controleur) {
		controleurs.add(controleur);
	}

	public void supprimer(ControleurMorpion controleur) {
		assert controleurs.contains(controleur) : "Contrôleur non inscrit : " + controleur;

		controleurs.remove(controleur);
	}

// Propagation vers les sous-contrôleurs des opérations du contrôleur !

	public void activer() {
		for (ControleurMorpion c : controleurs) {
			c.activer();
		}
	}

	public void desactiver() {
		for (ControleurMorpion c : controleurs) {
			c.desactiver();
		}
	}

	public void terminer() {
		// On n'utilise pas l'itérateur car on modifie la liste des contrôleurs !
		while (controleurs.size() > 0) {
			ControleurMorpion c = (ControleurMorpion) controleurs.get(0);
			c.terminer();
		}
	}

}
