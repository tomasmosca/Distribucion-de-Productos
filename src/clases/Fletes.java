package clases;

public class Fletes extends Transportes {
	
	private int acomp;
	private double costoPorAcom;

	public Fletes(String identificacion, double capacidad, double cargaMax, double costoKm, int acomp, double costoPorAcom) {
		super(identificacion, capacidad, cargaMax, costoKm, false);
		this.acomp = acomp;
		this.costoPorAcom = costoPorAcom;
	}

	public int getAcomp() {
		return acomp;
	}

	public void setAcomp(int acomp) {
		this.acomp = acomp;
	}

	public double getCostoPorAcom() {
		return costoPorAcom;
	}

	public void setCostoPorAcom(double costoPorAcom) {
		this.costoPorAcom = costoPorAcom;
	}

	@Override
	public double obtenerCostoDelViaje() {
		double costo = 0;
		costo+=getCostoKm()*getViajeActual().getKm()+costoPorAcom*acomp;
		return costo;
	}

}
