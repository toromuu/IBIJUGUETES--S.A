package controlador;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;



import modelo.Articulo;
import modelo.Compras;

import modelo.MateriaPrima;

import modelo.Produccion;
import modelo.ProductoFinal;
import modelo.RegistroOperacion;
import modelo.Venta;
import persistencia.AccesoBd;
import persistencia.DAOException;
import persistencia.FactoriaDAO;

import persistencia.IAdaptadorArticuloDAO;
import persistencia.IAdaptadorComprasDAO;
import persistencia.IAdaptadorProduccionDAO;
import persistencia.IAdaptadorVentaDAO;


public class ControladorIbiJuguetes {

	private static ControladorIbiJuguetes unicaInstancia;
	private IAdaptadorArticuloDAO adaptadorArticulo;
	private IAdaptadorComprasDAO adaptadorCompra;
	private IAdaptadorProduccionDAO adaptadorProduccion;
	private IAdaptadorVentaDAO adaptadorVenta;
	private AccesoBd conexionBD;


	private ControladorIbiJuguetes() {
		inicializarAdaptadores();
	}

	public static ControladorIbiJuguetes getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorIbiJuguetes();
		return unicaInstancia;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorArticulo = factoria.getArticuloDAO();
		adaptadorCompra = factoria.getCompraDAO();
		adaptadorProduccion = factoria.getProduccionDAO();
		adaptadorVenta = factoria.getVentaDAO();
		conexionBD = AccesoBd.getUnicaInstancia();
		
	}
	

	/*
	 * ALTA DE PRODUCTOS
	 */
	//Registro de materia prima
	public boolean registrarMateriaPrima(String articulo) {
		
		MateriaPrima prima;
		int idArticulo = conexionBD.consultaMaxId("SELECT max(idarticulo) FROM articulos")+1;
		try {
			prima = new MateriaPrima(idArticulo, articulo,  0,0);	
		} catch (NumberFormatException e) {
			System.out.println("ERROR Registrar Materia Prima");
			return false;
		}
		if (!adaptadorArticulo.registrarArticulo(conexionBD,prima)) return false;
		return true;
		
	}
	
	// Para el registro de Producto final se necesita saber todas las materias primas para seleccionar las que compondran al producto final
	public List <MateriaPrima> getMateriasPrimasDisponibles(){
		return  adaptadorArticulo.getListMateriasPrimas(conexionBD);
	}
	
	//Registro de productoFinal
	public boolean registrarProductoFinal(String articulo, String margen, HashMap<MateriaPrima,Integer> listaMateriasPrimas) {
		
		ProductoFinal productofinal;
		int idArticulo = conexionBD.consultaMaxId("SELECT max(idarticulo) FROM articulos");
		int idDesglose = conexionBD.consultaMaxId("SELECT max(iddesglose) FROM articulosdesglose")+1;
		productofinal = new ProductoFinal(idArticulo,idDesglose, articulo, Integer.parseInt(margen), listaMateriasPrimas);	
		
		if (!adaptadorArticulo.registrarArticulo(conexionBD,productofinal)) return false;
		return true;

	}
	
	
	/*
	 * GESTION INVENTARIO
	 */
	
	// Obtener la lista con todos los articulos tanto productos finales como materias primas
	public List<Articulo> obtenerTodosArticulos() {
		return adaptadorArticulo.getArticulos(conexionBD);
	}
	
	public Articulo obtenerArticulo(int idArticulo){
		return adaptadorArticulo.getArticulo(conexionBD,idArticulo);
	}

	public double getSaldoActualEmpresa() {
		 return conexionBD.consultaSaldo();
	}
	
	public double getPrecioActualArticulo(MateriaPrima articuloReponer, double cantidadaReponer) {
		return articuloReponer.getPrecio()*cantidadaReponer;
	}
	
	
	// Tras una reposición hay que refrescar la vista con getMateriasPrimasDisponibles() para saber el nuevo precio venta
	public boolean registrarCompra( MateriaPrima articuloReponer ,String proveedor , int cantidad , String precio) {
		int idCompra=conexionBD.consultaMaxId("SELECT max(idcompra) FROM compras")+1;
		if(getSaldoActualEmpresa()<=0.0) return false; 	//puede ser que el saldo de la empresa sea cero pero queramos reponer un producto recien dado de alta que su precio es cero
														//y el if de abajo dejaría dar de alta la compra
		if (getSaldoActualEmpresa() >=  getPrecioActualArticulo(articuloReponer, cantidad) ) {
			Compras compra= new Compras(idCompra, articuloReponer.getIdArticulo() ,proveedor ,cantidad, Double.parseDouble(precio));	
			
			if (!adaptadorCompra.registrarCompra(conexionBD,compra)) return false;
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public double precioCompras(){
		return adaptadorCompra.recuperarPrecioCompras(conexionBD);
	}
	
	public double costeProduccionMatPrimas(){
		return adaptadorProduccion.recuperarCosteProduccionTotal(conexionBD);
	}
	
	public double precioVentas(){
		return adaptadorVenta.recuperarPrecioVentas(conexionBD);
	}
	

	/*
	 * PRODUCCION
	 */
	public HashMap<MateriaPrima, Integer> getMateriasPrimasNecesarias(ProductoFinal productofinal){
		return  adaptadorArticulo.getDesglose(conexionBD,productofinal);
	}
	
	//Como propiedad tiene un mapa con los materiales necesarios y su cantidad
	public List <ProductoFinal> getProductosFinalesDisponibles(){
		return  adaptadorArticulo.getListProductosFinales(conexionBD);
	}
	
	
	public double obtenerCosteProduccionPorUnidad(ProductoFinal productofinal) {
		double coste= adaptadorProduccion.getCosteProduccion(conexionBD,productofinal);
		return coste;
	}
	
	
	//Si el registro se completa se actualiza mediante triggers el saldo de la empresa
	public boolean registrarProduccion(ProductoFinal productofinal, String cantidad) {
		
		int cantidadProducto = Integer.parseInt(cantidad);
		if(conexionBD.consultaMatPriSuficientes(productofinal,cantidadProducto)){
		
			double costeProduccionPorUnidad= obtenerCosteProduccionPorUnidad(productofinal);
			Produccion produccion;
			int idProduccion = conexionBD.consultaMaxId("SELECT max(idproduccion) FROM produccion")+1;
			int idArticulo=conexionBD.consultaIdArticulo(productofinal);
			int idConjunto=conexionBD.consultaMaxId("SELECT max(idconjunto) FROM produccionconjuntos")+1;
			
			produccion= new Produccion(idProduccion,idArticulo, idConjunto, cantidadProducto, costeProduccionPorUnidad );	
				
			if (!adaptadorProduccion.registrarProduccion(conexionBD,produccion)) return false;	
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public Produccion getUltimaProduccion() {
		return adaptadorProduccion.recuperarUltimaProduccion(conexionBD);
	}
	
	
	/*
	 * VENTAS
	 */
	
	public double obtenerPrecioVenta(int cantidad, ProductoFinal productofinal) {

		ProductoFinal pf = (ProductoFinal) conexionBD.consultaObtenerArticulo(productofinal.getIdArticulo());
		double precioVenta=(100.0*pf.getPrecio())/(100.0 -(double)pf.getMargen());
		
		return ( precioVenta * cantidad );
	}
	
	public boolean registrarVenta(String cantidad,String cliente, ProductoFinal productofinal) {
		int cantidadVenta = Integer.parseInt(cantidad);
		double precioVenta = obtenerPrecioVenta(cantidadVenta,productofinal);
		if ( productofinal.getStock() >= cantidadVenta ) {
			Venta venta;
			int idVenta =conexionBD.consultaMaxId("SELECT max(idventa) FROM ventas")+1;

			venta= new Venta(idVenta,productofinal.getIdArticulo(),cantidadVenta,precioVenta,cliente);	
						
			if (!adaptadorVenta.registrarVenta(conexionBD,venta)) return false;
			return true;
		}
		return false;
	}

	
	public boolean generarPDF(String ruta) throws FileNotFoundException, DocumentException {
		FileOutputStream archivo = new FileOutputStream(ruta+"\\Lista_Articulos_Inventario.pdf");
	    Document documento = new Document();
	    PdfWriter.getInstance(documento, archivo);
	    
	    documento.open();
	   
	    List<Articulo> inventarioArticulos = getUnicaInstancia().obtenerTodosArticulos();
	    
	    inventarioArticulos.stream().
	    		forEach(c -> {
					try {
						c.escribirEnPdf(documento,"\t");
					} catch (DocumentException e) {
						System.out.println("Error al generar PDF");
					}
				});
	   
	    documento.close();
	    return true;
	}

	public double obtenerBeneficioEsperado() {
		return conexionBD.consultarBeneficioEsperado();
	}

	public List<RegistroOperacion> obtenerRegistroOperaciones(Date dateInferior,Date dateSuperior,String operacion) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		List<RegistroOperacion> listaRegistros= new LinkedList<RegistroOperacion>();
		for (RegistroOperacion RegistroOperacion : conexionBD.consultarRegistroOperaciones()  ) {
			
				if (dateInferior!= null &&  dateSuperior!= null ) {
					
					if( !dateInferior.after(RegistroOperacion.getFecha()) && !dateSuperior.before(RegistroOperacion.getFecha()) 
							||  ( sdf.format(dateInferior).equals(sdf.format(RegistroOperacion.getFecha())) 
							&&   sdf.format(dateSuperior).equals(sdf.format(RegistroOperacion.getFecha())) ) ) {
							
							if (!operacion.equals("")) {
								if (RegistroOperacion.getOperacion().equals(operacion)) {
									listaRegistros.add(RegistroOperacion);
								}
							
							}else {
								listaRegistros.add(RegistroOperacion);
							}
						}
					
				}else {
					
					if ( ! operacion.equals("")) {
						if (RegistroOperacion.getOperacion().equals(operacion)) {
							listaRegistros.add(RegistroOperacion);
						}
					
					}else {
						listaRegistros.add(RegistroOperacion);
					}
				}
		}
		
		return listaRegistros;
		
	}
	

}
