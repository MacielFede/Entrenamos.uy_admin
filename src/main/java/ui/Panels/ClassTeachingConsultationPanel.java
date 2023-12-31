package ui.Panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

public class ClassTeachingConsultationPanel extends JPanel{
	private JTable classDataTable;
	private JScrollPane tableScrollPane;
	private JComboBox<String> institutesComboBox 	= new JComboBox<String>();
	private JComboBox<String> activitiesComboBox 	= new JComboBox<String>();
	private JComboBox<String> classesComboBox		= new JComboBox<String>();
	private String[] tableHeaders 					= new String[] {"Atributo", "Valor"};
	private final String nonSelectedOption			= "Sin seleccionar"; 
	private String selectedInstitute 				= nonSelectedOption;
	private String selectedActivity					= nonSelectedOption;
	private String selectedClass					= nonSelectedOption;
	private Map<String, DtInstitute> institutes 	= new TreeMap<String, DtInstitute>();
	private Map<String, DtActivity> activities 		= new TreeMap<String, DtActivity>();
	private Map<String, DtClass> classes 			= new TreeMap<String, DtClass>();
	
	private InstituteInterface instituteController 	= ControllerFactory.getInstance().getInstituteInterface();

	public ClassTeachingConsultationPanel() {
		initialize();
		setListeners();
		addBaseElements();
		institutes = instituteController.listSportInstitutes();
		if(institutes != null) {
			addItemsToComboBox(institutesComboBox, institutes.keySet());
		}
	}
	
	private void initialize() {
		setPanelLayout();
		setTitleLabel("Consulta de dictado de clase", "Calibri", Font.BOLD, 18);
		institutesComboBox 	= createLabelComboBox("Institutos", 1);
		activitiesComboBox	= createLabelComboBox("Actividad deportiva", 2);
		classesComboBox 	= createLabelComboBox("Clases", 3);
		activitiesComboBox.setEnabled(false);
		classesComboBox.setEnabled(false);
		createDataTable();
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
	}
	
	private void resetComboBox(JComboBox<String> comboBox) {
		comboBox.removeAllItems();
		comboBox.addItem(nonSelectedOption);
	}
	
	private void addItemsToComboBox(JComboBox<String> comboBox, Set<String> values) {
		for(String value : values) {
			comboBox.addItem(value);
		}
	}
	
	private void fillTable(DtClass data) {
		DefaultTableModel model = (DefaultTableModel) this.classDataTable.getModel();
		int actualRows = model.getRowCount();
		for (int i = actualRows - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
		actualRows = model.getRowCount();
		model.addRow(new String[] {"Nombre", data.getName()});
		model.addRow(new String[] {"Fecha de registro", data.getRegisterDate().toString()});
		model.addRow(new String[] {"Fecha y hora", data.getDateAndTime().toString()});
		model.addRow(new String[] {"URL", data.getUrl()});
		model.fireTableDataChanged(); 
		
		int numRows = model.getRowCount();
        int rowHeight = classDataTable.getRowHeight();
        int preferredHeight = rowHeight * Math.min(numRows, 4);
        classDataTable.setPreferredScrollableViewportSize(new Dimension(classDataTable.getPreferredSize().width, preferredHeight));
	}
	
	private void setListeners() {
		institutesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(institutesComboBox.getSelectedItem() != null && !institutesComboBox.getSelectedItem().toString().equals(selectedInstitute)) {
            		if(institutesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
                		activitiesComboBox.setEnabled(false);
                		resetComboBox(classesComboBox);
                		classesComboBox.setEnabled(false);
                	}
                	else {
                		activities = instituteController.selectInstitution(institutesComboBox.getSelectedItem().toString());
                		resetComboBox(activitiesComboBox);
                		if(activities != null) {
                			addItemsToComboBox(activitiesComboBox, activities.keySet());
                		}
                		activitiesComboBox.setEnabled(true);
                		resetComboBox(classesComboBox);
                		classesComboBox.setEnabled(false);
                	}
            		selectedActivity = nonSelectedOption;
            		activitiesComboBox.setSelectedItem(selectedActivity);
            		selectedClass = nonSelectedOption;
            		classesComboBox.setSelectedItem(selectedClass);
            		tableScrollPane.setVisible(false);
                	selectedInstitute = institutesComboBox.getSelectedItem().toString();
            	}
            }
        });
		
		activitiesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(activitiesComboBox.getSelectedItem() != null && !activitiesComboBox.getSelectedItem().toString().equals(selectedActivity)) {
            		if(activitiesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
                		classesComboBox.setEnabled(false);
                	}
                	else {
                		classes = instituteController.chooseActivity((activitiesComboBox.getSelectedItem().toString()));
                		resetComboBox(classesComboBox);
                		if(classes != null) {
                			addItemsToComboBox(classesComboBox, classes.keySet());
                		}
                		classesComboBox.setEnabled(true);
                	}
            		selectedClass = nonSelectedOption;
            		classesComboBox.setSelectedItem(selectedClass);
            		tableScrollPane.setVisible(false);
            		selectedActivity = activitiesComboBox.getSelectedItem().toString();
            	}
            }
        });
		JPanel mainReference = this;
		classesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(classesComboBox.getSelectedItem() != null &&!classesComboBox.getSelectedItem().toString().equals(selectedClass)) {
            		if(classesComboBox.getSelectedItem().toString().equals(nonSelectedOption)) {
            			tableScrollPane.setVisible(false);
                	}
                	else {
                		DtClass selectedClass = instituteController.chooseClassByName(classesComboBox.getSelectedItem().toString());
                		fillTable(selectedClass);
                		tableScrollPane.setVisible(true);
                		mainReference.revalidate();
                	}
            		selectedClass = classesComboBox.getSelectedItem().toString();
            	}
            }
        });
	}
	
	private void setPanelLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 66, 37, 68, 0, 52, -62};
		gridBagLayout.rowHeights = new int[]{50, 38, 38, 0, 10, 80, 53, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}
	
	private void setTitleLabel(String title, String fontName, int fontType, int fontSize) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font(fontName, fontType, fontSize));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.SOUTH;
		gbc_titleLabel.gridwidth = 7;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);
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
		gbc_institutesComboBox.gridwidth = 3;
		gbc_institutesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_institutesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_institutesComboBox.gridx = 3;
		gbc_institutesComboBox.gridy = gridy;
		add(genericComboBox, gbc_institutesComboBox);
		
		return genericComboBox;
	}
	
	private void createDataTable() {
		DefaultTableModel model = new DefaultTableModel(0, tableHeaders.length);
		model.setColumnIdentifiers(tableHeaders);
		
		classDataTable = new JTable(model);
		
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		classDataTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        cellRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		classDataTable.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		
		tableScrollPane = new JScrollPane(classDataTable);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 5;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 5;
		
		int rowCount = 5;
		int rowHeight = classDataTable.getRowHeight();
		int headerHeight = classDataTable.getTableHeader().getPreferredSize().height;
	    int totalHeight = (rowHeight * rowCount) + headerHeight;
		Dimension preferredSize = new Dimension(classDataTable.getPreferredSize().width, totalHeight);
		classDataTable.setPreferredScrollableViewportSize(preferredSize);
		
		add(tableScrollPane, gbc_table);
		tableScrollPane.setVisible(false);
	}
}
