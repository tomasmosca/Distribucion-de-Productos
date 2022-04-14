package clases;

import java.util.ArrayList;

public class Depositos {
	
	private ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
	private boolean esPropio;
	private boolean esTercerizado;
	private boolean refrigeracion;
	private double capacidad;
	private double costoPorTonelada;
	
	public Depositos(double capacidad,boolean refrigeracion,boolean esPropio) {
		this.capacidad = capacidad;
		this.esPropio = esPropio;
		this.refrigeracion = refrigeracion;
	}
	
	public Depositos(double capacidad,boolean refrigeracion,boolean esTercerizado, double costoPorTonelada) {
		this.capacidad = capacidad;
		this.esTercerizado = esTercerizado;
		this.refrigeracion = refrigeracion;
		this.costoPorTonelada = costoPorTonelada;
	}
	
	public void incorporaPaquete(Paquete p) {
		paquetes.add(p);
		setCapacidad(capacidad-1);
	}

	public boolean getEsPropio() {
		return esPropio;
	}

	public void setEsPropio(boolean esPropio) {
		this.esPropio = esPropio;
	}

	public boolean getEsTercerizado() {
		return esTercerizado;
	}

	public void setEsTercerizado(boolean esTercerizado) {
		this.esTercerizado = esTercerizado;
	}

	public boolean getRefrigeracion() {
		return refrigeracion;
	}

	public void setRefrigeracion(boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public double getCostoPorTonelada() {
		return costoPorTonelada;
	}

	public void setCostoPorTonelada(double costoPorTonelada) {
		this.costoPorTonelada = costoPorTonelada;
	}

	protected ArrayList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(ArrayList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	@Override
	public String toString() {
		if(esPropio) {
			return "DepositoPropio";
		}else if(esTercerizado) {
			return "DepositoTercerizado";
		}
		return null;
	}
}
