package visual;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import utils.CacheImagenes;

public class ToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	

	public ToolBar() {
		this.add(crearBoton(0, "Curso", "ruedadentada.png", "Ir a Gestión del Curso", new PanelGestionCurso()));
		this.add(crearBoton(1, "Estudiante", "ruedadentada.png", "Ir a Gestión del Estudiante", new PanelGestionEstudiante()));
		this.add(crearBoton(2, "Materia", "ruedadentada.png", "Ir a Gestión del Materia", new PanelGestionMateria()));
		this.add(crearBoton(3, "Profesor", "ruedadentada.png", "Ir a Gestión del Profesor", new PanelGestionProfesor()));
		this.add(crearBoton(4, "Valoración", "ruedadentada.png", "Ir a Gestión del Valoración de la Materia", new PanelGestionValoracionMateria()));
		
	}

	private JButton crearBoton(int num, String titulo, String icono, String toolTip, JPanel panel) {
		JButton jbt = new JButton(titulo);
		
		jbt.setText(titulo);
		jbt.setToolTipText(toolTip);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				dialogo.setResizable(true);
				dialogo.setTitle(titulo);
				dialogo.setContentPane(panel);
				dialogo.pack();
				dialogo.setModal(true);
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
				dialogo.setVisible(true);
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
