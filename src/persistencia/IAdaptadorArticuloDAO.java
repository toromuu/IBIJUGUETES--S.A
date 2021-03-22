package persistencia;


import java.util.HashMap;
import java.util.List;

import modelo.Articulo;
import modelo.MateriaPrima;
import modelo.ProductoFinal;

public interface IAdaptadorArticuloDAO {
	public boolean registrarArticulo(AccesoBd conexionBD,Articulo articulo);
	public List<ProductoFinal> getListProductosFinales(AccesoBd conexionBD);
	public List<MateriaPrima> getListMateriasPrimas(AccesoBd conexionBD);
	public HashMap<MateriaPrima, Integer> getDesglose(AccesoBd conexionBD,ProductoFinal productoFinal);
	public List<Articulo> getArticulos(AccesoBd conexionBD);
	public Articulo getArticulo(AccesoBd conexionBD,int idArticulo);
}
