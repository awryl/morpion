/**
  * ControleurMorpionTexte :
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */

public class ControleurMorpionTexte extends ControleurMorpionClasse {

	private boolean termine;
	private Thread thread;

	public ControleurMorpionTexte(ModeleMorpion morpion_) {
		super(morpion_);
		this.thread = new Thread(new Runnable() {
			public void run() {
				ControleurMorpionTexte.this.gerer();
				// XXX attention, les aspects concurrents ne sont pas traités.
				// Comme c'est l'utilisateur qui déclenche les traitements, les
				// risques d'accès concurrents sont faibles.
				// Ici, on pourrait tout exécuter dans le EventQeue !
			}
		});
		termine = false;
		this.thread.start();
	}

	public void activer() {
		super.activer();

		// Afficher les consignes
		System.out.println("Taper :");
		System.out.println("   Q  : quitter le programme :");
		System.out.println("   N  : recommencer une nouvelle partie :");
		System.out.println("   XN : pour la case (X,N) : A1, C3, etc.");
	}

	/* Saisir un coup (case jouée). */
	private void traiter() {
		char lettre = 0;	// lettre de la case sélectionnée
		char chiffre = 0;	// chiffre de la case sélectionnée

		String coup = Console.readLine("");

		if (actif) {
					// Si le contrôleur a été desactivé pendant la saisie du
					// coup, on ne tient pas compte de la saisie utilisateur.
			// Décoder le coup
			if (coup.length() == 1) {
				lettre = coup.charAt(0);
			} else if (coup.length() == 2) {
				lettre = coup.toLowerCase().charAt(0);
				chiffre = coup.toLowerCase().charAt(1);
			}

			switch (lettre) {
				case 'Q':
					System.out.println("--> Quitter le jeu !");
					quitter();
					break;

				case 'N':
					System.out.println("--> Nouvelle partie !");
					recommencer();
					break;

				case 'a':
				case 'b':
				case 'c':
					if (chiffre >= '1' && chiffre <= '3') {
						System.out.println("--> Jouer en " + Character.toUpperCase(lettre) + chiffre + " !");
						cocher(lettre-'a', chiffre-'1');
					} else {
						System.out.println("--> Case invalide !");
					}
					break;

				default:
					System.out.println("--> Commande non reconnue !");
					// afficherConsignes();
			}
		}
	}

	public void terminer() {
		System.out.println("Arrêt du ControleurMorpionTexte");
		super.terminer();
		termine = true;
	}

	private void gerer() {
		while (!termine) {
			traiter();
		}
	}

	public void signalerErreur(String message) {
		System.out.println("Erreur : " + message);
	}
}
