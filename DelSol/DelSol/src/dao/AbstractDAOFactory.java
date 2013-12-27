package dao;

public abstract class AbstractDAOFactory {
	public static final int MYSQL=1;
	public static final int FILE=2;

	public AbstractDAOFactory(){}

	public static AbstractDAOFactory getInstance(int f){
		switch (f) {
		case MYSQL:
			return new MysqlDAOFactory();
		default:
			return null;
		}
	}
	public abstract ProductoDAO getProductoDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract VentaDAO getVentaDAO();
}