package org.harrynoob.scripts.autoflipper.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class OptionPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public OptionPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JCheckBox chckbxPriceCheck = new JCheckBox("Price check");
		GridBagConstraints gbc_chckbxPriceCheck = new GridBagConstraints();
		gbc_chckbxPriceCheck.gridwidth = 2;
		gbc_chckbxPriceCheck.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPriceCheck.gridx = 0;
		gbc_chckbxPriceCheck.gridy = 1;
		add(chckbxPriceCheck, gbc_chckbxPriceCheck);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Update after:");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridwidth = 2;
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 2;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);

	}

}
