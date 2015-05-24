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
		
		for (Usuario u : usuarios) {
			for (Receta r : u.getRecetasCompartidas()) {
				unListado.add(r);
			}
		}
		
		return unListado;
	}
	
	public void agregarUsuario(Usuario unUsuario) {
		for (Usuario u : usuarios) {
			if (u == unUsuario) {
				throw new UnaExceptionMuyClaraYPuntual("El usuario ya se encuentra en el grupo.\n");
				return;
			}
		}
		
		usuarios.add(unUsuario);
		unUsuario.agregarGrupo(this);
	}
	
	public void quitarUsuario(Usuario unUsuario) {
		int index;
		boolean llave = false;
		
		for (index = 0; index < usuarios.size; index += 1) {
			if (usuarios.get(index) == unUsuario) {
				llave = true;
				break;
			}
		}
		
		if (!llave) {
			throw new UnaExceptionMuyClaraYPuntual("Ese ususario no se encuentra en este grupo.\n");
		} else {
			usuarios.remove(index);
			usuario.get(index).removerGrupo(this);
		}
	}
}
