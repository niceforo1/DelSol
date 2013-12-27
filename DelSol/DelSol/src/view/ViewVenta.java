package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.Producto;
import model.Venta;
import validaciones.*;
import controller.Controlador;
import controller.ParametrosAccion;
import dao.ModeloNuevo;
import dao.ModeloVenta;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class ViewVenta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane jDesktopPane = null;
	private JLabel jLIdProducto = null;
	private JComboBox jcbProducto = null;
	private JLabel jLabelCantidad = null;
	private JTextField jTCantidad = null;
	private JTextField jtPrecioVenta = null;
	private JButton jbVenta = null;
	private JButton jbCerrar = null;
	private JLabel jLPrecio = null;
	private JTextField jtPrecio = null;
	private JTextField jtCantCli = null;
	private JScrollPane jScrollPane = null;
	private JTable jtbVentas = null;
	private JScrollPane jSPClientes = null;
	private JTable jtbClientes = null;
	private JLabel jLCliente = null;
	private JButton jbNuevoCliente = null;
	private ModeloNuevo model = new ModeloNuevo();
	private ModeloVenta modelVenta = new ModeloVenta();
	private ResultSet rs= null;
	private JLabel jlTotalVenta = null;
	private boolean verifica = false;


	public ViewVenta() {
		super("Ventas",true,true,true,true);
		initialize();
	}

	private void initialize() {
		this.setSize(640, 515);
		this.setContentPane(getJDesktopPane());
		traerClientes();
	}

	private JDesktopPane getJDesktopPane() {

		if (jDesktopPane == null) {

			jlTotalVenta = new JLabel();
			jlTotalVenta.setBounds(new Rectangle(517, 435, 107, 33));
			jlTotalVenta.setHorizontalTextPosition(SwingConstants.RIGHT);
			jlTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
			jlTotalVenta.setForeground(Color.red);
			Font auxFont=jlTotalVenta.getFont();
			jlTotalVenta.setFont(new Font(auxFont.getFontName(), auxFont.getStyle(), 30));
			jLCliente = new JLabel();
			jLCliente.setBounds(new Rectangle(370, 13, 76, 16));
			jLCliente.setText("Cliente");
			jLPrecio = new JLabel();
			jLPrecio.setBounds(new Rectangle(30, 90, 61, 20));
			jLPrecio.setText("Precio");
			jLabelCantidad = new JLabel();
			jLabelCantidad.setBounds(new Rectangle(30, 60, 61, 20));
			jLabelCantidad.setText("Cantidad");
			jLIdProducto = new JLabel();
			jLIdProducto.setBounds(new Rectangle(30, 30, 61, 20));
			jLIdProducto.setText("Producto");
			jDesktopPane = new JDesktopPane();
			//jDesktopPane.setBackground(new Color(69, 64, 92));
			jDesktopPane.setBackground(new Color(0).LIGHT_GRAY);
			jDesktopPane.add(jLIdProducto, null);
			jDesktopPane.add(getJcbProducto(), null);
			jDesktopPane.add(jLabelCantidad, null);
			jDesktopPane.add(getJTCantidad(), null);
			jDesktopPane.add(jLPrecio, null);
			jDesktopPane.add(getJtPrecioVenta(),null);
			jDesktopPane.add(getJtPrecio(), null);
			jDesktopPane.add(getJtCantCli(), null);
			jDesktopPane.add(getJbVenta(), null);
			jDesktopPane.add(getJbCerrar(), null);
			jDesktopPane.add(getJScrollPane(), null);
			jDesktopPane.add(getJSPClientes(), null);
			jDesktopPane.add(jLCliente, null);
			jDesktopPane.add(getJbNuevoCliente(), null);
			jDesktopPane.add(jlTotalVenta, null);
		}
		return jDesktopPane;
	}

	private JComboBox getJcbProducto() {
		if (jcbProducto == null) {
			jcbProducto = new JComboBox();
			jcbProducto.setBounds(new Rectangle(105, 30, 121, 20));
			returnProductos();
			jcbProducto.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(jcbProducto.getSelectedItem() != ""){
						consultarProducto((String)jcbProducto.getSelectedItem());
					}else{
						//limpiarCampos();
					}
				}
			});
		}
		return jcbProducto;
	}

	private JTextField getJTCantidad() {
		if (jTCantidad == null) {
			jTCantidad = new JTextField();
			jTCantidad.setBounds(new Rectangle(105, 60, 121, 20));
			jTCantidad.addFocusListener(new FocusListener() {
				   public void focusLost(FocusEvent e) {
					   Float Resultado;
					   if(jTCantidad.getText().equals("")){
						   Resultado = Float.parseFloat(jtPrecioVenta.getText());
					   }else{
						   Resultado = Float.parseFloat(jTCantidad.getText()) * Float.parseFloat(jtPrecioVenta.getText());
					   }
					   jtPrecio.setText(Resultado.toString());
				   }
				   public void focusGained(FocusEvent e) {
				      // No hacemos nada
				   }
				});
		}
		return jTCantidad;
	}
////////////////////////////////////// Inicio PARAMETROS /////////////////////////////////////////
	private JTextField getJtPrecio() {
		if (jtPrecio == null) {
			jtPrecio = new JTextField();
			jtPrecio.setBounds(new Rectangle(105, 90, 121, 20));
			jtPrecio.setEditable(false);
		}
		return jtPrecio;
	}

	public JTextField getJtCantCli() {
		if(jtCantCli==null){
			jtCantCli= new JTextField();
			jtCantCli.setBounds(new Rectangle(105, 90, 121, 20));
			jtCantCli.setVisible(false);
		}
		return jtCantCli;
	}

	////////////////////////////////////// Fin PARAMETROS /////////////////////////////////////////
	private JButton getJbVenta() {
		if (jbVenta == null) {
			jbVenta = new JButton();
			jbVenta.setBounds(new Rectangle(30, 135, 76, 20));
			jbVenta.setText("Vender");
			jbVenta.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					grabarVenta();
					if (verifica != true){
						obtenerTotalVenta();
					}
					limpiarCampos();
				}
			});
		}
		return jbVenta;
	}
	public JButton getJbCerrar() {
		if (jbCerrar == null) {
			jbCerrar = new JButton();
			jbCerrar.setBounds(new Rectangle(498, 185, 73, 20));
			jbCerrar.setText("Cerrar");
			jbCerrar.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cerrarCliente();
					jlTotalVenta.setText("");
				}
			});
		}
		return jbCerrar;
	}

	private JButton getJbNuevoCliente() {
		if (jbNuevoCliente == null) {
			jbNuevoCliente = new JButton();
			jbNuevoCliente.setBounds(new Rectangle(370, 185, 73, 20));
			jbNuevoCliente.setText("Nuevo");
			jbNuevoCliente.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//pintarGrillaCli();
					agregarCliente();
				}
			});
		}
		return jbNuevoCliente;
	}


	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(30, 225, 591, 196));
			jScrollPane.setViewportView(getJtbVentas());
		}
		return jScrollPane;
	}

	private JTable getJtbVentas() {
		if (jtbVentas == null) {
			jtbVentas = new JTable(modelVenta);
		}
		return jtbVentas;
	}


	private JScrollPane getJSPClientes() {
		if (jSPClientes == null) {
			jSPClientes = new JScrollPane();
			jSPClientes.setBounds(new Rectangle(370, 30, 250, 151));
			jSPClientes.setViewportView(getJtbClientes());
		}
		return jSPClientes;
	}

	public JTextField getJtPrecioVenta() {
		if(jtPrecioVenta == null){
			jtPrecioVenta = new JTextField();
			jtPrecioVenta.setBounds(new Rectangle(105, 60, 106, 20));
			jtPrecioVenta.setVisible(false);
		}
		return jtPrecioVenta;
	}

	private JTable getJtbClientes() {
		if (jtbClientes == null){
			pintarGrillaCli();
			jtbClientes = new JTable(model);
			jtbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount() == 2){
						if(!jtbClientes.isRowSelected(jtbClientes.getSelectedRow())){
							JOptionPane.showMessageDialog(null,"Por Favor elija un cliente.",null,JOptionPane.INFORMATION_MESSAGE);
						}else{
							pintarGrillaVenta();
							obtenerTotalVenta();
						}
					}
				}
			});
		}
		return jtbClientes;
	}


//////////////////////////////METODOS////////////////////////

	protected void returnProductos (){
		String prueba = null;
		List<String> list = new ArrayList<String>();
		ParametrosAccion params = new ParametrosAccion();
		params.add("consRetProd",this);
		list  = Controlador.callConsPart("consRetProd", params);
		Iterator iterList = list.iterator();
		jcbProducto.addItem("");
		while(iterList.hasNext()){
			prueba = iterList.next().toString();
			jcbProducto.addItem(prueba);
		}
	}

	protected void consultarProducto(String producto){
		Float res = Float.parseFloat("0");
		Object[] obj = new Object[4];
		ParametrosAccion params = new ParametrosAccion();
		params.add("consProd",this);
		params.add("producto", producto);
		obj = Controlador.callCons("consProd", params);
		jtPrecioVenta.setText(obj[3].toString());
		if(jTCantidad.getText().equals("")){
			jtPrecio.setText(jtPrecioVenta.getText());
		}else{
			res = Float.parseFloat(jtPrecioVenta.getText()) * Float.parseFloat(jTCantidad.getText());
			jtPrecio.setText(Float.toString(res));
		}
	}

	public void agregarCliente(){
		String cliente;
		Integer numeroCli;
		ParametrosAccion params = new ParametrosAccion();
		JTextField nombreCli = new JTextField();
		Object[] obj = {"Por Favor Ingrese el nombre del cliente:\n\n", nombreCli};
		Object stringArray[] = {"OK","Cancel"};
		if (JOptionPane.showOptionDialog(null, obj, "Loguear",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION){
			cliente = nombreCli.getText();
			if(cliente.equals("")){
				verifica= true;
				JOptionPane.showMessageDialog(null, "Por Favor Ingrese el nombre del Cliente", "Error Cliente", JOptionPane.ERROR_MESSAGE);
			}else{
				if(!jtCantCli.getText().equals("")){
					numeroCli = Integer.parseInt(jtCantCli.getText());
					numeroCli++;
					jtCantCli.setText(numeroCli.toString());
				}else{
					numeroCli = 0;
					numeroCli++;
					jtCantCli.setText(numeroCli.toString());
				}
				params.add("cargaCli", this);
				params.add("cliente", cliente);
				params.add("numeroCli", jtCantCli.getText());
				Controlador.callAction("cargaCli", params);
				Cliente c = new Cliente();
				c.setNumeroCliente(Integer.parseInt(jtCantCli.getText()));
				c.setNombre(cliente);
				c.setPago(false);
				model.addCliente(c);
				JOptionPane.showMessageDialog(null,"Se agrego el Cliente.",null,JOptionPane.INFORMATION_MESSAGE);
			}
		}else{

		}
	}

	protected void traerClientes(){
		Integer cant = 0;
		String prueba = null;
		List<String> list = new ArrayList<String>();
		ParametrosAccion params = new ParametrosAccion();
		params.add("consTotCli", this);
		params.add("cantidad", cant);
		list = Controlador.callConsPart("consTotCli", params);
		//System.out.println(list.toString());
		Iterator iterList = list.iterator();
		while(iterList.hasNext()){
			prueba = iterList.next().toString();
			//jcbProducto.addItem(prueba);
			jtCantCli.setText(prueba);
		}

	}

	protected void pintarGrillaCli(){
		ParametrosAccion params = new ParametrosAccion();
		try{
			rs = Controlador.callLLenarJTable("consCli", params);
			while(rs.next()){
				Cliente c = new Cliente();
				c.setNumeroCliente(Integer.parseInt(rs.getString("Numero")));
				c.setNombre(rs.getString("Cliente"));
				if(rs.getString("Pago") == "Y"){
					c.setPago(true);
				}else{
					c.setPago(false);
				}
				model.addCliente(c);
			}
		}catch(SQLException sqle){
			System.out.print("Error");
		}
	}

	protected void pintarGrillaVenta(){
		ParametrosAccion params = new ParametrosAccion();
		Cliente cli = new Cliente();
		modelVenta.vaciarModeloVenta();
		cli = model.retornarCliente(jtbClientes.getSelectedRow());
		params.add("cliente", cli);
		try{
			rs = Controlador.callLLenarJTableVenta("consVenta", params);
			while(rs.next()){
				Venta venta = new Venta();
				Producto prod = new Producto();
				prod.setNombre(rs.getString("Producto"));
				prod.setPrecioVenta(Float.parseFloat(rs.getString("Precio")));
				venta.setCantidad(Integer.parseInt(rs.getString("Cantidad")));
				venta.setProduct(prod);
				modelVenta.addVenta(venta);
			}
		}catch(SQLException sqle){
			System.out.print("Error");
		}
	}
	protected void grabarVenta(){
		ValidacionesVista valid = new ValidacionesVista();
		if(valid.validarVenta(jcbProducto.getSelectedItem().toString(), jTCantidad.getText(), jtPrecio.getText())){
			Cliente cli = new Cliente();
			Venta venta = new Venta();
			Producto prod = new Producto();
			prod.setNombre(jcbProducto.getSelectedItem().toString());
			prod.setPrecioVenta(Float.parseFloat(jtPrecio.getText()));
			ParametrosAccion params = new ParametrosAccion();
			params.add("guarVenta", this);
			params.add("producto", jcbProducto.getSelectedItem().toString());
			params.add("cantidad", jTCantidad.getText());
			params.add("precio", jtPrecio.getText());
			if(!jtbClientes.isRowSelected(jtbClientes.getSelectedRow())){
				JOptionPane.showMessageDialog(null,"Por Favor elija un cliente.",null,JOptionPane.INFORMATION_MESSAGE);
				//limpiarCampos();
			}else{
				//String result = model.getValueAt(jtbClientes.getSelectedRow(), jtbClientes.getSelectedColumn()).toString();
				cli = model.retornarCliente(jtbClientes.getSelectedRow());
				params.add("cliente", cli);
				venta.setClient(cli);
				venta.setCantidad(Integer.parseInt(jTCantidad.getText()));
				venta.setProduct(prod);
				modelVenta.addVenta(venta);
				Controlador.callAction("guarVenta", params);
				pintarGrillaVenta();
			}
		}
	}

	private void cerrarCliente() {		//
		if(!jtbClientes.isRowSelected(jtbClientes.getSelectedRow())){
			JOptionPane.showMessageDialog(null,"Elija el cliente que desea cerrar.",null,JOptionPane.INFORMATION_MESSAGE);
		}else{
			ParametrosAccion params = new ParametrosAccion();
			params.add("cliente",model.retornarCliente(jtbClientes.getSelectedRow()));
			Controlador.callAction("cerrarCli", params);
			modelVenta.vaciarModeloVenta();
			model.vaciarModeloNuevo();
			pintarGrillaCli();
			limpiarCampos();
		}
	}

	private void obtenerTotalVenta() {
		Object[] obj = new Object[1];
		ParametrosAccion params = new ParametrosAccion();
		params.add("cliente",model.retornarCliente(jtbClientes.getSelectedRow()));
		obj = Controlador.callCons("consVentTot", params);
		jlTotalVenta.setText(obj[0].toString());
	}

	private void limpiarCampos(){
		jTCantidad.setText("");
		jtPrecio.setText("");
		jcbProducto.setSelectedIndex(0);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
