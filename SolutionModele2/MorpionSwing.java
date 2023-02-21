import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

/** Programmation d'un jeu de Morpion avec une interface graphique Swing.
  *
  * REMARQUE : Dans cette solution, le patron MVC n'a pas été appliqué !
  * On a un modèle (?), une vue et un contrôleur qui sont fortement liés.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.4 $
  */

public class MorpionSwing {

	// les images à utiliser en fonction de l'état du jeu.
	private static final Map<ModeleMorpion.Etat, ImageIcon> images
		= new HashMap<ModeleMorpion.Etat, ImageIcon>();
	static {
		images.put(ModeleMorpion.Etat.VIDE, new ImageIcon("blanc.jpg"));
		images.put(ModeleMorpion.Etat.CROIX, new ImageIcon("croix.jpg"));
		images.put(ModeleMorpion.Etat.ROND, new ImageIcon("rond.jpg"));
	}

// Choix de réalisation :
// ----------------------
//
//  Les attributs correspondant à la structure fixe de l'IHM sont définis
//	« final static » pour montrer que leur valeur ne pourra pas changer au
//	cours de l'exécution.  Ils sont donc initialisés sans attendre
//  l'exécution du constructeur !

	private ModeleMorpion modele;	// le modèle du jeu de Morpion

//  Les éléments de la vue (IHM)
//  ----------------------------

	/** Fenêtre principale */
	private JFrame fenetre;

	/** Bouton pour quitter */
	private final JButton boutonQuitter = new JButton("Q");

	/** Bouton pour commencer une nouvelle partie */
	private final JButton boutonNouvellePartie = new JButton("N");

	/** Cases du jeu */
	private final JLabel[][] cases = new JLabel[3][3];

	/** Zone qui indique le joueur qui doit jouer */
	private final JLabel joueur = new JLabel();

//  Les éléments du contrôleur
//  --------------------------

//  Remarque : les mêmes actions pourront être utilisées dans des contextes
//  différents (par exemple un bouton et une entrée d'un menu)

	/** L'action qui consiste à recommencer une nouvelle partie */
	private final ActionListener actionRecommencer = new ActionRecommencer();

	/** L'action qui consiste à quitter le morpion */
	private final ActionListener actionQuitter = new ActionQuitter();


// Le constructeur
// ---------------

	/** Construire le jeu de morpion */
	public MorpionSwing() {
		this(new ModeleMorpionSimple());
	}

	/** Construire le jeu de morpion */
	public MorpionSwing(ModeleMorpion modele) {
		// Initialiser le modèle
		this.modele = modele;

		// Créer les cases du Morpion
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = new JLabel();
			}
		}

		// Initialiser le jeu
		this.recommencer();

		// Construire la vue (présentation)
		//	Définir la fenêtre principale
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setLocation(100, 200);

		//	Définir le gestionnaire de placement
		Container contenu = this.fenetre.getContentPane();
		contenu.setLayout(new GridLayout(4,3));

		//	Positionner les composants swing
		//	- les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				contenu.add(this.cases[i][j]);
			}
		}
		//	- les boutons
		contenu.add(boutonNouvellePartie);
		contenu.add(joueur);
		contenu.add(boutonQuitter);

		//	- une barre de menus
		JMenuBar jmb = new JMenuBar();
		JMenu menuJeu = new JMenu("Jeu");
		jmb.add(menuJeu);
		JMenuItem menuNouvelle = new JMenuItem("Nouvelle partie");
		JMenuItem  menuQuitter = new JMenuItem("Quitter");
		this.fenetre.setJMenuBar(jmb);
		menuJeu.add(menuNouvelle);
		menuJeu.addSeparator();
		menuJeu.add(menuQuitter);

		// Construire le contrôleur (gestion des événements)
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boutonQuitter.addActionListener(this.actionQuitter);
		boutonNouvellePartie.addActionListener(this.actionRecommencer);
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].addMouseListener(new ActionCliquer(i, j));
			}
		}
		menuNouvelle.addActionListener(this.actionRecommencer);
		menuQuitter.addActionListener(this.actionQuitter);

		// afficher la fenêtre
		this.fenetre.pack();			// redimmensionner la fenêtre
		this.fenetre.setVisible(true);	// l'afficher
	}

// Quelques réactions aux interactions de l'utilisateur
// ----------------------------------------------------

	/** Recommencer une nouvelle partie. */
	public void recommencer() {
		this.modele.recommencer();

		// Vider les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j].setIcon(images.get(this.modele.getValeur(i, j)));
			}
		}

		// Mettre à jour le joueur
		joueur.setIcon(images.get(modele.getJoueur()));
	}

	/** Quitter le jeu. */
	public void quitter() {
		System.out.println("Fin...");
		System.exit(0);
	}

	/** Cocher la case (x, y). */
	public void cocher(int x, int y) {
		try {
			modele.cocher(x, y);
			cases[x][y].setIcon(images.get(modele.getValeur(x, y)));
			joueur.setIcon(images.get(modele.getJoueur()));

			// Fin de partie ?
			if (modele.estGagnee()) {
				JOptionPane.showMessageDialog(null, "gagne !",
						"Résultats", JOptionPane.INFORMATION_MESSAGE,
						images.get(modele.getJoueur()));
				recommencer();
			} else if (modele.estTerminee()) {
				JOptionPane.showMessageDialog(null, "Il n'y a pas de vainqueurs !",
					"Résultats", JOptionPane.INFORMATION_MESSAGE);
				recommencer();
			}
		} catch (CaseOccupeeException exception) {
			JOptionPane.showMessageDialog(null, "La case est déjà cochée !",
				"Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

// Les « écouteurs »
// -----------------

	/** Action pour quitter le jeu */
	private class ActionQuitter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			quitter();
		}
	}

	/** Action pour recommencer une nouvelle partie */
	private class ActionRecommencer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			recommencer();
		}
	}

	/** Action lorsqu'une case est sélectionnée */
	private class ActionCliquer extends MouseAdapter {
		private int x;	// abscisse de la case
		private int y;	// ordonnée de la case

		public ActionCliquer(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			cocher(x, y);
		}
	}


// La méthode principale
// ---------------------

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MorpionSwing();
			}
		});
	}

}
