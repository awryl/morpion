/**
  * VueMorpionClasse permet de factoriser le modèle et d'implanter la
  * desinscription de la vue de son modèle.
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

abstract public class VueMorpionClasse implements VueMorpion {

	/** Le modèle auquel réagit cette vue */
	private ModeleMorpion modele;	// Nécessaire pour terminer()

	//@ requires modele_ != null;
	public VueMorpionClasse(ModeleMorpion modele_) {
		this.modele = modele_;
	}

	public void terminer() {
		this.modele.supprimerVue(this);
	}

}

