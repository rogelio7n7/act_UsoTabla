package tablaConOpciones;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Table extends JFrame implements ActionListener
{
	JFrame window;
	
	JTable tableFood;
	
	DefaultTableModel model;
	
	JTextField nameField;
	JTextField priceField;
	JTextField idField;
	
	JButton addFood = new JButton();
	JButton editFood = new JButton();
	JButton removeColumn = new JButton();
	
	int id=0;
	
	public Table()
	{
		window = new JFrame();
		
		
		
		String label[] = {
				"ID", "NAME", "PRICE"
		};
		
		String data[][] = {
				
		};
		
		model = new DefaultTableModel(data, label);
		tableFood = new JTable(model);
		
		tableFood.setCellSelectionEnabled(true);
		ListSelectionModel select = tableFood.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(
				new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent e)
					{
						String Data = null;
						int[] row = tableFood.getSelectedRows();
						int[] columns = tableFood.getSelectedColumns();
						
						for(int i = 0; i < row.length; i++)
							for(int j = 0; j< columns.length; j++)
								Data = (String) tableFood.getValueAt(row[i], columns[j]);
							
						
						System.out.println("Table element select is: "+Data);
					}
				}
		);
		
		JScrollPane sp = new JScrollPane(tableFood);
		
		sp.setBounds(0, 75, 300, 400);
		window.setLayout(null);
		
		
		window.add(sp);
		window.setTitle("Food Table");
		window.setSize(300, 600);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		addOptions();
	}
	
	public void addOptions()
	{
		
		
		addFood.setText("add");
		addFood.setBounds(150, 10, 70, 25);
		addFood.addActionListener(this);
		window.add(addFood);

		editFood.setText("edit");
		editFood.setBounds(150, 515, 80, 25);
		editFood.addActionListener(this);
		window.add(editFood);
		
		removeColumn.setText("remove");
		removeColumn.setBounds(150, 485, 80, 25);
		removeColumn.addActionListener(this);
		window.add(removeColumn);
		
		JLabel nameText = new JLabel();
		JLabel priceText = new JLabel();
		JLabel idText = new JLabel();
		
		nameText.setText("Name: ");
		nameText.setBounds(10, 10, 40, 25);
		nameText.setForeground(Color.BLACK);
    	window.add(nameText);
    	
    	idText.setText("ID: ");
    	idText.setBounds(10, 500, 40, 25);
    	idText.setForeground(Color.BLACK);
    	window.add(idText);
    	
    	priceText.setText("Price: ");
    	priceText.setBounds(10, 40, 40, 25);
    	priceText.setForeground(Color.BLACK);
    	window.add(priceText);
    	
		nameField = new JTextField();
    	priceField = new JTextField();
    	idField = new JTextField();
    	
    	nameField.setBounds(60, 10, 75, 25);
    	window.add(nameField);
    	
    	priceField.setBounds(60, 40, 75, 25);
    	window.add(priceField);
		
    	idField.setBounds(60, 500, 75, 25);
    	window.add(idField);
	}
	

	
	public String convertCounter(int id)
	{
		return Integer.toString(id);
	}
	
	public int converterNumber(String id)
	{
		return Integer.valueOf(id)-1;
	}
	
	
	public void actionButton()
	{
		removeColumn.setEnabled(false);
		editFood.setEnabled(false);
		if(model.getRowCount()>0&&converterNumber(idField.getText())<id&&converterNumber(idField.getText())<=0)
		{
			removeColumn.setEnabled(true);
			editFood.setEnabled(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case "add":
			model.insertRow(id++, new Object[] {convertCounter(id), nameField.getText(), priceField.getText()});
			break;
		case "remove":
			if(model.getRowCount()>0&&converterNumber(idField.getText())<id&&converterNumber(idField.getText())>=0)
			{
				model.removeRow(converterNumber(idField.getText()));
				id--;
			}
			else
				JOptionPane.showMessageDialog(this, "Error, No products");
			break;
		case "edit":
			if(model.getRowCount()>0&&converterNumber(idField.getText())<id&&converterNumber(idField.getText())>=0)
			{
				model.removeRow(converterNumber(idField.getText()));
				model.insertRow(converterNumber(idField.getText()), new Object[] {idField.getText(), nameField.getText(), priceField.getText()});
			}
			else
				JOptionPane.showMessageDialog(this, "Error, No products");
		default:
			
		}
	}
	public static void main(String[] args)
	{
		new Table();
	}
}
