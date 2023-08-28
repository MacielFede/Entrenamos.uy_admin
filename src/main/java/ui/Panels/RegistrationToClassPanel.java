package ui.Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class RegistrationToClassPanel  extends JPanel {
	private JButton btnConfirm;
	private JComboBox<String> institutesComboBox 	= new JComboBox<String>();
	private JComboBox<String> activitiesComboBox 	= new JComboBox<String>();
	private JComboBox<String> classesComboBox		= new JComboBox<String>();
	private JComboBox<String> usersComboBox			= new JComboBox<String>();

	private final String nonSelectedOption			= "Sin seleccionar"; 
	private String selectedInstitute 				= nonSelectedOption;
	private String selectedActivity					= nonSelectedOption;
	private String selectedClass					= nonSelectedOption;
	private String selectedUser						= nonSelectedOption;
	
	
	public RegistrationToClassPanel(){
		setGridLayout();
		setTitleLabel("Alta de actividad deportiva", "Calibri", Font.BOLD, 18);
		institutesComboBox 	= createLabelComboBox("Instituto", 2);
		activitiesComboBox	= createLabelComboBox("Actividad deportiva", 3);
		classesComboBox 	= createLabelComboBox("Clase", 4);
		usersComboBox 		= createLabelComboBox("Usuario", 5);
		
		btnConfirm = new JButton("Confirmar");
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		btnConfirm.setBackground(Color.GREEN);
		btnConfirm.setForeground(Color.WHITE);
		gbc_btnConfirm.gridwidth = 3;
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 8;
		add(btnConfirm, gbc_btnConfirm);
		addBaseElements();
	}
	
	private void setGridLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 20, 20, 20, 20, 20, 20, 20, 20};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 0, 20, 20, 20, 20};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0};
		setLayout(gridBagLayout);
	}
	
	private void setTitleLabel(String title, String fontName, int fontType, int fontSize) {

		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(fontName, fontType, fontSize));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 1;
		gbc_titleLabel.gridy = 0;
		gbc_titleLabel.gridwidth = 9;
		gbc_titleLabel.gridx = 0;
		add(titleLabel, gbc_titleLabel);
	}
	
	private void addBaseElements() {
		institutesComboBox.addItem(selectedInstitute);
		selectedInstitute = nonSelectedOption;
		institutesComboBox.setSelectedItem(selectedInstitute);
		activitiesComboBox.addItem(selectedActivity);
		selectedActivity = nonSelectedOption;
		activitiesComboBox.setSelectedItem(selectedActivity);
		classesComboBox.addItem(selectedClass);
		selectedClass = nonSelectedOption;
		classesComboBox.setSelectedItem(selectedClass);
		selectedUser = nonSelectedOption;
		usersComboBox.addItem(selectedUser);
		usersComboBox.setSelectedItem(selectedUser);
	}
	
	private JComboBox<String> createLabelComboBox(String labelTitle, int gridy) {
		JLabel genericLabel = new JLabel(labelTitle);
		GridBagConstraints gbc_Label = new GridBagConstraints();
		gbc_Label.gridwidth = 2;
		gbc_Label.anchor = GridBagConstraints.EAST;
		gbc_Label.insets = new Insets(0, 0, 5, 5);
		gbc_Label.gridx = 1;
		gbc_Label.gridy = gridy;
		add(genericLabel, gbc_Label);
		
		JComboBox<String> genericComboBox = new JComboBox<String>();
		GridBagConstraints gbc_institutesComboBox = new GridBagConstraints();
		gbc_institutesComboBox.gridwidth = 4;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 3;
		gbc_institutesComboBox.gridy = gridy;
		add(genericComboBox, gbc_institutesComboBox);
		
		return genericComboBox;
	}
	
	private void resetComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem(nonSelectedOption);
	}
}