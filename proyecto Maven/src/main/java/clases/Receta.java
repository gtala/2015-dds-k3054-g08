package clases;

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

public class Receta {

	private Usuario      creador;
	private LocalDate         fecha;
	private String       horario;
	private List<String> ingredientes;
	private boolean		 compartida;
	private List<Calificacion> calificaciones;
	private String nombre;
	private String procedimiento;
	private int dificultad;
	private int calorias;

	public Receta crearReceta (Usuario unCreador, String unHorario, LocalDate unaFecha, List <String> listaIng, String unNombre){
		final Receta nuevaReceta;
		nuevaReceta = new Receta();
		nuevaReceta.setCreador(unCreador);
		nuevaReceta.setHorario(unHorario);
		nuevaReceta.setFecha(unaFecha);
		nuevaReceta.setIngredientes(listaIng);
		nuevaReceta.setCompartida(false);
		nuevaReceta.setNombre(unNombre);
		return nuevaReceta;
	}
	
	
	public LinkedList <Receta> planificarReceta (LinkedList <Receta> recetasUsuario, String precondicionUsuario, LocalDate fechaPlanificacion, String horarioPlanificacion){
		
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
			//uso un clon de la receta en este momento, por si la receta original cambia/se elimina
			return this.clone().agregarRecetaAListado(recetasUsuario);
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
	
	public boolean validarCalificacion(Calificacion unaCal) {
		for (Calificacion c : calificaciones) {
			if (c.getCalificador() == unaCal.getCalificador()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void nuevaCalificacion(Calificacion unaCal) {
		if (calificaciones == null) {
			List<Calificacion> temp = new LinkedList<Calificacion>();
			calificaciones = temp;
		}
		if (validarCalificacion(unaCal)) {
			calificaciones.add(unaCal);
		}
	}
	
	public int getCalificacionPromedio(Grupo unGrupo) {
		int total = 0;
		int count = 0;
		
		for (Calificacion c : calificaciones) {
			if (c.getGrupo() == unGrupo) {
				count++;
				total += c.getPuntaje();
			}
		}
		
		return (total / count);
	}
	
	public void setNombre(String unNombre) {
		nombre = unNombre;
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
	
	public void setFecha (LocalDate unaFecha){
		this.fecha = unaFecha;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Usuario getCreador(){
		return creador;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	
	public String getHorario(){
		return horario;
	}
	
	public List <String> getIngrediente (){
		return this.ingredientes;
	}
	
	public String getProcedimiento() {
		return procedimiento;
	}


	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}


	public int getDificultad() {
		return dificultad;
	}


	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}


	public int getCalorias() {
		return calorias;
	}


	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}


	public void imprimirDetalles (){
		System.out.println();
	}
	
	public Receta clone() {
		//si la clono para "Crear a partir de..." simplemente cambio nombre y creador para poder agregarla al RepositorioRecetas
		Receta r = crearReceta(creador, horario, fecha, ingredientes, nombre);
		return r;
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
		if (!(this.nombre == other.getNombre())){
			return false;
		}
		return true;
	}
	

}