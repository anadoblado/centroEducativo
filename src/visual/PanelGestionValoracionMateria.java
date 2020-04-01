package visual;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import model.controladores.ValoracionMateriaControlador;

public class PanelGestionValoracionMateria extends JPanel {

	Valoracionmateria actual = null;
	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	JScrollPane jspEstudiante = new JScrollPane();
	JButton jbtRefrescar = new JButton("Refrescar alumno");
	JLabel jlbEstudiante = new JLabel();
	
	
	
	public PanelGestionValoracionMateria() {
		actual = ValoracionMateriaControlador.getInstancia().findFirst();
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(), BorderLayout.CENTER);
	}

	public JPanel getPanelGestion() {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Introducir un profesor
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets (2,2,2,2);
		panel1.add(new JLabel("Nombre del profesor: "),c);
		
		List<Profesor> prof = ProfesorControlador.getInstancia().findAllProfesores();
		for (Profesor p : prof) {
			jcbProfesor.addItem(p);	
		}
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panel1.add(jcbProfesor, c);
		
		
		// Introducir una materia
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		panel1.add(new JLabel("Nombre de la asignatura: "),c);
		
		List<Materia> mat = MateriaControlador.getInstancia().findAllMaterias();
		for (Materia m : mat) {
			jcbMateria.addItem(m);
		}
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		panel1.add(jcbMateria, c);
		
		// Añadir botón
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtRefrescar, c);
		
		// Añadir el scrollPane
		c.gridx = 0;
		c.gridy = 3;
		panel1.add(jspEstudiante, c);
		jspEstudiante.setViewportView(getPanelAlumno());
		
		
		
		return panel1;
		
	}
	
	public JPanel getPanelAlumno() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		int i = 2;
		List<Estudiante> est = EstudianteControlador.getInstancia().findAllEstudiantes();
		for (Estudiante e : est) {
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets (2,2,2,2);
			panel.add(new JLabel(e.toString()), c);
			i++;
		}
		
		
		return panel;
	}
	
	public PanelGestionValoracionMateria(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionValoracionMateria(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionValoracionMateria(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
