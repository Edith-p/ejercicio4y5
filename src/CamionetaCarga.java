public class CamionetaCarga extends Vehiculo {
    protected double capMax;

    public CamionetaCarga(String placa, String marca, String modelo, double tarifaDiaria, double capMax) {
        super(placa, marca, modelo, tarifaDiaria);
        //validacion
        if (capMax <= 0){
            throw new IllegalArgumentException("La capacidad máxima tiene que ser mayor a 0."); 
        }

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
        return String.format("""
                CAMIONETA DE CARGA
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                Placa:          %s
                Marca:          %s
                Modelo:         %s
                Tarifa diaria:  Q%.2f
                Estado:         %s
                Cap. maxima:    %.1f toneladas""",
                placa, marca, modelo, tarifaDiaria,
                estado, capMax);
    }

    @Override
    public String categoria() {
        return "Camioneta de carga";
    }

    @Override 
    public int indiceCategoria(){
        return 2; 
    }
}