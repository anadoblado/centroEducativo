package visual;

import java.awt.BorderLayout;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import model.Curso;
import model.Estudiante;
import model.controladores.CursoControlador;
import model.controladores.EstudianteControlador;
import utils.CacheImagenes;

public class PanelGestionEstudiante extends JPanel {
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;

	Estudiante actual = null;
	//Se crea un objeto de tipo Panel de Gesti�n de Datos Personales porque es com�n a varios usuarios
	PanelGestionDatosPersonales panelDatos = new PanelGestionDatosPersonales();

	
	/**
	 * Construimos el Panel que se ver� para el Estudiante
	 */
	public PanelGestionEstudiante() {
		
		actual = EstudianteControlador.getInstancia().findFirst();
		
		this.setLayout(new BorderLayout());
		this.add(getBarraToolbar(), BorderLayout.NORTH);
		this.add(panelDatos, BorderLayout.CENTER);
		
		cargarDatosAcual();
	}

	/**
	 * Hacemos los botones del men�
	 * @return
	 */
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
	
	/**
	 * Aqu� se le da la funci�n a cada bot�n
	 * @param jbt
	 * @param icono
	 * @param funcion
	 */
	private void asignarFuncion(JButton jbt, String icono, final int funcion) {
		jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		jbt.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Estudiante obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = EstudianteControlador.getInstancia().findFirst();
				}
				if (funcion == LOAD_PREV) {
					obtenido = EstudianteControlador.getInstancia().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = EstudianteControlador.getInstancia().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = EstudianteControlador.getInstancia().findLast();
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
					actual = (Estudiante) obtenido;
					cargarDatosAcual();
				}
				
			}
			
		});
		
	}
	
	/**
	 * Crear un nuevo Estudiante 
	 */
	private void nuevo() {
		this.panelDatos.limpiarPantalla();
		this.actual = new Estudiante();
		JOptionPane.showMessageDialog(this, "Introduzca los datos nuevos");
	}
	


	/**
	 * Guardar un Estudiante
	 */
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
		this.actual.setImagen(this.panelDatos.getImagen());
		
		if (actual.getId() == 0) {
			EstudianteControlador.getInstancia().persist(actual);
		
		}
		else {
			EstudianteControlador.getInstancia().merge(actual);
			
		}
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
	}

	/**
	 * Carga los datos
	 */
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
			panelDatos.setImagen(this.actual.getImagen());
			
		}
		
	}

	/**
	 * Elimina al Estudiante
	 * @return
	 */
	private Estudiante eliminar() {
		String respuestas [] = new String[] {"S�", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "�Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Estudiante nuevoAMostrar = EstudianteControlador.getInstancia().findFirst();
			if(nuevoAMostrar == null) {
				nuevoAMostrar = EstudianteControlador.getInstancia().findNext(actual);
			}
			EstudianteControlador.getInstancia().remove(actual);
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

	
	public PanelGestionEstudiante(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionEstudiante(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionEstudiante(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
