import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  * Contrôleur graphique pour le jeu de morpion.  Son aspect est le suivant :
  * <pre>
  *	 o A	o 1	  Valider
  *	 o B	o 2	Recommencer
  *	 o C	o 3	  Quitter
  * </pre>
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.2 $
  */
public class ControleurMorpionGraphique extends ControleurMorpionClasse {
	/** La fenêtre du contrôleur */
	private JFrame fenetre;

	/** le groupe de boutons pour identifier la ligne (lettre) */
	final private ButtonGroup bgLettre = new ButtonGroup();

	/** le groupe de boutons pour identifier la colonne (chiffre) */
	final private ButtonGroup bgChiffre = new ButtonGroup();

	private JButton boutonValider;
	private JButton boutonQuitter;
	private JButton boutonRecommencer;

	/** Ajouter un bouton radio avec lettre comme label, code comme « action
	  * command » dnas le groupe et le contenant.
	  */
	private static void ajouterBoutonRadio(String lettre, String code,
			ButtonGroup groupe, Container contenant)
	{
		JRadioButton result = new JRadioButton(lettre, false);
		result.setActionCommand(code);
		groupe.add(result);
		contenant.add(result);
	}

	/** Initialiser un ControleurMorpionGraphique. */
	public ControleurMorpionGraphique(ModeleMorpion modele_) {
		super(modele_);

		// Construire la zone de saisie !
		fenetre = new JFrame("Contrôle du Morpion");
		Container contenu = fenetre.getContentPane();
		contenu.setLayout(new FlowLayout());

		// - les boutons radio pour sélectionner la lettre de la case
		Box panneauBoutonsLigne = Box.createVerticalBox();
		contenu.add(panneauBoutonsLigne);
		ajouterBoutonRadio("A", "0", bgLettre, panneauBoutonsLigne);
		ajouterBoutonRadio("B", "1", bgLettre, panneauBoutonsLigne);
		ajouterBoutonRadio("C", "2", bgLettre, panneauBoutonsLigne);

		// - les boutons radio pour sélectionner le chiffre de la case
		Box panneauBoutonsColonne = Box.createVerticalBox();
		contenu.add(panneauBoutonsColonne);
		ajouterBoutonRadio("1", "0", bgChiffre, panneauBoutonsColonne);
		ajouterBoutonRadio("2", "1", bgChiffre, panneauBoutonsColonne);
		ajouterBoutonRadio("3", "2", bgChiffre, panneauBoutonsColonne);


		JPanel panneauBoutons = new JPanel();
		panneauBoutons.setLayout(new GridLayout(3, 1, 10, 10));
		contenu.add(panneauBoutons);

		// - le bouton pour valider l'entrée
		boutonValider = new JButton("Valider");
		panneauBoutons.add(boutonValider);

		// - le bouton pour recommencer
		boutonRecommencer = new JButton("Recommencer");
		panneauBoutons.add(boutonRecommencer);

		// - Le bouton pour quitter
		boutonQuitter = new JButton("Quitter");
		panneauBoutons.add(boutonQuitter);

		// Activer la fenêtre
		fenetre.pack();
		fenetre.setVisible(true);


		fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Définir le contrôleur
		fenetre.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					terminer();
				}
			});

		boutonQuitter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					quitter();
				}
			});

		boutonRecommencer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recommencer();
				}
			});

		boutonValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (bgChiffre.getSelection() != null &&
								bgLettre.getSelection() != null) {
						int ligne = Integer.parseInt(
								bgLettre.getSelection().getActionCommand());
						int colonne = Integer.parseInt(
								bgChiffre.getSelection().getActionCommand());
						cocher(ligne, colonne);
					}
				}
			});
	}

	public void terminer() {
		System.out.println("Arrêt de ControleurMorpionGraphique");
		super.terminer();
		fenetre.dispose();
	}

	public void signalerErreur(String message) {
		JOptionPane.showMessageDialog(null, message,
					"Erreur !", JOptionPane.ERROR_MESSAGE);
	}

}

/** Test du ControleurMorpionGraphique. */
class TestControleurMorpionGraphique {

	public static void main(String[] args) {
		ModeleMorpion m = null;
		m = new ModeleMorpionSimple();
		m.ajouterVue(new VueMorpionGraphique(m, "Morpion"));
		new ControleurMorpionGraphique(m);
	}

}
