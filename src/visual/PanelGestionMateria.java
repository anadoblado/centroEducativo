package visual;

import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


import model.Curso;
import model.Materia;
import model.controladores.CursoControlador;
import model.controladores.MateriaControlador;
import utils.CacheImagenes;

public class PanelGestionMateria extends JPanel {
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	Materia actual = null;
	
	JTextField jtfId = new JTextField(5);
	JTextField jtfNombre = new JTextField(30);
	JTextField jtfAcronimo = new JTextField(10);
	JComboBox<Curso> jcbCurso = new JComboBox<Curso>();

	
	public PanelGestionMateria() {
		actual = MateriaControlador.getInstancia().findFirst();
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(), BorderLayout.CENTER);
		this.add(getBarraToolbar(), BorderLayout.NORTH);
		
		cargarDatosAcual();
	}
	
	private JPanel getPanelGestion() {
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Incluir campo Identificador
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Identificador: "), c);

		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfId, c);

		// Incluir campo Nombre
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Nombre: "), c);

		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfNombre, c);
		
		// Incluir campo Acronimo
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Acronimo: "), c);

		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfAcronimo, c);
		
		// Icluir JComboBox para el curso
		
		List<Curso> cursos = CursoControlador.getInstancia().findAllCursos();
		for (Curso cu : cursos) {
			jcbCurso.addItem(cu);
		}
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Curso: "), c);

		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jcbCurso, c);
		
		
		return panelGestion;
	}
	
	private JToolBar getBarraToolbar() {
		JToolBar tb = new JToolBar();
		
		// crea el bot�n de la ToolBar para ir al primer dato
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST);
		tb.add(jbtPrimero);

		// crea el bot�n de la ToolBar para ir al dato anterior
		JButton jbtAnterior = new JButton();
		asignarFuncion(jbtAnterior, "previous.png", LOAD_PREV);
		tb.add(jbtAnterior);

		// crea el bot�n de la ToolBar para ir al dato siguiente
		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", LOAD_NEXT);
		tb.add(jbtSiguiente);

		// crea el bot�n de la ToolBar para ir al �ltimo dato
		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST);
		tb.add(jbtUltimo);

		// crea el bot�n de la ToolBar para agregar un dato
		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW);
		tb.add(jbtNuevo);

		// crea el bot�n de la ToolBar para guardar
		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE);
		tb.add(jbtGuardar);
		
		// crear el bot�n de la ToolBar para eliminar
		JButton jbtEliminar = new JButton();
		asignarFuncion(jbtEliminar, "eliminar.png", REMOVE);
		tb.add(jbtEliminar);
		
		return tb;
	}
	
	private void asignarFuncion(JButton jbt, String icono, final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Materia obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = MateriaControlador.getInstancia().findFirst();
				}
				if (funcion == LOAD_PREV) {
					obtenido = MateriaControlador.getInstancia().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = MateriaControlador.getInstancia().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = MateriaControlador.getInstancia().findLast();
				}
				if(funcion == NEW) {
					nuevo();
				}
				if (funcion == SAVE) {
					try {
						guardar();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				if (funcion == REMOVE) {
					obtenido = eliminar();
				}
				if (obtenido != null) {
					actual = (Materia) obtenido;
					cargarDatosAcual();
				}
				
			}
			
		});
		
	}
	
	private void cargarDatosAcual() {
		if(this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfNombre.setText("" + this.actual.getNombre());
			this.jtfAcronimo.setText("" + this.actual.getAcronimo());
			this.jcbCurso.setSelectedItem(this.actual.getCurso());
		}
		
	}
	
	private Materia eliminar() {
		String respuestas [] = new String[] {"S�", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "�Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Materia nuevoAMostrar = MateriaControlador.getInstancia().findFirst();
			if(nuevoAMostrar == null) {
				nuevoAMostrar = MateriaControlador.getInstancia().findNext(actual);
			}
			CursoControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
			
			if(nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			}
			else {
				limpiarPantalla();
			}
		}
		return actual;
		
	}
	
	
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfNombre.setText("");
		this.jtfAcronimo.setText("");
		this.jcbCurso.setSelectedIndex(0);
		
		
	}
	
	
	private void nuevo() {
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Introduzca los datos nuevos");
	}
	
	
	private void guardar() {
		Materia nuevoRegistro = new Materia();
		
		if(this.jtfId.getText().trim().equals("")) {
			nuevoRegistro.setId(0);
		}
		else {
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		}
		
		nuevoRegistro.setNombre(this.jtfNombre.getText());
		nuevoRegistro.setAcronimo(this.jtfAcronimo.getText());
		nuevoRegistro.setCurso((Curso) this.jcbCurso.getSelectedItem());
		
		
		if (nuevoRegistro.getId() == 0) {
			CursoControlador.getInstancia().persist(nuevoRegistro);
		}
		else {
			CursoControlador.getInstancia().merge(nuevoRegistro);
		}
		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		this.actual = nuevoRegistro;
		
	}

}
