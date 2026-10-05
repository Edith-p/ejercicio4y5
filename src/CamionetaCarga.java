public class CamionetaCarga extends Vehiculo {
    protected double capMax;

    public CamionetaCarga(String placa, String marca, String modelo, double tarifaDiaria, double capMax) {
        super(placa, marca, modelo, tarifaDiaria);
        this.capMax = capMax;
    }

    public double getCapMax() {
        return capMax;
    }

    @Override
    public double calcularSubtotal(int dias) {
        return (tarifaDiaria * dias) + (100 * capMax * dias);
    }

    @Override
    public String licenciaNecesaria() {
        return "B";
    }

    @Override
    public int umbralMantenimiento() {
        return 15;
    }

    @Override
    public String informacion() {
        return "Camioneta de carga | Placa: " + placa + " | Marca: " + marca + " | Modelo: " + modelo + " | Tarifa diaria: Q" + String.format("%.2f", tarifaDiaria) + " | Estado: " + estado + " | Capacidad maxima: " + capMax + " toneladas";
    }
}