public class Microbus extends Vehiculo {
    protected int pasajeros;
    protected boolean piloto;

    public Microbus(String placa, String marca, String modelo, double tarifaDiaria, int pasajeros, boolean piloto) {
        super(placa, marca, modelo, tarifaDiaria);
        
        //validacion
        if(pasajeros <= 0){
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor a 0"); 
        }
        
        this.pasajeros = pasajeros;
        this.piloto = piloto;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public boolean getPiloto() {
        return piloto;
    }

    @Override
    public double calcularSubtotal(int dias) {
        double subtotal = tarifaDiaria * dias;
        if (piloto) subtotal += 250 * dias;
        return subtotal;
    }

    @Override
    public String licenciaNecesaria() {
        if (piloto) return "NINGUNA";
        return "B";
    }

    @Override
    public int umbralMantenimiento() {
        return 25;
    }

    @Override
    public String informacion() {
        return String.format("""
                MICROBUS
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                Placa:          %s
                Marca:          %s
                Modelo:         %s
                Tarifa diaria:  Q%.2f
                Estado:         %s
                Pasajeros:      %d
                Incluye piloto: %s""",
                placa, marca, modelo, tarifaDiaria,
                estado, pasajeros, piloto ? "Si" : "No");
    }

    @Override
    public String categoria() {
        return "Microbus";
    }
}