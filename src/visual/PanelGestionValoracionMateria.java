package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
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

	private static final Dimension minimaDimensionJTexteField = new Dimension (150, 20);
	Valoracionmateria actual = null;
	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	List<EstudianteJSpinner> spinners = new ArrayList<EstudianteJSpinner> ();
	JScrollPane jspEstudiante = new JScrollPane();
	JButton jbtRefrescar = new JButton("Refrescar alumno");
	JButton jbtGuardar = new JButton("Guardar alumno");
	JLabel jlbEstudiante = new JLabel();

	
	
	public PanelGestionValoracionMateria() {
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(), BorderLayout.CENTER);
		
	}

	/**
	 * Panel principal donde se cargan el profesor, la materia y un panel de alumnos
	 * @return
	 */
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
		jcbMateria.setMinimumSize(minimaDimensionJTexteField);
		panel1.add(jcbMateria, c);
		
		// Añadir botón de refrescar
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtRefrescar, c);
		
		jbtRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jspEstudiante.setViewportView(getPanelAlumno());
				
			}
		});
		
		
		// Añadir el scrollPane
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		jspEstudiante.setPreferredSize(new Dimension (250, 250));
		panel1.add(jspEstudiante, c);

		
		// añadir botón guardar
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtGuardar, c);
		
		jbtGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (spinners != null) {
					Profesor p = (Profesor) jcbProfesor.getSelectedItem();
					Materia m = (Materia) jcbMateria.getSelectedItem();
					for (EstudianteJSpinner spinner : spinners) {
						Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(
								p, m , spinner.estudiante);
						if (valoracion != null) {
							valoracion.setValoracion(((Integer) spinner.getValue()).floatValue()); 
							ValoracionMateriaControlador.getInstancia().merge(valoracion);
						}
						else {
							Valoracionmateria v = new Valoracionmateria();
							v.setEstudiante(spinner.estudiante);
							v.setMateria(m);
							v.setProfesor(p);
							ValoracionMateriaControlador.getInstancia().persist(v);
						}
						
					}
				}
				
			}
				
			
		});
		
		return panel1;
		
	}
	
	/**
	 * JPanel con los alumnos que irán dentro del JScrollPane
	 * @return
	 */
	public JPanel getPanelAlumno() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		int i = 2;
		this.spinners.clear();
		List<Estudiante> est = EstudianteControlador.getInstancia().findAllEstudiantes();
		for (Estudiante j : est) {
			
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0 + i;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.EAST;
			c.insets = new Insets (2,2,2,2);
			panel.add(new JLabel(j.toString()), c);
			
			c.gridx = 1;
			c.gridy = 0 + i;
			c.anchor = GridBagConstraints.WEST;
			EstudianteJSpinner spinner = new EstudianteJSpinner(j);
			this.spinners.add(spinner);
			
			// Usamos la valoración para cargar la nota del alumno en el Spinner
			Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(
					(Profesor) this.jcbProfesor.getSelectedItem(), (Materia) this.jcbMateria.getSelectedItem(), j);
			if (valoracion != null) {
				spinner.setValue(new Integer((int) valoracion.getValoracion()));
				// en el spinner aparece la valoración casteada porque es float y el spinner solo admite enteros
			}
			panel.add(spinner,c);			
			i++;
		}
		

		return panel;
	}
	

	/**
	 * Spinner de Estudiante en una clase interna para usarlo para las notas
	 * @author anita
	 *
	 */
	private class EstudianteJSpinner extends JSpinner {

		Estudiante estudiante = null;

		public EstudianteJSpinner (Estudiante estudiante) {
			super();
			this.estudiante = estudiante;
	
		}
		
	}
}
