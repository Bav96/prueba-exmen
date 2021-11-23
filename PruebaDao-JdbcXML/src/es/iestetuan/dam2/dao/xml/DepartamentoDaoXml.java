package es.iestetuan.dam2.dao.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import es.iestetuan.dam2.dao.IDepartamentoDao;
import es.iestetuan.dam2.utilidades.GestorConfiguracion;
import es.iestetuan.dam2.vo.Departamento;

/*
En esta clase el tratamiento se realiza sobre Objetos Departamento que luego se convierten a fichero XML
El alta de un departamento supone añadir un objeto de partamento a la lista de todos los departamentos; después se convierte a XML
De forma similar se puede aplicar a la baja y modificación de un departamento. 
*/

public class DepartamentoDaoXml implements IDepartamentoDao{

	@Override
	public Departamento consultarDepartamento(int idDepartamento) {
		Departamento departamento=null;
		String rutaOrigen = GestorConfiguracion.getInfoConfiguracion("origenDepartamentos");
		File fichero = new File (rutaOrigen);
		Document documento = null;
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  documento = dBuilder.parse(fichero);
			  documento.getDocumentElement().normalize();
			} catch(Exception e) {
			  e.printStackTrace();
		}
		// Se obtiene la lista de nodos relacionado con 'departamento'
		NodeList nListIni = documento.getElementsByTagName("departamento");
		System.out.println("Nº de departamentos: " + nListIni.getLength());
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
		    	int numDpto=Integer.parseInt(numeroDpto);
		    	// Se guardan el departamento que contienen el mismo numero de Departamento
		    	if (numDpto==idDepartamento) {
			    	String nombreDpto= elemento.getElementsByTagName("nombre").item(0).getTextContent();
			    	String localidadDpto= elemento.getElementsByTagName("localidad").item(0).getTextContent();
			    	departamento=new Departamento();
			    	departamento.setNumeroDepartamento(numDpto);
			    	departamento.setNombreDepartamento(nombreDpto);
			    	departamento.setLocalidad(localidadDpto);
		    	}
			}
		}
		
		return departamento;
	}

	@Override
	public List<Departamento> consultarDepartamentos() {
		List<Departamento> listaDepartamentos= null;
		Departamento departamento=null;
		String rutaOrigen = GestorConfiguracion.getInfoConfiguracion("origenDepartamentos");
		File fichero = new File (rutaOrigen);
		Document documento = null;
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  documento = dBuilder.parse(fichero);
			  documento.getDocumentElement().normalize();
			} catch(Exception e) {
			  e.printStackTrace();
		}
		// Se obtiene la lista de nodos relacionado con 'departamento'
		NodeList nListIni = documento.getElementsByTagName("departamento");
		System.out.println("Nº de departamentos: " + nListIni.getLength());
		if (nListIni.getLength() > 0){
			listaDepartamentos= new ArrayList<Departamento>();
		}
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String nombreDpto= elemento.getElementsByTagName("nombre").item(0).getTextContent();
		    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
		    	String localidadDpto= elemento.getElementsByTagName("localidad").item(0).getTextContent();
		    	departamento=new Departamento();
		    	departamento.setNumeroDepartamento(Integer.parseInt(numeroDpto));
		    	departamento.setNombreDepartamento(nombreDpto);
		    	departamento.setLocalidad(localidadDpto);
		    	listaDepartamentos.add(departamento);
			}
		}
		
		return listaDepartamentos;
	}

	@Override
	// Departamentos que contienen el contenido de nombreDepartamento
	public List<Departamento> consultarDepartamentosNombre(String nombreDepartamento) {
		List<Departamento> listaDepartamentos= null;
		Departamento departamento=null;
		String rutaOrigen = GestorConfiguracion.getInfoConfiguracion("origenDepartamentos");
		File fichero = new File (rutaOrigen);
		Document documento = null;
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  documento = dBuilder.parse(fichero);
			  documento.getDocumentElement().normalize();
			} catch(Exception e) {
			  e.printStackTrace();
		}
		// Se obtiene la lista de nodos relacionado con 'departamento'
		NodeList nListIni = documento.getElementsByTagName("departamento");
		System.out.println("Nº de departamentos: " + nListIni.getLength());
		if (nListIni.getLength() > 0){
			listaDepartamentos= new ArrayList<Departamento>();
		}
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String nombreDpto= elemento.getElementsByTagName("nombre").item(0).getTextContent();
		    	// Se guardan aquellos departamento que contienen el contenido de nombreDepartamento
		    	if (nombreDpto.contains(nombreDepartamento)) {
			    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
			    	String localidadDpto= elemento.getElementsByTagName("localidad").item(0).getTextContent();
			    	departamento=new Departamento();
			    	departamento.setNumeroDepartamento(Integer.parseInt(numeroDpto));
			    	departamento.setNombreDepartamento(nombreDpto);
			    	departamento.setLocalidad(localidadDpto);
			    	listaDepartamentos.add(departamento);
		    	}
			}
		}
		
		return listaDepartamentos;
	}

	@Override
	public void crearDepartamento(Departamento dpto) {
		List<Departamento> listaDepartamentos= consultarDepartamentos();
		boolean existe= false;
		for (Departamento departamento: listaDepartamentos) {
			if(departamento.getNumeroDepartamento()==dpto.getNumeroDepartamento()) {
				existe= true;
				break;
			}			
		}
		// Se crea el fichero si no existía el departamento, si no, se deja como estaba.
		if(!existe) {
			listaDepartamentos.add(dpto);
			guardarDepartamentos(listaDepartamentos);
		}
	}

	@Override
	public void modificarDepartamento(Departamento dpto) {
		List<Departamento> listaDepartamentos= consultarDepartamentos();
		boolean existe= false;
		for (Departamento departamento: listaDepartamentos) {
			if(departamento.getNumeroDepartamento()==dpto.getNumeroDepartamento()) {
				departamento.setNombreDepartamento(dpto.getNombreDepartamento());
				departamento.setLocalidad(dpto.getLocalidad());
				existe= true;
				break;
			}			
		}
		// Solo se guarda la información si existía un departamento. Si no, se deja como estaba.
		if(existe)
			guardarDepartamentos(listaDepartamentos);
		
	}

	@Override
	public void borrarDepartamento(int idDepartamento) {
		List<Departamento> listaDepartamentos= consultarDepartamentos();
		boolean existe= false;
		for (Departamento departamento: listaDepartamentos) {
			if(departamento.getNumeroDepartamento()==idDepartamento) {
				listaDepartamentos.remove(departamento);
				existe= true;
				break;
			}			
		}
		// Solo se borra la información si existía un departamento. Si no, se deja como estaba.
		if(existe)
			guardarDepartamentos(listaDepartamentos);
	}

	private void guardarDepartamentos(List<Departamento> listaDepartamentos) {
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//DOMImplementation domImpl = dBuilder.getDOMImplementation();
			//documento = domImpl.createDocument(null, "xml", null);
			documento = dBuilder.newDocument();
				
			// Se crear el nodo raíz
			Element raiz = documento.createElement("departamentos");
			documento.appendChild(raiz);

			// Información para nodos internos
			Element nodoDepartamento = null , nodoDatos = null ;
			Text texto = null;
			for (Departamento departamento: listaDepartamentos) {
				// Se asigna el nodo/elemento usuario a elemento raíz 
				nodoDepartamento = documento.createElement("departamento");
				raiz.appendChild(nodoDepartamento);
				
				// Se carga información número de departamento
				nodoDatos = documento.createElement("numero");
				nodoDepartamento.appendChild(nodoDatos);
				String numDpto= String.valueOf(departamento.getNumeroDepartamento());
				texto = documento.createTextNode(numDpto);
				nodoDatos.appendChild(texto);
	
				// Se carga información nombre
				nodoDatos = documento.createElement("nombre");
				nodoDepartamento.appendChild(nodoDatos);
				texto = documento.createTextNode(departamento.getNombreDepartamento());
				nodoDatos.appendChild(texto);

				// Se carga información apellido1
				nodoDatos = documento.createElement("localidad");
				nodoDepartamento.appendChild(nodoDatos);
				texto = documento.createTextNode(departamento.getLocalidad());
				nodoDatos.appendChild(texto);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			String rutaOrigen = GestorConfiguracion.getInfoConfiguracion("origenDepartamentos");
			Result output = new StreamResult(new File(rutaOrigen));
			Source input = new DOMSource(documento);
			transformer.transform(input, output);
		} catch(Exception e) {
			  e.printStackTrace();
		}		
	}
}
