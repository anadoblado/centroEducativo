package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

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
import model.controladores.TipologiaSexoControlador;

public class PanelGestionDatosPersonales extends JPanel {
	
	 public JTextField jtfId = new JTextField(10);
	 JTextField jtfNombre = new JTextField(20);
	 JTextField jtfPrimerApellido = new JTextField(20);
	 JTextField jtfSegundoApellido = new JTextField(20);
	 JTextField jtfDni= new JTextField(10);
	 JTextField jtfDireccion = new JTextField(40);
	 JTextField jtfEmail = new JTextField(20);
	 JTextField jtfTelefono = new JTextField(20);
	 JComboBox<Tipologiasexo> jcbSexo = new JComboBox<Tipologiasexo>();
	 JScrollPane scrollPaneImagen;
	// byte[] imagen;
	 JButton jbtCambiarImg = new JButton("Elige imagen");
	 JLabel jlblColorElegido = new JLabel();
	 JTextField jtfColorElegido = new JTextField(20);
	 JButton jbtElegirColor = new JButton("Elige el color");
	//JPanel jpPaneAColorear = new JPanel();
	

	public PanelGestionDatosPersonales() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		// añadimos los campos para nombre
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Id: "), c);
		
		c.gridx = 1;
		c.gridy = 0;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);
		
		// añadimos los campos para nombre
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Nombre: "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		jtfNombre.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, c);
		
		// añadimos los campos para primerApellido
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Primer Apellido: "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		jtfPrimerApellido.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfPrimerApellido, c);
		
		// añadimos los campos para segundoApellido
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Segundo Apellido: "), c);
		
		c.gridx = 1;
		c.gridy = 3;
		jtfSegundoApellido.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfSegundoApellido, c);

		// añadimos los campos para Dni
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dni: "), c);
		
		c.gridx = 1;
		c.gridy = 4;
		jtfDni.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDni, c);
		
		// añadimos los campos para Dirección
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Dirección: "), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		jtfDireccion.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDireccion, c);
		
		// añadimos los campos para Teléfono
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Teléfono: "), c);
		
		c.gridx = 1;
		c.gridy = 6;
		jtfTelefono.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfTelefono, c);

		// añadimos los campos para Email
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Email: "), c);
		
		c.gridx = 1;
		c.gridy = 7;
		jtfEmail.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfEmail, c);
		
		// incluimos JComboBox para el Sexo
		List<Tipologiasexo> tipo = TipologiaSexoControlador.getInstancia().findAllTipologiasSexo();
		for (Tipologiasexo ti : tipo) {
			jcbSexo.addItem(ti);
		}
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Sexo: "), c);
		
		c.gridx = 1;
		c.gridy = 8;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbSexo, c);
		
		// añadimos los campos para Color
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		this.add( new JLabel("Color: "), c);
		
		
		
		c.gridx = 1;
		c.gridy = 9;
		jtfColorElegido.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfColorElegido, c);
//		panelGestion.add(this.jpPaneAColorear, c);
//		panelGestion.setBackground(color);
		
		c.gridx = 2;
		c.gridy = 9;
		c.anchor = GridBagConstraints.WEST;
		this.add(jbtElegirColor, c);
		
		
		jbtElegirColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarColor();
				
			}
		});





		
		
	}

	
	public void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtfNombre.setText("");
		this.jtfPrimerApellido.setText("");
		this.jtfSegundoApellido.setText("");
		this.jtfDni.setText("");
		this.jtfDireccion.setText("");
		this.jtfTelefono.setText("");
		this.jtfEmail.setText("");
		this.jcbSexo.setSelectedIndex(0);
		
	}
	
	public String getId() {
		return jtfId.getText();
	}
	
	public void setId(String id) {
		this.jtfId.setText(id);
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
			System.out.println("No cambio");
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
//			this.jpPaneAColorear.setBackground(color);
			this.setBackground(color);
	
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


