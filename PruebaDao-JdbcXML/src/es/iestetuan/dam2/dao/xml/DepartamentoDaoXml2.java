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
 En esta clase el tratamiento se realiza "al vuelo" sobre los nodos del documento (alta, baja, modificación) 
 */
public class DepartamentoDaoXml2 implements IDepartamentoDao{

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
		boolean existeDepartamento=false;
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
				if(Integer.parseInt(numeroDpto)==dpto.getNumeroDepartamento()) {
					existeDepartamento= true;
					break;
				}			
			}
		}
		// Se crea el fichero si no existía el departamento, si no, se deja como estaba.
		if(!existeDepartamento) {
			Element raiz = documento.getDocumentElement(); // departamentos
			Element nodoDepartamento = null , nodoDatos = null ;
			Text texto = null;

			// Se asigna el nodo/elemento usuario a elemento raíz 
			nodoDepartamento = documento.createElement("departamento");
			raiz.appendChild(nodoDepartamento);
			
			// Se carga información número de departamento
			nodoDatos = documento.createElement("numero");
			nodoDepartamento.appendChild(nodoDatos);
			String numDpto= String.valueOf(dpto.getNumeroDepartamento());
			texto = documento.createTextNode(numDpto);
			nodoDatos.appendChild(texto);

			// Se carga información nombre
			nodoDatos = documento.createElement("nombre");
			nodoDepartamento.appendChild(nodoDatos);
			texto = documento.createTextNode(dpto.getNombreDepartamento());
			nodoDatos.appendChild(texto);

			// Se carga información apellido1
			nodoDatos = documento.createElement("localidad");
			nodoDepartamento.appendChild(nodoDatos);
			texto = documento.createTextNode(dpto.getLocalidad());
			nodoDatos.appendChild(texto);
			
			documento.normalize();
			guardarInformacionFicheroXML(documento);		
		}
	}

	@Override
	public void modificarDepartamento(Departamento dpto) {
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
		boolean existeDepartamento=false;
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
		    	// Se modifican los valores en caso
				if(Integer.parseInt(numeroDpto)==dpto.getNumeroDepartamento()) {
					elemento.getElementsByTagName("nombre").item(0).setTextContent(dpto.getNombreDepartamento());
					elemento.getElementsByTagName("localidad").item(0).setTextContent(dpto.getLocalidad());
					existeDepartamento= true;
					break;
				}			
			}
		}

		// Solo se guarda la información si existía un departamento. Si no, se deja como estaba( no ha habido modificación).
		if(existeDepartamento) {
			guardarInformacionFicheroXML(documento);		
		}
		
	}

	@Override
	public void borrarDepartamento(int idDepartamento) {
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
		boolean existeDepartamento=false;
		Element departamentoABorrar=null;
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	String numeroDpto= elemento.getElementsByTagName("numero").item(0).getTextContent();
		    	// Departamento a borrar
				if(Integer.parseInt(numeroDpto)==idDepartamento) {
					existeDepartamento= true;
					departamentoABorrar=elemento;
					break;
				}			
			}
		}
		// Se boorra el nodo.
		if(existeDepartamento) {
			Element departamentos = documento.getDocumentElement(); // should be <departamentos>
			departamentos.removeChild(departamentoABorrar);
	
			documento.normalize();
			guardarInformacionFicheroXML(documento);						
		}
	}

	private void guardarInformacionFicheroXML(Document documento) {
		try {
			documento.normalize();
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
