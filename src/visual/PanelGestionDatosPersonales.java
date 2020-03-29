package visual;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Tipologiasexo;

public class PanelGestionDatosPersonales extends JPanel {
	private JTextField jtfNombre;
	private JTextField jtfPrimerApellido;
	private JTextField jtfSegundoApellido;
	private JTextField jtfDni;
	private JTextField jtfDireccion;
	private JTextField jtfEmail;
	private JTextField jtfTelefono;
	private JComboBox<Tipologiasexo> jcbSexo;
	private JScrollPane scrollPaneImagen;
	private byte[] imagen;
	private JButton jbtCambiarImg;
	private JLabel jlblColorElegido;
	private JTextField jtfColorElegido;
	private JButton jbtElegircolor;
	

	public PanelGestionDatosPersonales() {
		// TODO Auto-generated constructor stub
	}

	
	public void limpiarPantalla() {
		this.setNombre("");
		this.jtfPrimerApellido.setText("");
		this.jtfSegundoApellido.setText("");
		this.jtfDni.setText("");
		this.jtfDireccion.setText("");
		
	}
	
	public String getNombre() {
		return jtfNombre.getText();
	}
	
	public void setNombre(String nombre) {
		this.jtfNombre.setText(nombre);
	}
	
	public String getPrimerApellido() {
		return jtfPrimerApellido.getText();
		
	}
	
	public void setPrimerApellido(String primerApellido) {
		this.jtfPrimerApellido.setText(primerApellido);
	}
	
	public String getSegundoApellido() {
		return jtfSegundoApellido.getText();
		
	}
	
	public void setSegundoApellido(String segundoApellido) {
		this.jtfSegundoApellido.setText(segundoApellido);
	}
	
	public String getDni() {
		return jtfDni.getText();
		
	}
	
	public void setDni(String Dni) {
		this.jtfDni.setText(Dni);
	}
	
	public String getDireccion() {
		return jtfDireccion.getText();
		
	}
	
	public void setDireccion(String direccion) {
		this.jtfDireccion.setText(direccion);
	}
	
	public String getTelefono() {
		return jtfTelefono.getText();
		
	}
	
	public void setTelefono(String telefono) {
		this.jtfTelefono.setText(telefono);
	}
	
	public String getEmail() {
		return jtfEmail.getText();
		
	}
	
	public void setEmail(String email) {
		this.jtfEmail.setText(email);
	}
	
	public String getColorElegido() {
		return jtfColorElegido.getText();
	}
	
	public void setColorElegido(String colorElegido) {
		this.jtfColorElegido.setText(colorElegido);
		try {
			this.setBackground(Color.decode(colorElegido));
		} catch (Exception e) {
			this.setBackground(Color.GRAY);
		}
	}
	
	public void seleccionarColor() {
		Color color = (new JColorChooser().showDialog(null, "Elige un color", Color.gray));
		// cuando se elige un color, el color inicial deja de ser null
		if (color != null) {
			String strColor = "#"+Integer.toHexString(color.getRGB()).substring(2);
			this.jtfColorElegido.setText(strColor);
			this.setColorElegido(strColor);
		}
	}
	
	public Tipologiasexo getTipologiaSexo() {
		return (Tipologiasexo) this.jcbSexo.getSelectedItem();
	}
	
	public void setTipologiaSexo(Tipologiasexo sexoEscogido) {
		if(sexoEscogido == null && this.jcbSexo.getItemCount() > 0) {
			this.jcbSexo.setSelectedIndex(0);
		}
		else {
			for (int i = 0; i < this.jcbSexo.getItemCount(); i++) {
				Tipologiasexo sexoEnJCombo = this.jcbSexo.getItemAt(i);
				if(sexoEscogido.getId() == sexoEnJCombo.getId()){
					this.jcbSexo.setSelectedIndex(i);
				}
			}
		}
	}
	
	
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] iagen) {
		this.imagen = imagen;
		if(imagen != null && imagen.length > 0) {
			ImageIcon icono = new ImageIcon(this.imagen);
			JLabel jlblIcono = new JLabel(icono);
			jlblIcono.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseDragged(e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseEntered(e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseExited(e);
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseMoved(e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseReleased(e);
				}

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					// TODO Auto-generated method stub
					super.mouseWheelMoved(e);
				}
				
			});
		}
	}
	
//	private void mostrarMenu(MouseEvent e) {
//		if(e.isPopupTrigger()) {
//			JPopupMenu menu = new JPopupMenu();
//			menu.add(new JMenuItem(icono.getIconWidth() + "x" + icono.getIconeHeight() + "pixeles"));
//			menu.addSeparator();
//			JMenuItem miImagenSeleccionada = new JMenuItem("Seleccionar una imagen");
//			miImagenSeleccionada.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					seleccionarImagen();
//					
//				}
//			});
//			menu.add(miImagenSeleccionada);
//			menu.show(e.getComponent(), e.getX(), e.getY());
//		}
//	}
	
	public PanelGestionDatosPersonales(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionDatosPersonales(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionDatosPersonales(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}


