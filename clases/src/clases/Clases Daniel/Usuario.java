package clases;

import java.util.List;
import java.util.Date;

public class Usuario {
	private List<Grupo> grupos;
	private List<Receta> recetas;
	private String email="";
	private String nombre="";
	private int edad=0;
	private String dieta="";
	private double altura=0;
	private String complexion="";
	private char sexo = 'M';
	private String precondicion="";
	private String rutina="";
	
	public Usuario()
	{
		
	}
	
	public Usuario crearUsuario(String email,String nombre,int edad,String dieta,double altura,String complexion, char sexo,String precond,String rutina)
	{
		Usuario usr = new Usuario();
		boolean validmail = Validaciones.validateEmail(email);
		if(validmail){
		usr.setEmail(email);
		}
		else
		{
			new UnaExceptionMuyClaraYPuntual("email no valido\n");
			return null;
		}
		usr.setNombre(nombre);
		if(edad<18){
			new UnaExceptionMuyClaraYPuntual("la edad no puede ser menor a 18 años\n");
			return null;
		}else{
		usr.setEdad(edad);
		}
		usr.setDieta(dieta);
		usr.setAltura(altura);
		usr.setComplexion(complexion);
		usr.setSexo(sexo);
		usr.setPrecondicion(precond);
		usr.setRutina(rutina);
				
		return usr;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDieta() {
		return dieta;
	}

	public void setDieta(String dieta) {
		this.dieta = dieta;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getPrecondicion() {
		return precondicion;
	}

	public void setPrecondicion(String precondicion) {
		this.precondicion = precondicion;
	}

	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Receta consultarPlanificacion(String unHorario, Date unaFecha, Receta unaReceta){
		
		boolean horarioValido;
		boolean recetaValida;
		boolean fechaValida;
		
		horarioValido = unaReceta.validarHorario(unHorario);
		recetaValida  = unaReceta.validarIngredientes(this.precondicion);
		fechaValida   = Validaciones.validateDate(unaFecha);
		
		if(!horarioValido){
			new UnaExceptionMuyClaraYPuntual("Horario de receta no valido\n");
			return null;
		}
		else if (!recetaValida){
			new UnaExceptionMuyClaraYPuntual("Ingrediente no valido para usuario\n");
			return null;		
		}
		else if (!fechaValida) {
			new UnaExceptionMuyClaraYPuntual("Fecha no valida para usuario\n");
			return null;
		}
		else {
			this.planificarReceta(unHorario, unaFecha, unaReceta);
			return unaReceta;
		}
	}
	
	public Receta planificarReceta (String unHorario, Date unaFecha, Receta unaReceta) {

		int size = this.recetas.size();
		
		for (int i = 0; i < size; i++) {
			
			final Receta  recetaUsuario = this.recetas.get(i);
			final boolean mismaFecha;
			final boolean mismaReceta;
			
			mismaFecha    = recetaUsuario.validarFecha(unaFecha);
			mismaReceta   = recetaUsuario.equals(unaReceta);
			
			if (mismaFecha && !mismaReceta && unaReceta != null ){
				this.recetas.remove(i);
				this.recetas.add(unaReceta);
				this.recetas.get(i).setFecha(unaFecha);
			}
			else if (mismaFecha && !mismaReceta && unaReceta == null ){
				this.recetas.remove(i);
			}
			
		}
		return unaReceta;
	}

	public void listarRecetas (){
		
		List <Grupo> listadoGrupos = this.getGrupos();
		RepositorioRecetas listadoDefault = new RepositorioRecetas();
		
		System.out.println("Recetas propias del usuario "+ this.nombre);
		this.imprimirListadoRecetas(this.recetas);
		System.out.println("Recetas de sus grupo");
		this.obtenerListadoUsuarios(listadoGrupos);
		System.out.println("Recetas por default");
		this.imprimirListadoRecetas(listadoDefault.getRecetas());
	}
	
	public void obtenerListadoUsuarios (List <Grupo> listadoGrupos){

		int size = listadoGrupos.size();
		for (int i = 0; i < size; i++) {
			this.obtenerRecetasUsuario(listadoGrupos.get(i).getUsuarios());
		}
	}

	public void obtenerRecetasUsuario (List <Usuario> listadoUsuarios){

		int size = listadoUsuarios.size();
		for (int i = 0; i < size; i++) {
			this.imprimirListadoRecetas(listadoUsuarios.get(i).recetas);
		}
	}
	
	public void imprimirListadoRecetas (List <Receta> listadoRecetas){

		int size = listadoRecetas.size();
		for (int i = 0; i < size; i++) {
			listadoRecetas.get(i).imprimirDetalles();
		}	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}