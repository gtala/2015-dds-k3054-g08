package clases;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RepositorioRecetas {

	List <Receta> recetas;
	
	public RepositorioRecetas() {
	}
	
	public List <Receta> getRecetas(){
		return recetas;
	}
	
	public void eliminarReceta(Receta unaReceta) {
		int indiceAEliminar = -1;
		for (int index = 0; index < recetas.size(); index += 1) {
			if (unaReceta == recetas.get(index)) {
				indiceAEliminar = index;
			}
		}
		
		if (indiceAEliminar != -1) {
			recetas.remove(indiceAEliminar);
		}
	}
	
	public void nuevaReceta(Receta unaReceta) throws UnaExceptionMuyClaraYPuntual {
		if (recetas == null) {
			List<Receta> temp = new ArrayList<Receta>();
			temp.add(unaReceta);
			recetas = temp;
		} else {
			if (validarReceta(unaReceta)) {
				recetas.add(unaReceta);
			} else {
				throw new UnaExceptionMuyClaraYPuntual("Esa receta ya existe.\n");
			}
		}
	}
	
	public List<Receta> buscarRecetasDeAutor(Usuario u) {
		List<Receta> unListado = new LinkedList<Receta>();
		
		for (Receta r : recetas) {
			if (r.getCreador() == u) {
				unListado.add(r);
			}
		}
		
		return unListado;
	}
	
	public Receta buscarReceta(Usuario u, String nombre) throws UnaExceptionMuyClaraYPuntual {
		Receta devolucion = null;
		for (Receta r : buscarRecetasDeAutor(u)) {
			if (r.getNombre() == nombre) {
				devolucion = r;
			} else {
				throw new UnaExceptionMuyClaraYPuntual("Esa receta no existe.\n");
			}
		}
		
		return devolucion;
	}
	
	public void modificarReceta(Receta r) throws UnaExceptionMuyClaraYPuntual {
		Receta recetaEnRepo = buscarReceta(r.getCreador(), r.getNombre());
		if (recetaEnRepo != null) {
			int index = recetas.indexOf(recetaEnRepo);
			recetas.set(index, r);
		}
	}

	private boolean validarReceta(Receta unaReceta) {
		if (recetas == null) { return true; }
		
		for (Receta r : recetas) {
			if ((r.getCreador().getNombre() == unaReceta.getCreador().getNombre()) && (r.getNombre() == unaReceta.getNombre())) {
				return false;
			}
		}
		
		return true;
	}
}