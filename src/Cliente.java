import java.util.ArrayList;

public abstract class Cliente {
    protected String id;
    protected String nombre; 
    protected ArrayList<String> licencias = new ArrayList<>(); 



    protected Cliente(String id, String nombre, ArrayList<String> licencias ) {
        if(id == null || nombre == null || licencias.isEmpty()){
            throw new IllegalArgumentException("Este campo no puede estar vacio");
        }
    this.id = id; 
    this.nombre = nombre; 
    this.licencias = licencias; 
    }


    public String getId(){
        return id; 
    }

    public String getNombre(){
        return nombre; 
    }

    public ArrayList<String> getLicencias(){
        return licencias;
    }

    public boolean tieneLicencia(String licenciaBuscada) {
        if (licenciaBuscada == null
                || licenciaBuscada.equalsIgnoreCase("Ninguna")) {
            return true;
        }

        for (String licenciaCliente : licencias) {
            if (licenciaCliente.equalsIgnoreCase(licenciaBuscada)) {
                return true;
            }
        }

        return false;
    }
    
    

    public abstract double calcularDescuento(double subtotal, int contarAlquileresConfirmados); 

    public abstract int limiteAlquileres();

    public abstract String informacion(); 
}

