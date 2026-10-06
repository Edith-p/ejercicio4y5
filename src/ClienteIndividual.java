import java.util.ArrayList;

public class ClienteIndividual extends Cliente {

    public ClienteIndividual(String id, String nombre, ArrayList<String> licencias){
        super(id, nombre, licencias);

        if (id == null || id.length() != 13){
            throw new IllegalArgumentException("El DPI debe ser de 13 digitos exactos.");
        }
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new IllegalArgumentException("el dpi es de datos numericos");
            }
        }
    }

    @Override 
    public double calcularDescuento(double subtotal, int alquileresConfirmados){

        if (alquileresConfirmados>= 3){
            double descuento = subtotal * 0.05; 
            return descuento; 
        }
    return 0; 
    }

    @Override 
    public int limiteAlquileres(){
        return 1; 
    }

    @Override
    public String informacion() {
        return String.format(
            "CLIENTE INDIVIDUAL\n" +
            "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--\n" +
            "Identificador:  %s\n" +
            "Nombre:         %s\n" +
            "Licencias:      %s",
            id,
            nombre,
            licencias
        );
    }

}
