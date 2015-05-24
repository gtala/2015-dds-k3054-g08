package clases;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Receta {

	private Usuario      creador;
	private Date         fecha;
	private String       horario;
	private List<String> ingredientes;
	private boolean		 compartida;

	public Receta crearReceta (Usuario unCreador, String unHorario, Date unaFecha, List <String> listaIng){
		final Receta nuevaReceta;
		nuevaReceta = new Receta();
		nuevaReceta.setCreador(unCreador);
		nuevaReceta.setHorario(unHorario);
		nuevaReceta.setFecha(unaFecha);
		nuevaReceta.setIngredientes(listaIng);
		nuevaReceta.setCompartida(false);
		return nuevaReceta;
	}
	
	
	public LinkedList <Receta> planificarReceta (LinkedList <Receta> recetasUsuario, String precondicionUsuario, Date fechaPlanificacion, String horarioPlanificacion){
		
		boolean horarioValido;
		boolean recetaValida;
		boolean fechaValida;
		
		horarioValido = this.validarHorario(horarioPlanificacion);
		recetaValida  = this.validarIngredientes(precondicionUsuario);
		fechaValida   = Validaciones.validateDate(fechaPlanificacion);
		
		if(!horarioValido){
			//new UnaExceptionMuyClaraYPuntual("Horario de receta no valido\n");
			return recetasUsuario;
		}
		else if (!recetaValida){
			//new UnaExceptionMuyClaraYPuntual("Ingrediente no valido para usuario\n");
			return recetasUsuario;		
		}
		else if (!fechaValida) {
			//new UnaExceptionMuyClaraYPuntual("Fecha no valida para usuario\n");
			return recetasUsuario;
		}
		else {
			
			return this.agregarRecetaAListado(recetasUsuario);
		}
	}
	
	public LinkedList <Receta> agregarRecetaAListado (LinkedList <Receta> recetasUsuario) {

		LinkedList <Receta> unListado = new LinkedList<Receta>();
	
		if (recetasUsuario == null){
			recetasUsuario = unListado;
			recetasUsuario.add(this);
			return recetasUsuario;
		}
			
		int size = recetasUsuario.size();

		for (int i = 0; i < size; i++) {
			
			final Receta  receta = recetasUsuario.get(i);
			final boolean mismaReceta;
			
			mismaReceta = receta.equals(this);
			
			if (mismaReceta){
				recetasUsuario.remove(i);
			}

		}
		
		recetasUsuario.add(this);
		return recetasUsuario;
	}
	
	public boolean validarHorario (String unHorario){
		if(this.horario == unHorario){
			return true;
		}
		return false;
	}
	
	public boolean validarIngredientes(String unaCondicion){
		
		int size = this.ingredientes.size();

		for (int i = 0; i < size; i++){
			
			if (Validaciones.validateStrings(unaCondicion, "VEGETARIANO") && Validaciones.validateStrings(this.ingredientes.get(i), "CARNE"))
				return false;
			else if (Validaciones.validateStrings(unaCondicion, "DIABETICO") && Validaciones.validateStrings(this.ingredientes.get(i), "AZUCAR"))
				return false;
		}
			
		return true;
	}
	
	public void setCompartida(boolean unBool) {
		this.compartida = unBool;
	}
	
	public boolean getCompartida() {
		return this.compartida;
	}
	
	public void setCreador(Usuario unCreador){
		this.creador = unCreador;
	}
	
	public void setHorario(String unHorario){
		this.horario = unHorario;
	}
	
	public void setIngredientes(List <String> listadoIngredientes){
		this.ingredientes = listadoIngredientes;
	}
	
	public void setFecha (Date unaFecha){
		this.fecha = unaFecha;
	}
	
	public Usuario getCreador(){
		return creador;
	}
	
	public Date getFecha(){
		return fecha;
	}
	
	public String getHorario(){
		return horario;
	}
	
	public List <String> getIngrediente (){
		return this.ingredientes;
	}
	
	public void imprimirDetalles (){
		System.out.println();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Receta other = (Receta) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (!horario.equals(other.horario)){
			return false;
		}
		return true;
	}

}
