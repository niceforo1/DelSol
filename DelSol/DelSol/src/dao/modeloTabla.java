package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

import model.Cliente;

public class modeloTabla extends AbstractTableModel {
	public ResultSet rs;
	public ResultSetMetaData rsmd;

	public String getColumnName(int c) {
		try {
			if (rsmd != null)
				return rsmd.getColumnName(c + 1);
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	/*************** getColumnCount() ******************/
	public int getColumnCount() {
		try {
			if (rsmd != null)
				return rsmd.getColumnCount();
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/****************** getRowCount() *******************/
	public int getRowCount() {
		try {
			if (rs != null) {
				rs.last(); // Nos situamos en la última fila
				return rs.getRow(); // Devolvemos el número de la fila
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/******************* getValueAt() *********************/
	public Object getValueAt(int fila, int col) {
		try {
			if (rs != null) {
				rs.absolute(fila + 1);
				return rs.getObject(col + 1);
			}
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addCliente(Cliente c)
	{
		//rs.insertRow();
		this.fireTableDataChanged();
		
	}
	
}
