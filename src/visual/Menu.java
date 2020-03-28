package visual;

import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import utils.CacheImagenes;

public class Menu extends JMenuBar {
	
	//JTabbedPane jTabbedPane = new JTabbedPane();

	
	
	public Menu() {
		// Men� para la Gesti�n
		JMenu menuGes = new JMenu("Gestion");
	    

	    menuGes.add(crearNuevoMenuItem("Gesti�n Curso", new PanelGestionCurso()));
		menuGes.add(crearNuevoMenuItem("Estudiante", new PanelGestionEstudiante()));
		menuGes.add(crearNuevoMenuItem("Materia", new PanelGestionMateria()));
		menuGes.add(crearNuevoMenuItem("Profesor", new PanelGestionProfesor()));
		menuGes.add(crearNuevoMenuItem("Valoracion Materia", new PanelGestionValoracionMateria()));
		
		this.add(menuGes);
	}

	/**
	 * 
	 * @param titulo
	 * @param nombreIcono
	 * @param atajoTeclado
	 * @return
	 */
	private JMenuItem crearNuevoMenuItem(String titulo, JPanel panel) {
		JMenuItem item = new JMenuItem(titulo);
		
		item.addActionListener(new ActionListener() 
		{
			  @Override
	            public void actionPerformed(ActionEvent e) {
				JDialog dialogo = new JDialog();
				
				// para no redimesnionar el di�logo
				dialogo.setResizable(true);
				
				// le pasamos el t�tulo del di�logo
				dialogo.setTitle(titulo);
				
				// le pasamos el JPanel que creamos para cada una de las opciones
				dialogo.setContentPane(panel);
				
				// etiquetamos el di�logo hace que todos los componentes ocupen el espacio que necesitan
				dialogo.pack();
				
				// el usuario no puede hacer clic sobre la ventana padre si el di�logo est� abierto
				dialogo.setModal(true);
				
				// para centrar el di�logo en la pantalla
				dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2,
						(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
				
				// sacar el dialogo en la pantalla
				dialogo.setVisible(true);
			
	                
					
					
	            }
		});
		
		return item;
	}

}