public class Automovil extends Vehiculo {
    protected int pasajeros;
    protected String transmision;

    public Automovil(String placa, String marca, String modelo, double tarifaDiaria, int pasajeros, String transmision) {
        super(placa, marca, modelo, tarifaDiaria);


        //validacion
        if(pasajeros <= 0){
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor a 0"); 
        }
        if (transmision == null || transmision.isBlank()){
            throw new IllegalArgumentException("Se debe ingresar la transmision ");
        }
        if (!transmision.equalsIgnoreCase("Automática") && !transmision.equalsIgnoreCase("Automatica") && !transmision.equalsIgnoreCase("Manual")){
            throw new IllegalArgumentException("La transcripción solo es o automática o manual :/");
        }
        this.pasajeros = pasajeros;
        this.transmision = transmision;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public String getTransmision() {
        return transmision;
    }

    @Override
    public double calcularSubtotal(int dias) {
        double subtotal = tarifaDiaria * dias;
        if (transmision.equalsIgnoreCase("Automatica") || transmision.equalsIgnoreCase("Automática")) subtotal += 50 * dias;
        return subtotal;
    }

    @Override
    public String licenciaNecesaria() {
        return "C";
    }

    @Override
    public int umbralMantenimiento() {
        return 30;
    }

    @Override
    public String informacion() {
        return String.format("""
                AUTOMOVIL
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                Placa:          %s
                Marca:          %s
                Modelo:         %s
                Tarifa diaria:  Q%.2f
                Estado:         %s
                Pasajeros:      %d
                Transmision:    %s""",
                placa, marca, modelo, tarifaDiaria,
                estado, pasajeros, transmision);
    }

    @Override
    public String categoria() {
        return "Automovil";
    }
}