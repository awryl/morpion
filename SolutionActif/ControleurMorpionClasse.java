/**
  * ControleurMorpionClasse :
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  * @has * contrôle 1 ModeleMorpion
  */

abstract public class ControleurMorpionClasse implements ControleurMorpion {

	protected ModeleMorpion modele;
	protected boolean actif = false;
	protected int mode;


// Opérations générales sur un Modele
//	==> À mettre sur une classe Modele (ou une interface)

	public ControleurMorpionClasse(ModeleMorpion modele_)
	{
		modele = modele_;
		modele.ajouterControleur(this);
	}

	public ModeleMorpion getModele()
	{
		return modele;
	}

	public void activer()
	{
		actif = true;
	}

	public void desactiver()
	{
		actif = false;
	}


// Opérations spécifiques d'un ModeleMorpion.
//	==> Règles du jeu de Morpion !


	/** Sélectionner la case (x,y). */
	protected void cocher(int x, int y)
	{
		if (actif) {
			try {
				getModele().cocher(x, y);
			} catch (CaseOccupeeException e) {
				signalerErreur(e.getMessage());
			}
		}
	}

	/** Quitter le jeu. */
	protected void quitter()
	{
		if (actif) {
			getModele().quitter();
		}
	}

	/** Recommencer une nouvelle partie. */
	protected void recommencer() {
		if (actif) {
			getModele().recommencer();
		}
	}

	public void terminer() {
		getModele().supprimerControleur(this);
	}

	/** signaler une erreur.
	 * @param message l'erreur à signaler
	 */
	abstract protected void signalerErreur(String message);

}
