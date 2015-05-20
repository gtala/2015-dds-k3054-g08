package clases;

import java.util.List;




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