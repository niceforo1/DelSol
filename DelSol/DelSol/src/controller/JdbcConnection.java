package controller;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcConnection{
	protected static Properties p;
	protected Connection con;

	static{
		try{
			p=new Properties();
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dao/db.config"));
		}catch (IOException e ) {
			System.out.println(JdbcConnection.class.getSimpleName() + ": No se pudoabrirdb.config");
			System.exit(0);
		}
	}

	//******************* procedimiento para conectar *******************//
	public Connection conectar(){
		con = null;
		try{
			if(con ==null){
				Class.forName(p.getProperty("driver"));
				con = DriverManager.getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));
			}
		}catch (Exception e){
			System.out.println(getClass().getSimpleName() + ": No se pudoconectar a la base: " + e.getMessage());
			System.exit(0);
		}
		return con;
	}
	//******************* procedimiento para cerrar conexion *******************//
	protected void cerrar(){
		try{
			con.close();
		}catch (Exception e){
			System.out.println(getClass().getSimpleName() + ": No se pudo cerrar la base: " + e.getMessage());
		}
	}


	//******************* procedimiento para consultar datos *******************//
	public List<List<String>> consulta(String sql){
		Statement st = null;
		ResultSet rs = null;
		List<List<String>> ret = null;
		try{
			st = conectar().createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			List<String> fila = new ArrayList<String>();
			for (int i = 0;i<cols;i++){
				fila.add(rsmd.getColumnName((i+1)));
			}
			ret = new ArrayList<List<String>>();
			ret.add(fila);

			while (rs.next()){
				fila = new ArrayList<String>();
				for (int i = 0;i<cols;i++){
					fila.add(rs.getString((i+1)));
				}
				ret.add(fila);
			}
		}
		catch (Exception e){
			System.out.println(getClass().getSimpleName() + ": No se pudoejecutar la consulta: " + e.getMessage());
			return null;
		}
		finally {
			try{
				rs.close();
			}
			catch (SQLException e){
				System.out.println(getClass().getSimpleName()+": No se puedecerrar el objeto "+e.getMessage());
			}
			try{
				st.close();
			}
			catch(SQLException e1){
				System.out.println(getClass().getSimpleName()+": No se puede cerrar el objeto "+e1.getMessage());
			}
			cerrar();
		}
		return ret;
	}
	//******************* procedimiento para insertar datos *******************//
	public boolean actualizar(String sql, Object[][] params){
		PreparedStatement pr = null;
		int paramcount = 0;
		try{
			pr=conectar().prepareStatement(sql);
			ParameterMetaData pmd = pr.getParameterMetaData();
			/*if (params[0][0].equals("empleado")){
				pr.setString(1, (String)params[0][1]);
				pr.setString(2, (String)params[0][2]);
				pr.setString(3, (String)params[0][3]);
				pr.setString(4, (String)params[0][4]);
			}else if (params[0][0].equals("cliente")){

			}else if (params[0][0].equals("reclamo")){

			}*/
			for(int i = 1; i<params[0].length;i++){
				if(params[0][i].getClass().equals(Float.class)){
					pr.setFloat(i, (Float)params[0][i]);
				}else{//(params[0][i].getClass().equals(String.class)){
					pr.setString(i, (String)params[0][i]);
				}
				/*}else if(params[0][i].getClass().equals(Integer.class)){
					System.out.println("puto3");
					pr.setInt(i, Integer.getInteger((String)params[0][i]));
				}*/
			}

			/*for(Object[] fila:params){
				for(int i = 0;i<fila.length;i++){
					switch (pmd.getParameterType((i+1))){
						case Types.INTEGER:pr.setInt((i+1), (Integer)fila[i]);
						System.out.println(4);
							break;
						case Types.FLOAT:pr.setFloat((i+1), (Float)fila[i]);
							break;
						case Types.DOUBLE:pr.setDouble((i+1), (Double)fila[i]);
							break;
						case Types.VARCHAR:pr.setString((i+1), (String)fila[i]);
						System.out.println(5);
							break;
						default:
							System.out.println(6);
							pr.setString((i+1), (String)fila[i]);
							break;
					}
				}
			}*/
			pr.execute();
		}catch (Exception e){
			System.out.println(getClass().getSimpleName() + ": No se pudo ejecutar la consulta: " + e.getMessage());
			return false;
		}finally{
			try{
				pr.close();
			}catch(SQLException e){
				System.out.println(getClass().getSimpleName()+": No se puede cerrar el objeto "+e.getMessage());
			}
			cerrar();
		}
		return true;
	}
	//******************* procedimiento para ejecutar procedimientos almacenados en base*******************//
	public void procedimiento(String sql, Object[] in){
		CallableStatement cs = null;
		try{
			cs = conectar().prepareCall("{call"+sql+"}");
			ParameterMetaData pmd = cs.getParameterMetaData();
			for(int i = 0;i<in.length;i++){
				switch (pmd.getParameterType((i++))){
				case Types.INTEGER:cs.setInt((i+1), (Integer)in[i]);
					break;
				case Types.FLOAT:cs.setFloat((i+1), (Float)in[i]);
					break;
				case Types.DOUBLE:cs.setDouble((i+1), (Double)in[i]);
					break;
				case Types.VARCHAR:cs.setString((i+1), (String)in[i]);
					break;
				default:
					cs.setString((i+1), (String)in[i]);
					break;
				}
			}
			cs.execute();
		}catch(Exception e){
			System.out.println(getClass().getSimpleName()+": No se puede ejecutar el procedimiento "+e.getMessage());
		}finally{
			try{
				cs.close();
			}catch(SQLException e){
				System.out.println(getClass().getSimpleName()+": No se puede cerrar el objeto "+e.getMessage());
			}
			cerrar();
		}
	}

	public ResultSet llenarJTable(String sql){
		Statement stat;
		try{
			con = conectar();
			stat = con.createStatement();
			//Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tercer_tiempo","root","admin");
			//stat = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//Connection cn = DriverManager.getConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("password"));
			//stat = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return stat.executeQuery(sql);
			//return stat.executeQuery("select * from clientes where fecha = '2012-02-07'");
		}catch(Exception e){
			System.out.println("Error consultado Resultset" + e.getMessage());
		}
		return null;

	}


}//fin de la clase principal