package clases;

import java.util.Date;
import java.util.List;


public class Receta {

	private Date   fecha;
	private String horario;
	private List<String> ingredientes;
	
	public Date getFecha(){
		return fecha;
	}
	
	public void setFecha (Date unaFecha){
		this.fecha = unaFecha;
	}
	
	public boolean validarFecha (Date unaFecha){
		if (this.fecha == unaFecha)
			return true;
		return false;
	}
	
	public boolean validarHorario (String unHorario){
		if(this.horario == unHorario)
			return true;
		return false;
	}
	
	public boolean validarIngredientes(String unaCondicion){
		int size = this.ingredientes.size();
		boolean valor = true;
		for (int i = 0; i < size; i++){
			if (unaCondicion == "VEGETARIANO" && this.ingredientes.get(i) == "CARNE"){
				valor = false;
			}
			else if (unaCondicion == "DIABETICO" && this.ingredientes.get(i) == "AZUCAR"){
				valor = false;
			}
		}
		
		return valor;
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
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
