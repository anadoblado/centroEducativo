package visual;

import java.awt.BorderLayout;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	String valorNota = null;
	private DefaultListModel<Estudiante> listModelAlumnos = null;
	private List<Estudiante> alumnos = EstudianteControlador.getInstancia().findAllEstudiantes();
	private JList jlistAlumnos;
	private JList jlistAlumnos2;
	JButton jbtRefrescar = new JButton("Refrescar alumno");
		
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
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panel1.add(new JLabel("Nota: "), c);
		
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
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		panel1.add(new JLabel("Nota puesta"), c);
		
//		valorNota = Integer.toString(js.getValue());
		jlNota = new JLabel();
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		panel1.add(jlNota, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.SOUTHEAST;
		panel1.add(new JLabel("Fecha "), c);

		c.gridx = 1;
		c.anchor = GridBagConstraints.SOUTHWEST;
		panel1.add(getJFormattedTextFieldDatePersonalizado(), c);
		
		jlistAlumnos = new JList(this.getDefaultListModel());
		this.jlistAlumnos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollJList = new JScrollPane(jlistAlumnos);
		
		
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.weighty = 1;
		panel1.add(scrollJList,c);

		jlistAlumnos2 = new JList(this.getDefaultListModel());
		this.jlistAlumnos2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollJList2 = new JScrollPane(jlistAlumnos2);
		
		
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 4;
		c.weighty = 1;
		panel1.add(scrollJList2,c);

// A�adir bot�n de refrescar
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		panel1.add(jbtRefrescar, c);
		
		jbtRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollJList.setViewportView(jlistAlumnos);
				
				
			}
		});
	
		return panel1;
		
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
		this.listModelAlumnos = new DefaultListModel<Estudiante>();
		return this.listModelAlumnos;
	}


//	/**
//	 * @return the valorNota
//	 */
//	public String getValorNota() {
//		return valorNota;
//	}
//
//	/**
//	 * @param valorNota the valorNota to set
//	 */
//	public void setValorNota(String valorNota) {
//		this.valorNota = valorNota;
//	}

//	public static JSlider instance() {
//		return instance ((Utils.obtenerNumeroAzar(0, 1) == 0) ? JSlider.HORIZONTAL : JSlider.VERTICAL, MIN, MAX, INIT);
//	}
//
//	public static JSlider instance (int tipo, int min, int max, int init) {
//		JSlider js = new JSlider (tipo, min, max, init);
//		js.setMajorTickSpacing(10);
//		js.setMinorTickSpacing(1);
//		js.setPaintTicks(true);
//		js.setPaintLabels(true);
//		return js;
//	}

	


}