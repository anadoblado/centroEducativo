package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.prism.paint.Color;

import model.Estudiante;
import model.Materia;
import model.Profesor;
import model.Valoracionmateria;
import model.controladores.EstudianteControlador;
import model.controladores.MateriaControlador;
import model.controladores.ProfesorControlador;
import model.controladores.ValoracionMateriaControlador;


public class PanelGestionValoracionMasiva extends JPanel {
	
	static final int MIN = 0;
	static final int MAX = 10;
	static final int INIT = 5;
	
	Valoracionmateria actual = null;
	JComboBox<Materia> jcbMateria = new JComboBox<Materia>();
	JComboBox<Profesor> jcbProfesor = new JComboBox<Profesor>();
	JSlider js = null;
	JLabel jlNota = null;
	
	private DefaultListModel<Estudiante> listModelAlumnosDisponibles = new DefaultListModel<Estudiante>();
	private JList jlistAlumnosDisponibles = new JList<Estudiante>(listModelAlumnosDisponibles);
	private JScrollPane jspAlumnosDisponibles = new JScrollPane(jlistAlumnosDisponibles);
	
	private DefaultListModel<Estudiante> listModelAlumnosSeleccionados = new DefaultListModel<Estudiante>();
	private JList jlistAlumnosSeleccionados = new JList<Estudiante>(listModelAlumnosSeleccionados);
	private JScrollPane jspAlumnosSeleccionados = new JScrollPane(jlistAlumnosSeleccionados);

	
	JButton jbtRefrescar = new JButton("Refrescar alumno");
	JButton jbtGuardar = new JButton("Guradar alumno con nota");
	JButton jbtPasarADerechaUno = new JButton(">");
	JButton jbtPasarADerechaTodos = new JButton(">>");
	JButton jbtPasarAIzquierdaUno = new JButton("<");
	JButton jbtPasarAIzquierdaTodos = new JButton("<<");
	
	
		
	/**
	 * 
	 */
	public PanelGestionValoracionMasiva() {
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(), BorderLayout.CENTER);
		
		this.js.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent e) {
						jlNota.setText(String.valueOf(js.getValue()));
						
						
					}
				});
	}
	
	
	/**
	 * 
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
		panel1.add(jcbMateria, c);
		
		
		// Se añade un JLabel para la nota
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panel1.add(new JLabel("Nota: "), c);
		
		// Se construye el JSlider y se introduce
		js = new JSlider (MIN, MAX, INIT);
		js.setMajorTickSpacing(MAX);
		js.setMinorTickSpacing(MIN);
		js.setPaintTicks(true);
		js.setPaintLabels(true);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(js, c);
		
		// Un JLabel donde aparece la nota que ponemos
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		panel1.add(new JLabel("Nota elegida"), c);
		
		
		jlNota = new JLabel();
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jlNota, c);
		
		// Se introduce la fecha de la evaluación
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.SOUTHEAST;
		panel1.add(new JLabel("Fecha "), c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.SOUTHWEST;
		panel1.add(getJFormattedTextFieldDatePersonalizado(), c);
		

		// Añadir botón de refrescar
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(3, 3, 3, 3);
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtRefrescar, c);
	
		jbtRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listModelAlumnosDisponibles.removeAllElements();
				List<Estudiante> listaEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
				for (Estudiante es : listaEstudiantes) {
					listModelAlumnosDisponibles.addElement(es);
				}
			}
		});
		
		// Panel con las listas y los botones
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(panelListas(), c);
		
		
        // Añadir botón de gurdar
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(3, 3, 3, 3);
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtGuardar, c);
		
		jbtGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Profesor p = (Profesor) jcbProfesor.getSelectedItem();
				Materia m = (Materia) jcbMateria.getSelectedItem();
				List<Estudiante> listaTodosEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
				List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
				
				for (int i = 0; i < listaTodosEstudiantes.size(); i++) {
					if (listModelAlumnosSeleccionados.contains(listaTodosEstudiantes.get(i))) {
						listaEstudiantes.add(listaTodosEstudiantes.get(i));
					}
				}
						
				for(Estudiante es : listaEstudiantes) {
					Valoracionmateria valoracion = ValoracionMateriaControlador.getInstancia().findByEstudianteAndProfesorAndMateria(p, m, es);
					if (valoracion != null) {
						valoracion.setValoracion(js.getValue());
						valoracion.setFecha((Date) getJFormattedTextFieldDatePersonalizado().getValue());
						ValoracionMateriaControlador.getInstancia().merge(valoracion);
					}
					else {
						Valoracionmateria v = new Valoracionmateria();
						v.setEstudiante(es);
						v.setMateria(m);
						v.setProfesor(p);
						v.setFecha((Date)getJFormattedTextFieldDatePersonalizado().getValue()); 
						v.setValoracion(js.getValue());
						ValoracionMateriaControlador.getInstancia().persist(v);
						
					}
					
				}
				
			}
			
		});
		
		
	
		return panel1;
		
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel getPanelBotones(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
        // Botones centrales
		c.gridwidth = 1;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(jbtPasarADerechaUno, c);
		
		jbtPasarADerechaUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int indiceAlumnosSeleccionados[] = jlistAlumnosDisponibles.getSelectedIndices();
				for (int i = indiceAlumnosSeleccionados.length - 1; i > -1; i--) {
					Estudiante est = listModelAlumnosDisponibles.elementAt(indiceAlumnosSeleccionados[i]);
					listModelAlumnosSeleccionados.addElement(est);
					listModelAlumnosDisponibles.removeElement(est);
				}
				
			}
		});
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(jbtPasarADerechaTodos, c);
		
		jbtPasarADerechaTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Estudiante> todosLosEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
				for (Estudiante est: todosLosEstudiantes) {
					listModelAlumnosSeleccionados.addElement(est);
					listModelAlumnosDisponibles.removeElement(est);
				}
				
				
			}
		});
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(jbtPasarAIzquierdaUno, c);
		
		jbtPasarAIzquierdaUno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int indiceAlumnosDisponibles[] = jlistAlumnosSeleccionados.getSelectedIndices();
				for (int i = indiceAlumnosDisponibles.length - 1; i > -1; i--) {
					Estudiante est = listModelAlumnosSeleccionados.elementAt(indiceAlumnosDisponibles[i]);
					listModelAlumnosDisponibles.addElement(est);
					listModelAlumnosSeleccionados.removeElement(est);
				}
				
			}
		});
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(jbtPasarAIzquierdaTodos, c);
		
		jbtPasarAIzquierdaTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Estudiante> todosLosEstudiantes = EstudianteControlador.getInstancia().findAllEstudiantes();
				for (Estudiante est: todosLosEstudiantes) {
					listModelAlumnosDisponibles.addElement(est);
					listModelAlumnosSeleccionados.removeElement(est);
				}

				
			}
		});

		
		return panel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JPanel panelListas() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Se introducen los paneles donde salen los listados de alumnos

//		this.jlistAlumnos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		c.anchor = GridBagConstraints.EAST;
		panel.add(jspAlumnosDisponibles,c);
		
		
		c.gridwidth = 1;
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(getPanelBotones(),c);
		
		
	
//		this.jlistAlumnos2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		
		
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.weighty = 1;
		c.anchor = GridBagConstraints.WEST;
		panel.add(jspAlumnosSeleccionados,c);
		
 		
		return panel;
	}
	

	/**
	 * 
	 * @return
	 */
	private JFormattedTextField getJFormattedTextFieldDatePersonalizado() {
		JFormattedTextField jftf = new JFormattedTextField(new JFormattedTextField.AbstractFormatter() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}

			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					return sdf.parse(text);
				} catch (Exception e) {
					return null;
				}
			}
		});
		jftf.setColumns(20);
		jftf.setValue(new Date());
		return jftf;
	}
	
	private DefaultListModel getDefaultListModel () {
		this.listModelAlumnosDisponibles = new DefaultListModel<Estudiante>();
		return this.listModelAlumnosDisponibles;
	}



	


}
