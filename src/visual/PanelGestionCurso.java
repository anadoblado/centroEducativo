package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Curso;

public class PanelGestionCurso extends JPanel {
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	JTextField jtfId = new JTextField(5);
	JTextField jtfDescripcion = new JTextField(15);
	
	Curso actual = null;
	

	public PanelGestionCurso() {
		super();
		this.setLayout(new BorderLayout());
		//this.add(getBarraToolbar(), BorderLayout.NORTH);
		this.add(getPanelGestion(), BorderLayout.CENTER);
	}

	private JPanel getPanelGestion() {
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Incluir panel de navegación
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 2;
//		c.insets = new Insets(0, 0, 25, 0);
//		this.add(getBarraToolbar(), c);
		
		//Incluir campo Identificador
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Identificador: "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);
		
		// Incluir campo CIF
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Descripción: "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDescripcion, c);
		
		return panelGestion;
		
	}

	private Component getBarraToolbar() {
		// TODO Auto-generated method stub
		return null;
	}

	public PanelGestionCurso(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionCurso(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionCurso(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
