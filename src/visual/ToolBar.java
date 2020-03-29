package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import utils.CacheImagenes;

public class ToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	

	public ToolBar() {
		this.add(crearBoton(0, "", "ruedadentada.png", "Ir a Gestión del Curso"));
		this.add(crearBoton(1, "", "ruedadentada.png", "Ir a Gestión del Estudiante"));
		this.add(crearBoton(2, "", "ruedadentada.png", "Ir a Gestión del Materia"));
		this.add(crearBoton(3, "", "ruedadentada.png", "Ir a Gestión del Profesor"));
		this.add(crearBoton(4, "", "ruedadentada.png", "Ir a Gestión del Valoración de la Materia"));
		
	}

	private JButton crearBoton(int num, String titulo, String icono, String toolTip) {
		JButton jbt = new JButton();
		
		jbt.setText(titulo);
		jbt.setToolTipText(toolTip);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//VentanaPrincipal.jTabbedPane.setSelectedIndex(num);
				System.out.println("Has hecho click en el botón: \"" + toolTip + "\"");
			}
			
		});
		
		try {
			jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jbt;
	}
	

}
