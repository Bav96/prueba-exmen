package es.iestetuan.dam2.vo;

public class Departamento {
	private int numeroDepartamento;
	private String nombreDepartamento;
	private String localidad;
	public int getNumeroDepartamento() {
		return numeroDepartamento;
	}
	public void setNumeroDepartamento(int numeroDepartamento) {
		this.numeroDepartamento = numeroDepartamento;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Número de Departamento: " + numeroDepartamento + " Nombre de Departamento: " + nombreDepartamento + " Localidad: " + localidad;
	}
}
