package clases;

public class Calificacion {
	private Usuario calificador;
	private Grupo grupo;
	private int puntaje;
	
	public Calificacion(Usuario unUsuario, Grupo unGrupo, int unPuntaje) {
		calificador = unUsuario;
		grupo = unGrupo;
		puntaje = unPuntaje;
	}
	
	public void modificarCalificacion(int nuevoPuntaje) {
		puntaje = nuevoPuntaje;
	}
	
	public Usuario getCalificador() {
		return calificador;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public int getPuntaje() {
		return puntaje;
	}

}