package es.iestetuan.dam2.dao;

import java.util.List;

import es.iestetuan.dam2.vo.Departamento;

public interface IDepartamentoDao {
 public Departamento consultarDepartamento(int idDepartamento);
 public List<Departamento> consultarDepartamentos();
 public List<Departamento> consultarDepartamentosNombre(String nombre);
 public void crearDepartamento(Departamento departamento);
 public void modificarDepartamento(Departamento departamento);
 public void borrarDepartamento(int idDepartamento);
}
