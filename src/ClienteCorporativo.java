import java.util.ArrayList;

public class ClienteCorporativo extends Cliente{

    protected String nombreContacto; 

    public ClienteCorporativo(String id, String nombre, ArrayList<String> licencias, String nombreContacto){
        super(id, nombre, licencias);
        this.nombreContacto = nombreContacto; 
    }


    public String getNombreContacto(){
        return nombreContacto; 
    }
      
    @Override 
    public double calcularDescuento(double subtotal, int alquileresConfirmados){

        if (alquileresConfirmados>= 3){
            double descuento = subtotal * 0.10; 
            return descuento; 
        }
    return 0; 
    }

    @Override 
    public int limiteAlquileres(){
        return 3; 
    }

    @Override 
    public String informacion() {
        return String.format("Cliente | id: %s | Nombre: %s | Licencias: %s |Nombre Contacto: %s", id, nombre, licencias, nombreContacto);
    }
}
