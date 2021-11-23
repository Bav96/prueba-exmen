package es.iestetuan.dam2;

import java.util.List;

import es.iestetuan.dam2.dao.IDepartamentoDao;
import es.iestetuan.dam2.dao.jdbc.DepartamentoDaoJdbc;
import es.iestetuan.dam2.dao.xml.DepartamentoDaoXml;
import es.iestetuan.dam2.dao.xml.DepartamentoDaoXml2;
import es.iestetuan.dam2.vo.Departamento;

public class AplicacionDpto {

	public static void main(String[] args) {
		AplicacionDpto app = new AplicacionDpto();
		// app.tratamientoDaoJDBC();
		app.tratamientoDaoXML();
	}
	
	public void tratamientoDaoJDBC() {
		// Implementación JDBC
    	IDepartamentoDao operacionesDepartamento = new DepartamentoDaoJdbc();
    	
    	// Consulta 
    	Departamento departamento = operacionesDepartamento.consultarDepartamento(50);
    	if(departamento!=null)
    		System.out.println(departamento);

    	// Consulta de departamentos
    	List<Departamento> listaDptos= operacionesDepartamento.consultarDepartamentos();
    	for (Departamento depart : listaDptos) {
        	System.out.println(depart);
		}
    	
    	// Creación de un departamento
    	Departamento dept = new Departamento();
    	dept.setNumeroDepartamento(60);
    	dept.setNombreDepartamento("TIC y TACA");
    	dept.setLocalidad("Santander");
    	operacionesDepartamento.crearDepartamento(dept);
    	
    	// Borrado de un departamento
    	operacionesDepartamento.borrarDepartamento(60);
    	
    	// Modificación de datos de Departamento
    	// Para este ejemplo se cambia la información del departamento dado de alta
    	Departamento dept1 = new Departamento();
    	dept1.setNumeroDepartamento(20);
    	dept1.setNombreDepartamento("TRAZBILIDAD");
    	dept1.setLocalidad("Madrid");
    	operacionesDepartamento.modificarDepartamento(dept1);

    	// Consulta de datos con llamada a procedimienot almacenado
    	listaDptos= operacionesDepartamento.consultarDepartamentosNombre("V");
    	for (Departamento depart : listaDptos) {
        	System.out.println(depart);
		}
	}
	
	public void tratamientoDaoXML() {
		// Implementación XML
    	//IDepartamentoDao operacionesDepartamento = new DepartamentoDaoXml();
    	IDepartamentoDao operacionesDepartamento = new DepartamentoDaoXml2();
    	
    	// Consulta 
    	Departamento departamento = operacionesDepartamento.consultarDepartamento(40);
    	if(departamento!=null)
    		System.out.println(departamento);

    	// Consulta de departamentos
    	List<Departamento> listaDptos= operacionesDepartamento.consultarDepartamentos();
    	for (Departamento depart : listaDptos) {
        	System.out.println(depart);
		}

    	// Creación de un departamento
    	Departamento dept = new Departamento();
    	dept.setNumeroDepartamento(60);
    	dept.setNombreDepartamento("TIC y TACA");
    	dept.setLocalidad("Santander");
    	operacionesDepartamento.crearDepartamento(dept);
    	
    	// Borrado de un departamento
    	operacionesDepartamento.borrarDepartamento(60);
    	
    	// Modificación de datos de Departamento
    	// Para este ejemplo se cambia la información del departamento dado de alta
    	Departamento dept1 = new Departamento();
    	dept1.setNumeroDepartamento(20);
    	dept1.setNombreDepartamento("TRAZBILIDAD");
    	dept1.setLocalidad("Madrid");
    	operacionesDepartamento.modificarDepartamento(dept1);

    	// Consulta de datos con llamada a procedimienot almacenado
    	listaDptos= operacionesDepartamento.consultarDepartamentosNombre("V");
    	for (Departamento depart : listaDptos) {
        	System.out.println(depart);
		}
	}	
}
