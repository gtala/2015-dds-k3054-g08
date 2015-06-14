package clases;

import java.util.Comparator;

public class RecetaComparator implements Comparator<Receta> {

	private Grupo grupo;
	
	public RecetaComparator(Grupo unGrupo) {
		grupo = unGrupo;
	}
	
	@Override
	public int compare(Receta r1, Receta r2) {
		return (r1.getCalificacionPromedio(grupo) - r2.getCalificacionPromedio(grupo));
	}
	
}