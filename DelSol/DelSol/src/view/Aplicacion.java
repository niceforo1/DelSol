package view;

import javax.swing.UIManager;

import controller.*;

public class Aplicacion {
	public static void main(String[] args){
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
			//UIManager.setLookAndFeel("napkin.NapkinLookAndFeel");
		}catch (Exception e){
			e.printStackTrace();
		}
		Principal p = new Principal();
		p.setVisible(true);
		ParametrosAccion params = new ParametrosAccion();
		params.add("principal",p);
		Controlador.callAction("loginForm", params);
	}


}
