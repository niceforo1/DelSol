package controller;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public abstract class Controlador {
	protected static Properties config;
		static{
			try{
				config = new Properties();
				config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("controller/config.properties"));
			}
			catch (Exception e){
				System.out.println("Error al cargar config de controladores. Cerrando aplicacion");
				System.exit(-1);
			}
		}
	public Controlador(){}

	public static void callAction(String name, ParametrosAccion pars){
		try{
			Class c = Class.forName(config.getProperty(name+".class"));
			Controlador cont = (Controlador)c.newInstance();
			cont.execute(name, pars);
		}catch (Exception e){
			System.out.println("Error al llamar a la accion "+name+". Cerrando aplicacion");
			System.exit(-1);
		}
	}
	public static Object[] callCons(String name, ParametrosAccion pars){
		Object [] parm = new Object[4];
		try{
			Class c = Class.forName(config.getProperty(name+".class"));
			Controlador cont = (Controlador)c.newInstance();
			parm = cont.executeCons(name, pars);
		}catch (Exception e){
			System.out.println("Error al llamar a la accion "+name+". Cerrando aplicacion. ");
			System.exit(-1);
		}
		return parm;
	}

	public static List<String> callConsPart(String name, ParametrosAccion pars){
		List<String> lista = new ArrayList<String>();
		try{
			Class c = Class.forName(config.getProperty(name+".class"));
			Controlador cont = (Controlador)c.newInstance();
			lista = cont.executeConsPart(name, pars);
		}catch (Exception e){
			System.out.println("Error al llamar a la accion "+name+". Cerrando aplicacion. ");
			System.exit(-1);
		}
		return lista;
	}

	public static ResultSet callLLenarJTable(String name, ParametrosAccion pars){
		ResultSet rs = null;
		try{
			Class c = Class.forName(config.getProperty(name+".class"));
			Controlador cont = (Controlador)c.newInstance();
			rs = cont.executellenarJtable();
		}catch (Exception e){
			System.out.println("Error al llamar a la accion "+name+". Cerrando aplicacion. ");
			System.exit(-1);
		}
		return rs;
	}
	public static ResultSet callLLenarJTableVenta(String name, ParametrosAccion pars){
		ResultSet rs = null;
		try{
			Class c = Class.forName(config.getProperty(name+".class"));
			Controlador cont = (Controlador)c.newInstance();
			rs = cont.executellenarJtableVenta(pars);
		}catch (Exception e){
			System.out.println("Error al llamar a la accion "+name+". Cerrando aplicacion. ");
			System.exit(-1);
		}
		return rs;
	}

	public abstract void execute(String name, ParametrosAccion pars);
	public abstract Object[] executeCons(String name, ParametrosAccion pars);
	public abstract List<String> executeConsPart(String name, ParametrosAccion pars);
	public abstract ResultSet executellenarJtable();
	public abstract ResultSet executellenarJtableVenta(ParametrosAccion pars);
}
