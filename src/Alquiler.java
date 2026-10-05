public class Alquiler {
    //Atributos
    protected int correlativo;
    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected int dias;
    protected double subtotal;
    protected double descuentoAplicado;
    protected double total;
    protected String estado;

    //Constructor
    public Alquiler(int correlativo, Cliente cliente, Vehiculo vehiculo, int dias, double subtotal, double descuentoAplicado, double total) {
        this.correlativo = correlativo;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.subtotal = subtotal;
        this.descuentoAplicado = descuentoAplicado;
        this.total = total;
        this.estado = "Activo";
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void finalizar() {
        estado = "Finalizado";
    }
}