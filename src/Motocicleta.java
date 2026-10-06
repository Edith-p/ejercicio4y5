public class Motocicleta extends Vehiculo {
    protected int cilindraje;

    public Motocicleta(String placa, String marca, String modelo, double tarifaDiaria, int cilindraje) {
        super(placa, marca, modelo, tarifaDiaria);
        //validacion 
        if (cilindraje<=0){
            throw new IllegalArgumentException("El cilindraje debe ser mayor a 0. :("); 
        }
        
        
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public double calcularSubtotal(int dias) {
        double subtotal = tarifaDiaria * dias;
        if (cilindraje > 250) subtotal += 75;
        return subtotal;
    }

    @Override
    public String licenciaNecesaria() {
        return "M";
    }

    @Override
    public int umbralMantenimiento() {
        return 20;
    }

    @Override
    public String informacion() {
        return "Motocicleta | Placa: " + placa + " | Marca: " + marca + " | Modelo: " + modelo + " | Tarifa diaria: Q" + String.format("%.2f", tarifaDiaria) + " | Estado: " + estado + " | Cilindraje: " + cilindraje + " cc";
    }
    
    @Override
    public String categoria() {
        return "Motocicleta";
    }
}