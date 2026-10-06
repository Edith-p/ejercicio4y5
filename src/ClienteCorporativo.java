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
        return subtotal * 0.10;
    }

    @Override 
    public int limiteAlquileres(){
        return 3; 
    }

    @Override
    public String informacion() {
        return String.format(
            "CLIENTE CORPORATIVO\n" +
            "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--\n" +
            "Identificador:  %s\n" +
            "Empresa:        %s\n" +
            "Contacto:       %s\n" +
            "Licencias:      %s",
            id,
            nombre,
            nombreContacto,
            licencias
        );
    }
}
