package modelo;

import java.util.Date;

public class RegistroOperacion {
	
	private String operacion;
	private String articulo;
	private int idArticulo;
	private String proveedorCliente;
	private int cantidad;
	private double precio;
	private Date fecha;

	
	public RegistroOperacion(String operacion, String articulo, int idArticulo,String proveedorCliente , int cantidad,double coste,Date fecha) {
		this.operacion=operacion;
		this.articulo=articulo;
		this.idArticulo = idArticulo;
		this.cantidad =cantidad;
		this.precio = coste;
		this.proveedorCliente=proveedorCliente;
		this.fecha = fecha;
	}


	public String getOperacion() {
		return operacion;
	}

	public String getArticulo() {
		return articulo;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public String getProveedorCliente() {
		return proveedorCliente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public Date getFecha() {
		return fecha;
	}
	
}
