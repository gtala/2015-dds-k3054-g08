package clases;

import java.util.List;
import java.util.LinkedList;

public class Grupo {
	List <Usuario> usuarios;
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public LinkedList <Receta> listadoRecetas(){
		LinkedList <Receta> unListado = new LinkedList <Receta> ();
		return unListado;
	}
}
