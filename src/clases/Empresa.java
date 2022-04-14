package clases;

import java.util.ArrayList;
import java.util.Iterator;

public class Empresa {
	
	private String CUIT;
	private String Nombre;
	private ArrayList<Depositos> depositos = new ArrayList<Depositos>();
	private ArrayList<Transportes> transportes = new ArrayList<Transportes>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	
	public Empresa(String CUIT, String Nombre) {
		this.CUIT = CUIT;
		this.Nombre = Nombre;
	}
	
	public int agregarDeposito(double capacidad,boolean refrigerado,boolean esPropio) {
		Depositos d = new Depositos(capacidad, refrigerado, esPropio);
		if(!esPropio) {
			d.setEsTercerizado(true);
		}
		depositos.add(d);
		return depositos.size();
	}
	
	public int agregarDepTercerizFrio(double capacidad, double costoPorTonelada) {
		Depositos d = new Depositos(capacidad, true, true, costoPorTonelada);
		depositos.add(d);
		return depositos.size();
	}
	
	public void iniciarViaje(String idTransp) {
		for(Transportes t : transportes) {
			if(t.getIdentificacion().equals(idTransp)) {
				t.iniciaViaje();
			}
		}
	}
	
	public void finalizarViaje(String idTransp) {
		for(Transportes t : transportes) {
			if(t.getIdentificacion().equals(idTransp)) {
				if(t.getEnViaje()) {                            //si se encuentra en viaje
					Iterator<Viaje> it = viajes.iterator();
					while(it.hasNext()) {
						Viaje v = it.next();
						if(v.getDestino().equals(t.getViajeActual().getDestino())) {
							t.descargarTransporte();
							it.remove();                       //elimina viaje del arreglo de viajes
						}
					}
					t.setEnViaje(false);
				}else {
					throw new RuntimeException("El transporte no inicio el viaje");
				}
			}
		}
	}

	public void agregarTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico, double costoKm, double segCarga) {
		Transportes t = new Trailer(idTransp, capacidad, cargaMax, frigorifico, costoKm, segCarga);
		transportes.add(t);
		t.setEsTrailer(true);
	}

	public void agregarMegaTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico, double costoKm, double segCarga, double costoFijo, double comida) {
		Transportes t = new MegaTrailer(idTransp, capacidad, cargaMax, frigorifico, costoKm, segCarga, costoFijo, comida);
		transportes.add(t);
		t.setEsMegaTrailer(true);
	}

	public void agregarFlete(String idTransp, double cargaMax, double capacidad, double costoKm, int acomp, double costoPorAcom) {
		Transportes t = new Fletes(idTransp, capacidad, cargaMax, costoKm, acomp, costoPorAcom);
		transportes.add(t);
		t.setEsFlete(true);
	}
	
	public void agregarDestino(String destino, int km) {
		Viaje v = new Viaje(destino, km);
		viajes.add(v);
	}

	public void asignarDestino(String idTransp, String destino) {
		for(Transportes t : transportes) {
			if(t.getIdentificacion().equals(idTransp)) {
				if(t.getViajeActual().getDestino().length()==0) {  //si es igual a 0 significa que no hay un viaje asignado en ese transporte
					if(t.getPaquetes().size()==0) {
						for(Viaje v : viajes) {
							if(v.getDestino().equals(destino)) {
								t.asignacionDestino(v);   //se asigna el destino
							}
						}
						if(t.getViajeActual().getDestino().length()==0) { //si sigue siendo = a 0 es porque no se encontro el viaje en array viajes
							throw new RuntimeException("No se encontro el destino especificado");
						}
					}else {
						throw new RuntimeException("El transporte ya tiene mercaderia cargada");
					}
				}else {
					throw new RuntimeException("El transporte ya tiene destino asignado");
				}
			}
		}
	}

	public boolean incorporarPaquete(String destino, double peso, double volumen, boolean frio) { 
		Paquete p = new Paquete(destino, peso, volumen, frio);
		for(Depositos d : depositos) {
			if(d.getRefrigeracion() == frio && d.getCapacidad()>=0) {
				d.incorporaPaquete(p);     //metodo que incorpora un paquete en un deposito
				return true;
			}
		}
		return false;
	}

	public double cargarTransporte(String idTransp) { 
		double volumen = 0;
		for(Transportes t : transportes) {
			if (t.getIdentificacion().equals(idTransp)) {
				if(t.getEnViaje()==false) {
					for(Depositos d : depositos) {
						if(t.getRefrigeracion() == d.getRefrigeracion()) {
							t.cargaDeTransporte(d);                         // llama metodo que toma un deposito
							volumen = t.getVolumenCargaActual();
						}
					}
				}else {
					throw new RuntimeException("El transporte ya se encuentra en viaje");
				}
			}
		}
		return volumen;
	}
	
	public double obtenerCostoViaje(String idTransp) {  // la adicion de costo tercerizado se hace directamente en las clases trailer y megatrailer
		double costo = 0;
		for(Transportes t : transportes) {
			if(t.getIdentificacion().equals(idTransp)) {
				if(t.getEnViaje()) {              //si se encuentra en viaje realiza la obtencion del costo, sino no lo realiza hasta que inicie el viaje
					costo = t.obtenerCostoDelViaje();    
					return costo;
				}else {
					throw new RuntimeException("El transporte no se encuentra en viaje"); 
				}
			}
		}
		return costo;
	}

	public String obtenerTransporteIgual(String idTransp) {
		String id = null;
		for(Transportes t : transportes) {
			if(t.getIdentificacion().equals(idTransp)) {
				for (Transportes t2 : transportes) {
					if(!t2.getIdentificacion().equals(idTransp)) {
						if(t.obtencionTranspIgual(t2)) {
							id = t2.getIdentificacion();
							return id;
						}
					}
				}
			}
		}
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empresa [CUIT=").append(CUIT).append(", Nombre=").append(Nombre).append(", depositos=")
				.append(depositos).append(", transportes=").append(transportes).append(", viajes=").append(viajes)
				.append("]");
		return builder.toString();
	}
}
