package modelo;

import java.util.Date;


public class Venta {

	private int idVenta;
	private int idArticulo;
	private int cantidad;
	private double precio;
	private String cliente;
	private Date FechaVenta;
	
	public Venta(int idVenta,int idArticulo,int cantidad,double precio,String cliente) {
		this.idVenta = idVenta;
		this.idArticulo = idArticulo;
		this.cantidad = cantidad ;
		this.precio = precio;
		this.cliente = cliente;
		this.FechaVenta = new Date();
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public String getCliente() {
		return cliente;
	}

	public Date getFechaVenta() {
		return FechaVenta;
	}

}
