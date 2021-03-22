package modelo;

import java.util.Date;


import controlador.ControladorIbiJuguetes;

public class Produccion {
	
	private int idProduccion;
	private int idArticulo;
	private int idConjunto;
	private int cantidad;
	private double costePorUnidad;
	private Date fechaProduccion;
	
	public Produccion(int idProduccion, int idArticulo, int idConjunto, int cantidad,double coste) {
		this.idProduccion = idProduccion;
		this.idArticulo = idArticulo;
		this.idConjunto = idConjunto;
		this.cantidad =cantidad;
		this.costePorUnidad = coste;
		this.fechaProduccion = new Date();
	}

	public int getIdProduccion() {
		return idProduccion;
	}

	public void setIdProduccion(int idProduccion) {
		this.idProduccion = idProduccion;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdConjunto() {
		return idConjunto;
	}

	public void setIdConjunto(int idConjunto) {
		this.idConjunto = idConjunto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getCoste() {
		return costePorUnidad;
	}

	public void setCoste(double coste) {
		this.costePorUnidad = coste;
	}

	public Date getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(Date fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}
	
	public double getCosteTotal() {
		return cantidad*costePorUnidad;
	}

	public ProductoFinal getProductoFinal() {
		return (ProductoFinal) ControladorIbiJuguetes.getUnicaInstancia().obtenerArticulo(idArticulo);
	}
	
}
