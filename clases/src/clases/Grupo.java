package clases;

import java.util.LinkedList;

public class Grupo {
	LinkedList <Usuario> usuarios;
	
	public LinkedList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public int cantidadUsuarios() {
		int size;
		if (usuarios == null)
			size = 0;
		else
			size = usuarios.size();
				
		return size;
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
	
	public LinkedList<Usuario> agregarUsuario(Usuario unUsuario) {
		
		LinkedList<Usuario> unListado = new LinkedList<Usuario>();

		int size;
		
		if (usuarios == null){
			usuarios = unListado;
			usuarios.add(unUsuario);
			unUsuario.agregarGrupo(this);
			return usuarios;
		}
		else
			size = usuarios.size();
		
		for (int i = 0; i < size; i++){
			if (usuarios.get(i) == unUsuario) {
				new UnaExceptionMuyClaraYPuntual("El usuario ya se encuentra en el grupo.\n");
				return usuarios;
			}
		}
		usuarios.add(unUsuario);
		unUsuario.agregarGrupo(this);
		return usuarios;
	}
	
	public LinkedList<Usuario> quitarUsuario(Usuario unUsuario){
		
		int index, size;
		boolean llave = false;
		
		if (usuarios == null)
			size = 0;
		else
			size = usuarios.size();
		
		for (index = 0; index < size; index += 1) {
			if (usuarios.get(index) == unUsuario) {
				llave = true;
				break;
			}
		}
		if (llave) {
			usuarios.get(index).removerGrupo(this);
			usuarios.remove(index);
		}
		return usuarios;
	}
}
