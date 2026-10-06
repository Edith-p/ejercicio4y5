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
        return "Microbus | Placa: " + placa + " | Marca: " + marca + " | Modelo: " + modelo + " | Tarifa diaria: Q" + String.format("%.2f", tarifaDiaria) + " | Estado: " + estado + " | Pasajeros: " + pasajeros + " | Piloto: " + (piloto ? "Si" : "No");
    }

    @Override
    public String categoria() {
        return "Microbus";
    }
}