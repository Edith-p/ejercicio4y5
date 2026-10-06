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
        return String.format("""
                MOTOCICLETA
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                Placa:          %s
                Marca:          %s
                Modelo:         %s
                Tarifa diaria:  Q%.2f
                Estado:         %s
                Cilindraje:     %d cc""",
                placa, marca, modelo, tarifaDiaria,
                estado, cilindraje);
}
    
    @Override
    public String categoria() {
        return "Motocicleta";
    }

    @Override 
    public int indiceCategoria(){
        return 1; 
    }
}