import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
  * VueMorpionGraphique propose une vue graphique pour le jeu de Morpion.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.3 $
  */

public class VueMorpionGraphique extends VueMorpionClasse {

	// Les trois images pour décorer les cases du jeu
	private static final ImageIcon BLANC = new ImageIcon("blanc.jpg");
	private static final ImageIcon CROIX = new ImageIcon("croix.jpg");
	private static final ImageIcon ROND = new ImageIcon("rond.jpg");

	/** La fenêtre principale */
	private JFrame fenetre;

	/** Les cases du jeu */
	private JLabel[][] cases;

	/** La zone qui indique le joueur qui doit jouer */
	private JLabel suivantLabel = new JLabel();

	public VueMorpionGraphique(ModeleMorpion modele, String titre) {
		super(modele);

		// Création des cases du Morpion
		cases = new JLabel[3][3];
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[i].length; j++) {
				cases[i][j] = new JLabel();
				cases[i][j].setIcon(contenu2Image(ModeleMorpion.Etat.VIDE));
			}
		}

		// Construction de la vue (présentation)
		fenetre = new JFrame(titre == null ? "Morpion" : titre);
		fenetre.setLocation(300,200);
		Container contenu = fenetre.getContentPane();
		contenu.setLayout(new GridLayout(4,3));
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[i].length; j++) {
				contenu.add(cases[i][j]);
			}
		}
		// contenu.add(boutonNouvellePartie);
		contenu.add(new JLabel(""));
		contenu.add(suivantLabel);
		contenu.add(new JLabel("joue..."));
		// contenu.add(boutonQuitter);

		fenetre.pack();
		fenetre.setVisible(true);

		// Permettre la fermeture de la fenêtre
		fenetre.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				terminer();
			}
		});
	}

	public void majCase(int x, int y, ModeleMorpion.Etat contenu) {
		cases[x][y].setIcon(contenu2Image(contenu));
	}

	public void vider() {
		for (int i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[i].length; j++) {
				cases[i][j].setIcon(contenu2Image(ModeleMorpion.Etat.VIDE));
			}
		}
	}

	public void majJoueur(ModeleMorpion.Etat joueur) {
		suivantLabel.setIcon(contenu2Image(joueur));
	}

	public void proclamerMatchNul() {
		JOptionPane.showMessageDialog(null, "Il n'y a pas de vainqueurs !",
					"Résultats", JOptionPane.INFORMATION_MESSAGE);
	}

	public void proclamerVainqueur(ModeleMorpion.Etat joueur) {
		JOptionPane.showMessageDialog(null, "gagne !", "Résultats",
				JOptionPane.INFORMATION_MESSAGE, contenu2Image(joueur));
	}

	public void terminer() {
		System.out.println("Arrêt de VueMorpionGraphique " + fenetre.getTitle());
		super.terminer();
		fenetre.dispose();
	}

	static private Icon contenu2Image(ModeleMorpion.Etat contenu)
	{
		Icon resultat = null;
		switch (contenu) {
			case VIDE:
				resultat = BLANC;
				break;

			case CROIX:
				resultat = CROIX;
				break;

			case ROND:
				resultat = ROND;
				break;
		}
		return resultat;
	}

}
