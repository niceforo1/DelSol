package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

import controller.Controlador;
import controller.ParametrosAccion;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.freixas.jcalendar.JCalendarCombo;

public class ViewReportes extends JInternalFrame {

	private JDesktopPane jDesktopPane = null;
	private JButton jbReporte = null;
	private JCalendarCombo jCBFechaInicio = null;
	private JCalendarCombo jCBFechaFin = null;


	public ViewReportes() {
		super("Reportes",true,true,true,true);
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJDesktopPane());
	}

	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(new Color(0).lightGray);
			jDesktopPane.add(getJbReporte(), null);
			jDesktopPane.add(getJCBFechaInicio(), null);
			jDesktopPane.add(getJCBFechaFin(), null);
		}
		return jDesktopPane;
	}

	private JButton getJbReporte() {
		if (jbReporte == null) {
			jbReporte = new JButton();
			jbReporte.setBounds(new Rectangle(90, 120, 105, 30));
			jbReporte.setText("Reporte");
			jbReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarReporte();
				}
			});
		}
		return jbReporte;
	}

	private JCalendarCombo getJCBFechaInicio() {
		if (jCBFechaInicio == null) {
			jCBFechaInicio = new JCalendarCombo();
			jCBFechaInicio.setBounds(new Rectangle(75, 15, 148, 25));
		}
		return jCBFechaInicio;
	}


	private JCalendarCombo getJCBFechaFin() {
		if (jCBFechaFin == null) {
			jCBFechaFin = new JCalendarCombo();
			jCBFechaFin.setBounds(new Rectangle(75, 45, 148, 25));
		}
		return jCBFechaFin;
	}

	private void mostrarReporte(){
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		ParametrosAccion params = new ParametrosAccion();
		sdf.format(jCBFechaInicio.getDate());
		params.add("fechaInicio", sdf.format(jCBFechaInicio.getDate()));
		params.add("fechaFin", sdf.format(jCBFechaFin.getDate()));
		params.add("genRep",this);
		Controlador.callAction("genReporteVentas", params);
	}
}
