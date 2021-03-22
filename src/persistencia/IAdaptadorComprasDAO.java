package persistencia;



import modelo.Compras;

public interface IAdaptadorComprasDAO {
	public boolean registrarCompra(AccesoBd conexion,Compras compra);
	public double recuperarPrecioCompras(AccesoBd conexion);
}
