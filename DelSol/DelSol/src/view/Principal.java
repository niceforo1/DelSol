package view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import controller.*;

public class Principal extends JFrame implements ActionListener{
	private JDesktopPane desk = new Fondo();

	public Principal() {
		crearMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//desk = new JDesktopPane();
		setContentPane(desk);
        Color cx = new Color(0,0,0);
		desk.setBackground(cx.getColor("GRAY"));
		//TAMAÑO SEGUN EL TAMAÑO DE LA IMAGEN DE FONDO
		setSize(568, 426);
		//desk.setBackground(cx.lightGray);
		setTitle("Tercer Tiempo");
		//setBounds(100, 100, 500, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void crearMenu(){
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu mArchivo = new JMenu("Archivo");
		menu.add(mArchivo);

		JMenuItem jmiProd = new JMenuItem("Venta");
		jmiProd.setActionCommand("ventas");
		jmiProd.addActionListener(this);
		mArchivo.add(jmiProd);

		JMenuItem jmiFact = new JMenuItem("Producto");
		jmiFact.setActionCommand("producto");
		jmiFact.addActionListener(this);
		mArchivo.add(jmiFact);

		JMenuItem jmiRep = new JMenuItem("Reportes");
		jmiRep.setActionCommand("reportes");
		jmiRep.addActionListener(this);
		mArchivo.add(jmiRep);

		JMenuItem salir = new JMenuItem("Salir");
		salir.setActionCommand("salir");
		salir.addActionListener(this);
		mArchivo.add(salir);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("producto")) {
			Principal p = new Principal();
			ParametrosAccion params = new ParametrosAccion();
			params.add("principal", p);
			params.add("desk", desk);
			Controlador.callAction("producto", params);
		} else if (e.getActionCommand().equals("ventas")) {
			Principal p = new Principal();
			ParametrosAccion params = new ParametrosAccion();
			params.add("principal", p);
			params.add("desk", desk);
			Controlador.callAction("venta", params);
		} else if (e.getActionCommand().equals("reportes")) {
			Principal p = new Principal();
			ParametrosAccion params = new ParametrosAccion();
			params.add("principal", p);
			params.add("desk", desk);
			Controlador.callAction("reportes", params);
		} else if (e.getActionCommand().equals("salir")) {
			System.exit(0);
		}
	}

	/*
	 * public Principal(){ setBounds(100,100,500,400); addWindowListener(new
	 * WindowAdapter(){ public void windowClosing(WindowEvent arg0){
	 * super.windowClosing(arg0); Controlador.callAction("logout",null); } });
	 * MenuItem miSalir = new MenuItem("Salir"); miSalir.addActionListener(new
	 * ActionListener(){ public void actionPerformed(ActionEvent e){
	 * Controlador.callAction("logout", null); } }); MenuItem miAgEmp = new
	 * MenuItem("Agregar Empleado"); miAgEmp.addActionListener(new
	 * ActionListener(){ public void actionPerformed(ActionEvent e){ Principal p
	 * = new Principal(); ParametrosAccion params = new ParametrosAccion();
	 * params.add("principal",p); Controlador.callAction("agemp", params); }
	 *
	 * });
	 *
	 * MenuItem miAgCli = new MenuItem("Agregar Cliente");
	 * miAgCli.addActionListener(new ActionListener(){ public void
	 * actionPerformed(ActionEvent e){ Principal p = new Principal();
	 * ParametrosAccion params = new ParametrosAccion(); params.add("principal",
	 * p); Controlador.callAction("agcli", params); } });
	 *
	 * Menu mArchivo = new Menu("Archivo"); mArchivo.add(miAgEmp);
	 * mArchivo.add(miAgCli); mArchivo.add(miSalir);
	 *
	 * MenuBar menu = new MenuBar(); menu.add(mArchivo); setMenuBar(menu); }
	 */
}
