package persistencia;


import modelo.Produccion;
import modelo.ProductoFinal;


public interface IAdaptadorProduccionDAO {
	public boolean registrarProduccion(AccesoBd conexionBD, Produccion produccion);
	public double getCosteProduccion(AccesoBd conexionBD,ProductoFinal productofinal);
	public Produccion recuperarUltimaProduccion(AccesoBd conexionBD);
	public double recuperarCosteProduccionTotal(AccesoBd conexion);
}
