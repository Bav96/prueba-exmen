delimiter //
CREATE PROCEDURE consultarDepartamentosNombre (IN nombre_departamento VARCHAR(30))
    BEGIN
        SELECT dept_no as numero_departamento, dnombre as nombre_departamento, loc AS localidad
		FROM departamentos
		WHERE dnombre like CONCAT(nombre_departamento, '%');
    END
//