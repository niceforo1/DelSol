package view;
import validaciones.*;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JButton;

import controller.ConsAction;
import controller.Controlador;
import controller.ParametrosAccion;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewProducto extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	private JDesktopPane jDesktopPane = null;
	private JLabel jLProducto = null;
	private JLabel jlCosto = null;
	private JLabel jlGanancia = null;
	private JLabel jlVenta = null;
	private JTextField jtCosto = null;
	private JTextField jtGanancia = null;
	private JTextField jtProducto = null;
	private JTextField jtVenta = null;
	private JButton jbGrabar = null;
	private JButton jbConsultar = null;
	private JButton jbModificar = null;
	private JComboBox jcProducto = null;
	private JTextField jtPass= null;

	public ViewProducto() {
		super("Producto",true,true,true,true);
		initialize();
	}


	private void initialize() {
		this.setSize(300, 480);
		this.setContentPane(getJDesktopPane());
	}


	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jlVenta = new JLabel();
			jlVenta.setBounds(new Rectangle(30, 120, 61, 20));
			jlVenta.setText("Venta");
			jlGanancia = new JLabel();
			jlGanancia.setBounds(new Rectangle(30, 90, 61, 20));
			jlGanancia.setText("Ganancia");
			jlCosto = new JLabel();
			jlCosto.setBounds(new Rectangle(30, 60, 61, 20));
			jlCosto.setText("Costo");
			jLProducto = new JLabel();
			jLProducto.setBounds(new Rectangle(30, 30, 61, 20));
			jLProducto.setText("Producto");
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(new Color(0).lightGray);
			jDesktopPane.add(jLProducto, null);
			jDesktopPane.add(getJtProducto(), null);
			jDesktopPane.add(jlCosto, null);
			jDesktopPane.add(getJtCosto(), null);
			jDesktopPane.add(getJcProducto(),null);
			jDesktopPane.add(jlGanancia, null);
			jDesktopPane.add(getJtGanancia(), null);
			jDesktopPane.add(jlVenta, null);
			jDesktopPane.add(getJtPass(),null);
			jDesktopPane.add(getJtVenta(), null);
			jDesktopPane.add(getJbGrabar(), null);
			jDesktopPane.add(getJbConsultar(),null);
			jDesktopPane.add(getJbModificar(),null);
		}
		return jDesktopPane;
	}


	private JTextField getJtProducto() {
		if (jtProducto == null) {
			jtProducto = new JTextField();
			jtProducto.setBounds(new Rectangle(105, 30, 106, 20));
		}
		return jtProducto;
	}


	private JTextField getJtCosto() {
		if (jtCosto == null) {
			jtCosto = new JTextField();
			jtCosto.setBounds(new Rectangle(105, 60, 106, 20));
			jtCosto.addFocusListener(new FocusListener() {
				   public void focusLost(FocusEvent e) {
					   Float Resultado;
					   if (jtCosto.getText().equals("") & jtGanancia.getText().equals("") ){
						   Resultado = Float.parseFloat("0");
					   }else{
						   if(jtGanancia.getText().equals("")){
							   Resultado = Float.parseFloat(jtCosto.getText());
						   }else{
							   Resultado = Float.parseFloat(jtCosto.getText())+Float.parseFloat(jtGanancia.getText());
						   }
					   }
					   jtVenta.setText(Resultado.toString());
				   }
				   public void focusGained(FocusEvent e) {
				      // No hacemos nada
				   }
				});
		}
		return jtCosto;
	}

	private JTextField getJtGanancia() {
		if (jtGanancia == null) {
			jtGanancia = new JTextField();
			jtGanancia.setBounds(new Rectangle(105, 90, 106, 20));
			jtGanancia.addFocusListener(new FocusListener() {
				   public void focusLost(FocusEvent e) {
					   Float Resultado;
					   if(jtGanancia.getText().equals("") & jtCosto.getText().equals("")){
						   Resultado = Float.parseFloat("0");
					   }else{
						   if(jtCosto.getText().equals("")){
							   Resultado = Float.parseFloat(jtGanancia.getText());
						   }else if(jtGanancia.getText().equals("")){
							   Resultado = Float.parseFloat(jtCosto.getText());
						   }else{
							   Resultado = Float.parseFloat(jtCosto.getText())+Float.parseFloat(jtGanancia.getText());
						   }
					   }
					   jtVenta.setText(Resultado.toString());
				   }
				   public void focusGained(FocusEvent e) {
				      // No hacemos nada
				   }
				});
		}
		return jtGanancia;
	}

	private JTextField getJtVenta() {
		if (jtVenta == null) {
			jtVenta = new JTextField();
			jtVenta.setBounds(new Rectangle(105, 120, 106, 20));
			jtVenta.setEditable(false);
		}
		return jtVenta;
	}


	private JButton getJbGrabar() {
		if (jbGrabar == null) {
			jbGrabar = new JButton();
			jbGrabar.setBounds(new Rectangle(30, 180, 73, 31));
			jbGrabar.setText("Grabar");
			jbGrabar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jbGrabar.getText()== "Nuevo"){
						jcProducto.setVisible(false);
						jtProducto.setVisible(true);
						jtProducto.setEnabled(true);
						jtGanancia.setEditable(true);
						jbGrabar.setText("Grabar");
						jtCosto.setEditable(true);
						limpiarCampos();
					}else if(jbGrabar.getText()== "Grabar"){
						grabarProducto();
					}

				}
			});
		}
		return jbGrabar;
	}


	public JButton getJbConsultar() {
		if (jbConsultar == null) {
			jbConsultar = new JButton();
			jbConsultar.setBounds(new Rectangle(105, 180, 73, 31));
			jbConsultar.setText("Consulta");
			jbConsultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jbGrabar.setText("Nuevo");
					jtProducto.setEnabled(false);
					jtProducto.setVisible(false);
					jtGanancia.setEditable(false);
					jcProducto.setVisible(true);
					jcProducto.setEnabled(true);
					jtCosto.setEditable(false);
					if(jcProducto.getItemCount()==0){
						returnProductos();
					}
				}
			});
		}
		return jbConsultar;
	}


	public JButton getJbModificar() {
		if (jbModificar == null) {
			jbModificar = new JButton();
			jbModificar.setBounds(new Rectangle(180, 180, 73, 31));
			jbModificar.setText("Modificar");
			jbModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jbGrabar.getText().equals("Nuevo")){
						if(jbModificar.getText().equals("Modificar")){
							verificarDatos();
						}else if(jbModificar.getText().equals("Confirmar")){
							modificarProducto();
							System.out.println("vamos por buen rumbo");
						}
					}

				}
			});
		}
		return jbModificar;
	}

	public JComboBox getJcProducto() {
		if(jcProducto == null){
			jcProducto = new JComboBox();
			jcProducto.setBounds(new Rectangle(105, 30, 106, 20));
			jcProducto.setEnabled(false);
			jcProducto.setVisible(false);
			jcProducto.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(jcProducto.getSelectedItem() != ""){
						consultarProducto((String)jcProducto.getSelectedItem());
					}else{
						limpiarCampos();
					}
				}
			});
		}
		return jcProducto;
	}

	public JTextField getJtPass() {
		if(jtPass == null){
			jtPass = new JTextField();
			jtPass.setBounds(new Rectangle(105, 30, 106, 20));
			jtPass.setEditable(false);
			jtPass.setEnabled(false);
			jtPass.setVisible(false);
			jtPass.setText("hoytambienjuego");

		}
		return jtPass;
	}

//////////////////////////////// METODOS ///////////////////////////

	protected void grabarProducto(){
		boolean valido = false;
		ValidacionesVista valid = new ValidacionesVista();
		valido = valid.validarProducto(jtProducto.getText(),
									   jtCosto.getText(),
									   jtGanancia.getText(),
									   jtVenta.getText());
		if(valido){
			ParametrosAccion params = new ParametrosAccion();
			params.add("guarProd", this);
			params.add("producto", jtProducto.getText());
			params.add("costo", jtCosto.getText());
			params.add("ganancia", jtGanancia.getText());
			params.add("venta", jtVenta.getText());
			Controlador.callAction("cargaProd", params);
			limpiarCampos();
			JOptionPane.showMessageDialog(null,"El Producto se agrego correctamente.",null,JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void modificarProducto(){
		boolean valido = false;
		ParametrosAccion params = new ParametrosAccion();
		ValidacionesVista valid = new ValidacionesVista();
		valido = valid.validarProducto(jcProducto.getSelectedItem().toString(),
									   jtCosto.getText(),
									   jtGanancia.getText(),
									   jtVenta.getText());
		if(valido){
			params.add("actualizaProd", this);
			params.add("producto", jcProducto.getSelectedItem());
			params.add("costo", jtCosto.getText());
			params.add("ganancia", jtGanancia.getText());
			params.add("venta", jtVenta.getText());
			Controlador.callAction("actuProd", params);
			limpiarCampos();
			JOptionPane.showMessageDialog(null,"El Producto se modifico correctamente.",null,JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void consultarProducto(String producto){
		Object[] obj = new Object[4];
		ParametrosAccion params = new ParametrosAccion();
		params.add("consProd",this);
		params.add("producto", producto);
		obj = Controlador.callCons("consProd", params);
		jtCosto.setText(obj[1].toString());
		jtGanancia.setText(obj[2].toString());
		jtVenta.setText(obj[3].toString());
	}

	protected void returnProductos (){
		String prueba = null;
		List<String> list = new ArrayList<String>();
		ParametrosAccion params = new ParametrosAccion();
		params.add("consRetProd",this);
		list  = Controlador.callConsPart("consRetProd", params);
		Iterator iterList = list.iterator();
		jcProducto.addItem("");
		while(iterList.hasNext()){
			prueba = iterList.next().toString();
			jcProducto.addItem(prueba);
		}
	}



	protected void limpiarCampos(){
		jtGanancia.setText("");
		jtCosto.setText("");
		jtVenta.setText("");
		if(jtProducto.isVisible()){
			jtProducto.setText("");
		}
	}

	protected void verificarDatos(){
		String password;
		JPasswordField passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		Object[] obj = {"Por Favor Ingrese la contraseña:\n\n", passwordField};
		Object stringArray[] = {"OK","Cancel"};
		if (JOptionPane.showOptionDialog(null, obj, "Loguear",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION){
			//password = passwordField.getPassword().toString();
			password = passwordField.getText();
			if(password.equals(jtPass.getText())){
				jtCosto.setEditable(true);
				jtGanancia.setEditable(true);
				jcProducto.setEnabled(false);
				jbModificar.setText("Confirmar");
				System.out.println("Se logueo bien");
			}else{
				System.out.println("Se logueo mal");
			}
		}
	}

}

