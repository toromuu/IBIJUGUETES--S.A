package persistencia;


import java.util.HashMap;

import modelo.MateriaPrima;
import modelo.Produccion;
import modelo.ProductoFinal;



public class AdaptadorProduccion implements IAdaptadorProduccionDAO {


	private static AdaptadorProduccion unicaInstancia = null;

	public static AdaptadorProduccion getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorProduccion();
		} else
			return unicaInstancia;
	}

	private AdaptadorProduccion() { 
		
	}

	@Override
	public boolean registrarProduccion(AccesoBd conexion,Produccion produccion) {
		String registroProduccion= "INSERT INTO Produccion (IdArticulo,Cantidad,FechaProduccion) VALUES"+
						   "("+produccion.getIdArticulo()+","+produccion.getCantidad()+",SYSDATE)"	;
		conexion.insertar(registroProduccion);
		return true;
	}


	@Override
	public double getCosteProduccion(AccesoBd conexionBD,ProductoFinal productofinal) {
		HashMap<MateriaPrima, Integer> materias= conexionBD.consultaObtenerMateriasArticulo(productofinal.getArticulo());
		double precio=0.0;
		for(MateriaPrima mp : materias.keySet()){
			precio+=(mp.getPrecio()*materias.get(mp));
		}
		return precio;
	}

	@Override
	public Produccion recuperarUltimaProduccion(AccesoBd conexionBD) {
		return conexionBD.consultaUltimaProduccion();
	}
	
	public double recuperarCosteProduccionTotal(AccesoBd conexion){
		return conexion.consultarCosteProduccionTotal();
	}


}
