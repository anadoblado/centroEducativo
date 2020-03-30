package visual;

import java.awt.BorderLayout;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.Profesor;
import model.controladores.EstudianteControlador;
import model.controladores.ProfesorControlador;
import utils.CacheImagenes;

public class PanelGestionProfesor extends JPanel {

	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	Profesor actual = null;
	PanelGestionDatosPersonales panelDatos = new PanelGestionDatosPersonales();
	
	public PanelGestionProfesor() {
		
		actual = ProfesorControlador.getInstancia().findFirst();
		
		this.setLayout(new BorderLayout());
		this.add(getBarraToolbar(), BorderLayout.NORTH);
		this.add(panelDatos, BorderLayout.CENTER);
		
		cargarDatosAcual();
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
				Profesor obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = ProfesorControlador.getInstancia().findFirst();
				}
				if (funcion == LOAD_PREV) {
					obtenido = ProfesorControlador.getInstancia().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = ProfesorControlador.getInstancia().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = ProfesorControlador.getInstancia().findLast();
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
					actual = (Profesor) obtenido;
					cargarDatosAcual();
				}
				
			}
			
		});
		
	}
	
	private void nuevo() {
		this.panelDatos.limpiarPantalla();
		this.actual = new Profesor();
		JOptionPane.showMessageDialog(this, "Introduzca los datos nuevos");
	}
	
	private void guardar() {
		this.actual.setNombre(this.panelDatos.getNombre());
		this.actual.setApellido1(this.panelDatos.getPrimerApellido());
		this.actual.setApellido2(this.panelDatos.getSegundoApellido());
		this.actual.setDni(this.panelDatos.getDni());
		this.actual.setDireccion(this.panelDatos.getDireccion());
		this.actual.setTelefono(this.panelDatos.getTelefono());
		this.actual.setEmail(this.panelDatos.getEmail());
		this.actual.setTipologiasexo(this.panelDatos.getTipologiaSexo());
		this.actual.setColor(this.panelDatos.getColorElegido());
		
		if (actual.getId() == 0) {
			ProfesorControlador.getInstancia().persist(actual);
		
		}
		else {
			ProfesorControlador.getInstancia().merge(actual);
			
		}
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
	}
	
	private void cargarDatosAcual() {
		if(this.actual != null) {
			panelDatos.setId(String.valueOf(this.actual.getId()));
			panelDatos.setNombre(this.actual.getNombre());
			panelDatos.setPrimerApellido(this.actual.getApellido1());
			panelDatos.setSegundoApellido(this.actual.getApellido2());
			panelDatos.setDni(this.actual.getDni());
			panelDatos.setDireccion(this.actual.getDireccion());
			panelDatos.setTelefono(this.actual.getTelefono());
			panelDatos.setEmail(this.actual.getEmail());
			panelDatos.setTipologiaSexo(this.actual.getTipologiasexo());
			panelDatos.setColorElegido(this.actual.getColor());
			
		}
		
	}
	
	
	private Profesor eliminar() {
		String respuestas [] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Profesor nuevoAMostrar = ProfesorControlador.getInstancia().findFirst();
			if(nuevoAMostrar == null) {
				nuevoAMostrar = ProfesorControlador.getInstancia().findNext(actual);
			}
			ProfesorControlador.getInstancia().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
			
			if(nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			}
			else {
				this.panelDatos.limpiarPantalla();
			}
		}
		return actual;
		
	}

	public PanelGestionProfesor(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionProfesor(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionProfesor(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
