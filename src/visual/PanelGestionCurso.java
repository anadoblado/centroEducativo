package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import model.Curso;
import model.controladores.CursoControlador;
import utils.CacheImagenes;

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
		
		actual = CursoControlador.getInstancia().findFirst();
		
		this.setLayout(new BorderLayout());
		this.add(getPanelGestion(), BorderLayout.CENTER);
		this.add(getBarraToolbar(), BorderLayout.NORTH);
		
		cargarDatosAcual();
	}



	private JPanel getPanelGestion() {
		JPanel panelGestion = new JPanel();
		panelGestion.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
//		// Incluir menu toolbar
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		c.anchor = GridBagConstraints.CENTER;
//		c.insets = new Insets(25,25, 25,25);
//		this.add(getBarraToolbar(), c);
		
		//Incluir campo Identificador
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		panelGestion.add(new JLabel("Identificador: "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfId, c);
		
		// Incluir campo Descripcion
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		panelGestion.add(new JLabel("Descripción: "), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		panelGestion.add(jtfDescripcion, c);
		
		return panelGestion;
		
	}

	private JToolBar getBarraToolbar() {
		JToolBar tb = new JToolBar();
		
		// crea el botón de la ToolBar para ir al primer dato
		JButton jbtPrimero = new JButton();
		asignarFuncion(jbtPrimero, "gotostart.png", LOAD_FIRST);
		tb.add(jbtPrimero);

		// crea el botón de la ToolBar para ir al dato anterior
		JButton jbtAnterior = new JButton();
		asignarFuncion(jbtAnterior, "previous.png", LOAD_PREV);
		tb.add(jbtAnterior);

		// crea el botón de la ToolBar para ir al dato siguiente
		JButton jbtSiguiente = new JButton();
		asignarFuncion(jbtSiguiente, "next.png", LOAD_NEXT);
		tb.add(jbtSiguiente);

		// crea el botón de la ToolBar para ir al último dato
		JButton jbtUltimo = new JButton();
		asignarFuncion(jbtUltimo, "gotoend.png", LOAD_LAST);
		tb.add(jbtUltimo);

		// crea el botón de la ToolBar para agregar un dato
		JButton jbtNuevo = new JButton();
		asignarFuncion(jbtNuevo, "nuevo.png", NEW);
		tb.add(jbtNuevo);

		// crea el botón de la ToolBar para guardar
		JButton jbtGuardar = new JButton();
		asignarFuncion(jbtGuardar, "guardar.png", SAVE);
		tb.add(jbtGuardar);
		
		// crear el botón de la ToolBar para eliminar
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
				Curso obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = CursoControlador.getInstancia().findFirst();
				}
				if (funcion == LOAD_PREV) {
					obtenido = CursoControlador.getInstancia().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = CursoControlador.getInstancia().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = CursoControlador.getInstancia().findLast();
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
					actual = (Curso) obtenido;
					cargarDatosAcual();
				}
				
			}
			
		});
		
	}



	/**
	 * 
	 */
	private void cargarDatosAcual() {
		if(this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfDescripcion.setText("" + this.actual.getDescripcion());
		}
		
	}

	/**
	 * 
	 * @return
	 */
	private Curso eliminar() {
		String respuestas [] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Curso nuevoAMostrar = CursoControlador.getInstancia().findFirst();
			if(nuevoAMostrar == null) {
				nuevoAMostrar = CursoControlador.getInstancia().findNext(actual);
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
	
	/**
	 * 
	 */
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfDescripcion.setText("");
		
	}
	
	/**
	 * 
	 */
	private void nuevo() {
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Introduzca los datos nuevos");
	}
	
	/**
	 * 
	 */
	private void guardar() {
		Curso nuevoRegistro = new Curso();
		
		if(this.jtfId.getText().trim().equals("")) {
			nuevoRegistro.setId(0);
		}
		else {
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		}
		
		nuevoRegistro.setDescripcion(this.jtfDescripcion.getText());
		
		
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
