public abstract class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double tarifaDiaria;
    protected String estado;
    protected int diasAcumulados;

    protected Vehiculo(String placa, String marca, String modelo, double tarifaDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.estado = "Disponible";
        this.diasAcumulados = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public String getEstado() {
        return estado;
    }

    public int getDiasAcumulados() {
        return diasAcumulados;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void aumentarDias(int dias) {
        diasAcumulados += dias;
    }

    public void reiniciarDias() {
        diasAcumulados = 0;
    }

    public abstract double calcularSubtotal(int dias);

    public abstract String licenciaNecesaria();

    public abstract int umbralMantenimiento();

    public abstract String informacion();

    public abstract String categoria();
}