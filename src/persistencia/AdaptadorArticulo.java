package persistencia;



import java.util.HashMap;

import java.util.List;


import modelo.Articulo;
import modelo.MateriaPrima;
import modelo.ProductoFinal;



public class AdaptadorArticulo implements IAdaptadorArticuloDAO {

	private static AdaptadorArticulo unicaInstancia = null;

	public static AdaptadorArticulo getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorArticulo();
		} else
			return unicaInstancia;
	}

	private AdaptadorArticulo() {
		
	}

	// Registro de un articulo tanto materia prima como producto final
	public  boolean registrarArticulo(AccesoBd conexionBD,Articulo articulo) {
		String registroArticulo = "";
		if(articulo instanceof MateriaPrima) {
			MateriaPrima materia = (MateriaPrima) articulo;
			 registroArticulo = "INSERT INTO Articulos " + 
					"(IDTIPOARTICULO, ARTICULO, STOCK, IDDESGLOSE, PRECIO, MARGEN, FECHAALTA ) \n" + 
					"VALUES " + 
					"(1,'"+ materia.getArticulo() +"',0,null,0,0,SYSDATE)"; //opcion 1

		} else if (articulo instanceof ProductoFinal) {
			ProductoFinal producto = (ProductoFinal) articulo;
			//INSERT IDDESGLOSE
			int idDesglose =producto.getIdDesglose();
			int idLinea =1;
			for (MateriaPrima mp :producto.getListaMateriasPrimas().keySet()){
				int idArticulo =conexionBD.consultaIdArticulo(mp);
				int cantidad= producto.getListaMateriasPrimas().get(mp);
				
				String desglose= "INSERT INTO ArticulosDesglose "+
								 "(IdDesglose,IdLinea,IdArticulo,Cantidad)"+
								 "			VALUES"+
								 "("+idDesglose+","+idLinea+","+idArticulo+","+cantidad+")";
				idLinea++;
				conexionBD.insertar(desglose);
			}
			// PRODUCTO FINAL
			registroArticulo = "INSERT INTO Articulos " + 
					"        ( IDTIPOARTICULO, ARTICULO, STOCK, IDDESGLOSE, PRECIO, MARGEN, FECHAALTA ) \n" + 
					"        VALUES\n" + 
					"        (2,'"+ producto.getArticulo() +"',0,"+idDesglose+",0,"+producto.getMargen()+",SYSDATE)";
			
		}
		
		int registro=conexionBD.insertar(registroArticulo);
		return (registro != 0);
	}
		
	// Obtiene todas las materias primas	
	public List<MateriaPrima> getListMateriasPrimas(AccesoBd conexionBD) {
		return conexionBD.consultaMatPrimas();
	}
	
	// Obtiene todas las materias primas necesarias para fabricar un producto
	public HashMap<MateriaPrima, Integer> getDesglose(AccesoBd conexionBD,ProductoFinal productoFinal){
		return conexionBD.consultaObtenerMateriasArticulo(productoFinal.getArticulo());
	}
	
	
	public List<ProductoFinal> getListProductosFinales(AccesoBd conexionBD){
		return conexionBD.consultaProdFinales();
	}
	

	/*
	 * Posibilidad de refactorizar si se obtienen todas las columnas y en los otros metodos especificos se crean 
	 * listas de tipos concretos haciendo uso de instance of y castings
	 */
	public List<Articulo> getArticulos(AccesoBd conexionBD) {
		return conexionBD.consultaArticulos();
	}
	
	public Articulo getArticulo(AccesoBd conexionBD,int idArticulo){
		Articulo articulo = conexionBD.consultaObtenerArticulo(idArticulo);
		return articulo;
	}
	
}
