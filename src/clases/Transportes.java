package clases;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Transportes{
	
	private String identificacion;
	private double cargaMax;
	private double volumenCargaActual;
	private double capacidad;
	private double costoKm;
	private boolean refrigeracion;
	private double segCarga;
	private boolean enViaje;
	private double adicionCostoTercerizado;
	private boolean esTrailer;
	private boolean esMegaTrailer;
	private boolean esFlete;
	
	private Viaje viajeActual = new Viaje("", 0);
	private ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
	
	public Transportes(String identificacion,double capacidad,double cargaMax,boolean refrigeracion, double costoKm, double segCarga) {
		this.identificacion = identificacion;
		this.capacidad = capacidad;
		this.cargaMax = cargaMax;
		this.refrigeracion = refrigeracion;
		this.costoKm = costoKm;
		this.segCarga = segCarga;
	}
	
	public Transportes(String identificacion, double capacidad, double cargaMax, double costoKm, boolean refrigeracion) {
		this.identificacion = identificacion;
		this.capacidad = capacidad;
		this.cargaMax = cargaMax;
		this.costoKm = costoKm;
	}
	
	public abstract double obtenerCostoDelViaje();
	
	public void asignacionDestino(Viaje v) {
		if(esTrailer == true && v.getKm() <= 500) {
			setViajeActual(v);
		}else if(esMegaTrailer == true && v.getKm() > 500) {
			setViajeActual(v);
		}else if(esFlete == true){
			setViajeActual(v);
		}else if(esTrailer == true && v.getKm() > 500) {
			throw new RuntimeException("Error en asignacion de destino, se debe asignar un destino acorde al transporte");
		}else if(esMegaTrailer == true && v.getKm() <= 500) {
			throw new RuntimeException("Error en asignacion de destino, se debe asignar un destino acorde al transporte");
		}
	}
	
	public boolean obtencionTranspIgual(Transportes otro) {  // equals
		boolean tipoIgual = false;
		boolean destinoIgual = false;
		boolean cargaIgual = false;
		if((esTrailer && otro.getEsTrailer()) || (esMegaTrailer && otro.getEsMegaTrailer()) || (esFlete && otro.getEsFlete())) {
			tipoIgual = true;
		}
		if(viajeActual.getDestino().equals(otro.getViajeActual().getDestino())) {
			destinoIgual = true;
		}
		if(paquetes.size() == otro.getPaquetes().size()) {  //si hay misma cantidad y mismo destino en todos entonces cargaIgual=true
			for(Paquete p : paquetes) {
				for(Paquete p2 : otro.getPaquetes()) {
					if(!p.getDestino().equals(p2.getDestino())) {      
						return false;
					}else {
						cargaIgual = true;
					}
				}
			}
		}
		if(tipoIgual && destinoIgual && cargaIgual) {
			return true;
		}
		return false;
	}
	
	public void cargaDeTransporte(Depositos d) {
		Iterator<Paquete> it = d.getPaquetes().iterator();
		while(it.hasNext()) {                                    //recorre los paquetes del deposito
			Paquete p = it.next();
			if(capacidad!=0 && cargaMax>=p.getVolumen()) {
				if(viajeActual.getDestino().length()!=0) {      //si es distinto de cero es que tiene viaje asignado
					if(viajeActual.getDestino().equals(p.getDestino())) {
						paquetes.add(p);
						setCapacidad(capacidad-1);
						setCargaMax(cargaMax-p.getVolumen());
						volumenCargaActual+=p.getVolumen();
						if(d.getEsTercerizado() == true && d.getRefrigeracion() == true) { //si el deposito es tercerizadoFrio se agrega el costo adicional
							adicionCostoTercerizado += p.getPeso()*d.getCostoPorTonelada()/1000;
						}
						it.remove();   //se elimina el paquete del deposito
					}
				}else {
					throw new RuntimeException("El transporte debe tener asignado un destino");
				}
			}
		}
	}
	
	public void descargarTransporte() {               //se llama en finalizarViaje
		Iterator<Paquete> it = paquetes.iterator();
		while (it.hasNext()) {
			Paquete p = it.next();
			if(p.getDestino().equals(viajeActual.getDestino())) {
				setCapacidad(capacidad+1);
				setCargaMax(cargaMax+p.getVolumen());
				it.remove();
			}
		}
		setVolumenCargaActual(0);
		setAdicionCostoTercerizado(0);
		viajeActual.setDestino("");
		viajeActual.setKm(0);
	}
	
	public void iniciaViaje() {  // se llama en iniciarViaje
		if(paquetes.size()>0){
			if(!enViaje) {
				if(viajeActual.getDestino().length()!=0) {
					setEnViaje(true);
				}else {
					throw new RuntimeException("El transporte debe tener un destino asignado");
				}
			}else {
				throw new RuntimeException("El transporte ya se encuentra en viaje");
			}
		}else {
			throw new RuntimeException("El transporte no se ha cargado");
		}
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	protected ArrayList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(ArrayList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public double getCargaMax() {
		return cargaMax;
	}

	public void setCargaMax(double cargaMax) {
		this.cargaMax = cargaMax;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public double getCostoKm() {
		return costoKm;
	}

	public void setCostoKm(double costoKm) {
		this.costoKm = costoKm;
	}

	public boolean getRefrigeracion() {
		return refrigeracion;
	}

	public void setRefrigeracion(boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}

	public double getSegCarga() {
		return segCarga;
	}

	public void setSegCarga(double segCarga) {
		this.segCarga = segCarga;
	}

	public boolean getEnViaje() {
		return enViaje;
	}

	public void setEnViaje(boolean enViaje) {
		this.enViaje = enViaje;
	}

	public double getAdicionCostoTercerizado() {
		return adicionCostoTercerizado;
	}

	public void setAdicionCostoTercerizado(double adicionCostoTercerizado) {
		this.adicionCostoTercerizado = adicionCostoTercerizado;
	}

	public boolean getEsTrailer() {
		return esTrailer;
	}

	public void setEsTrailer(boolean esTrailer) {
		this.esTrailer = esTrailer;
	}

	public boolean getEsMegaTrailer() {
		return esMegaTrailer;
	}

	public void setEsMegaTrailer(boolean esMegaTrailer) {
		this.esMegaTrailer = esMegaTrailer;
	}

	public boolean getEsFlete() {
		return esFlete;
	}

	public void setEsFlete(boolean esFlete) {
		this.esFlete = esFlete;
	}

	public Viaje getViajeActual() {
		return viajeActual;
	}

	public void setViajeActual(Viaje viajeActual) {
		this.viajeActual = viajeActual;
	}

	public double getVolumenCargaActual() {
		return volumenCargaActual;
	}

	public void setVolumenCargaActual(double volumenCargaActual) {
		this.volumenCargaActual = volumenCargaActual;
	}

	@Override
	public String toString() {
		return identificacion;
	}

}
