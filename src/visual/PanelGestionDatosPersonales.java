package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import model.Tipologiasexo;
import model.controladores.TipologiaSexoControlador;
import utils.CacheImagenes;

public class PanelGestionDatosPersonales extends JPanel {
	
	 JTextField jtfId = new JTextField(10);
	 JTextField jtfNombre = new JTextField(20);
	 JTextField jtfPrimerApellido = new JTextField(20);
	 JTextField jtfSegundoApellido = new JTextField(20);
	 JTextField jtfDni= new JTextField(10);
	 JTextField jtfDireccion = new JTextField(40);
	 JTextField jtfEmail = new JTextField(20);
	 JTextField jtfTelefono = new JTextField(20);
	 JComboBox<Tipologiasexo> jcbSexo = new JComboBox<Tipologiasexo>();
	 JLabel jlbImagen = new JLabel();
	 JScrollPane jsp = new JScrollPane(jlbImagen);
	 byte[] imagen;
	 JButton jbtCambiarImg = new JButton("Elige imagen");
	 JLabel jlblColorElegido = new JLabel();
	 JTextField jtfColorElegido = new JTextField(20);
	 JButton jbtElegirColor = new JButton("Elige el color");
	 JFileChooser jfileChooser;
	 
	
	 
	

	public PanelGestionDatosPersonales() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		

		// añadimos los campos para ID
		c.fill = GridBagConstraints.BOTH;
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
//		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Nombre: "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		jtfNombre.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfNombre, c);
		
		// añadimos los campos para primerApellido
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
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
		c.gridwidth = 1;
		jtfDireccion.setEnabled(true);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfDireccion, c);
		
		// añadimos los campos para Teléfono
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
		
        // para añadir la imagen
//		c.weightx = 2;
		c.gridheight = 4;
		c.gridx = 2;
		c.gridy = 1;
//		c.gridwidth =1;
		//c.insets = new Insets(0,0,5,5);
		jsp.setPreferredSize(new Dimension(100, 100));
		jsp.setMaximumSize(new Dimension(100, 100));
		this.add(jsp, c);
		
//		c.weightx = 1;
		c.gridheight = 1;
		c.gridx = 2;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		this.add(jbtCambiarImg, c);
		jbtCambiarImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarImagen(imagen);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
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
		this.jtfColorElegido.setText("");
		this.setBackground(Color.gray);
		this.jsp.setViewportView(null);

		
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
			this.setBackground(color);
	
		}
	}
	
	
	
	public byte[] seleccionarImagen(byte[] imagen) throws IOException {
		this.jfileChooser = new JFileChooser();
		byte[] imagenSeleccionada = null;
		
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));
		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				
				return "Archivos para elegir *.jpg *.png *.gif";
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory() || f.isFile() && f.getAbsolutePath().endsWith(".jpg")
						|| f.getAbsolutePath().endsWith(".jpeg")
						|| f.getAbsolutePath().endsWith(".png")
						|| f.getAbsolutePath().endsWith(".gif")) 
					return true;
				return false;
			}

			
			});
		int seleccionUsuario = jfileChooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File fichero = this.jfileChooser.getSelectedFile();
			
			// Vuelco el nombre del fichero sobre el JTextField
			//this.jtfNombre.setText(fichero.getAbsolutePath());
			
			if (fichero.isFile()) {
				try {
					
					imagenSeleccionada = Files.readAllBytes(fichero.toPath());
					ImageIcon imagenProvisional = new ImageIcon(imagenSeleccionada);
					if(imagenProvisional.getIconWidth() > 800 || imagenProvisional.getIconHeight() > 800) {
						JOptionPane.showMessageDialog(null, "La imagen es demasiado grande");
						return imagen;
					}
						
					setImagen(imagenSeleccionada);
					return imagenSeleccionada;
					
//					FileReader fileReader = new FileReader(fichero);
//					BufferedReader bufferedReader = new BufferedReader(fileReader);
//			
//					StringBuffer sb = new StringBuffer();
//					String lineaDelFichero;
//			
//					// Lectura del fichero línea a línea
//					while ((lineaDelFichero = bufferedReader.readLine()) != null) {
//						sb.append(lineaDelFichero + "\n");
//					}
//					
//					// Volcamos el contenido del fichero al JTextArea
//					this.jtaContenidoFichero.setText(sb.toString());
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		imagenSeleccionada = imagen;
		return imagen;
		

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
	
	public void setImagen(byte[] imagen) {
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
				
				private void mostrarMenu(MouseEvent e) {
					if(e.isPopupTrigger()) {
						JPopupMenu menu = new JPopupMenu();
						menu.add(new JMenuItem(icono.getIconWidth() + "x" + icono.getIconHeight() + "pixeles"));
						menu.addSeparator();
						JMenuItem miImagenSeleccionada = new JMenuItem("Seleccionar una imagen");
						miImagenSeleccionada.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									seleccionarImagen(imagen);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						});
						menu.add(miImagenSeleccionada);
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
				
			});
			this.jsp.setViewportView(jlblIcono);
		}
		else {
			JLabel jlblIcono = new JLabel("No hay imagen");
			this.jsp.setViewportView(jlblIcono);
		}
	}

	
	public JScrollPane creaScrollPane(String nombreIcono) {
		JLabel jlb = new JLabel(CacheImagenes.getCacheImagenes().getIcono(nombreIcono));
		JScrollPane scrollPaneImagen = new JScrollPane(jlb);
		return scrollPaneImagen;
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


