import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Vista vista;
    private double ingresosTotal;
    private double ingresosCategorias;
    private double descuentosTotales;
    private List<Vehiculo> vehiculos;
    private List<Cliente> clientes;
    private List<Alquiler> alquileres;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.ingresosTotal = 0;
        this.ingresosCategorias = 0;
        this.descuentosTotales = 0;
        this.vehiculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.alquileres = new ArrayList<>();
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || vehiculo.getPlaca() == null || vehiculo.getPlaca().isBlank()) {
            vista.mostrarMensaje("Vehiculo invalido -_-");
            return;
        }

        if (buscarVehiculo(vehiculo.getPlaca()) != null) {
            vista.mostrarMensaje("Ya existe un vehiculo con esa placa :/");
            return;
        }

        vehiculos.add(vehiculo);
        vista.mostrarMensaje("Vehiculo registrado correctamente :P");
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null || cliente.getId().isBlank()) {
            vista.mostrarMensaje("Cliente invalido -_-.");
            return;
        }

        if (buscarCliente(cliente.getId()) != null) {
            vista.mostrarMensaje("Ya existe un cliente con ese identificador :/");
            return;
        }

        clientes.add(cliente);
        vista.mostrarMensaje("Cliente registrado correctamente :)");
    }

    public void consultarFlota() {
        if (vehiculos.isEmpty()) {
            vista.mostrarMensaje("No hay vehiculos registrados :/");
            return;
        }

        for (Vehiculo vehiculo : vehiculos) vista.mostrarMensaje(vehiculo.informacion());
    }

    public void consultarClientes() {
        if (clientes.isEmpty()) {
            vista.mostrarMensaje("No hay clientes registrados :/");
            return;
        }

        for (Cliente cliente : clientes) vista.mostrarMensaje(cliente.informacion());
    }

    private Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) return vehiculo;
        }
        return null;
    }

    private Cliente buscarCliente(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equalsIgnoreCase(id)) return cliente;
        }
        return null;
    }

    private int contarAlquileresActivos(Cliente cliente) {
        int cantidad = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente() == cliente && alquiler.getEstado().equalsIgnoreCase("Activo")) cantidad++;
        }
        return cantidad;
    }

    private int contarAlquileresConfirmados(Cliente cliente) {
        int cantidad = 0;
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente() == cliente) cantidad++;
        }
        return cantidad;
    }

    public void cotizarAlquiler(String placa, String id, int dias) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        Cliente cliente = buscarCliente(id);

        if (vehiculo == null) {
            vista.mostrarMensaje("El vehiculo no existe -_-");
            return;
        }

        if (cliente == null) {
            vista.mostrarMensaje("El cliente no existe -_-");
            return;
        }

        if (dias <= 0) {
            vista.mostrarMensaje("Los dias deben ser mayores a 0 -_-");
            return;
        }

        double subtotal = vehiculo.calcularSubtotal(dias);
        int confirmados = contarAlquileresConfirmados(cliente);
        double descuento = cliente.calcularDescuento(subtotal, confirmados);
        double total = subtotal - descuento;

        vista.mostrarMensaje(vehiculo.informacion());
        vista.mostrarMensaje(String.format("Subtotal: Q%.2f | Descuento: Q%.2f | Total: Q%.2f", subtotal, descuento, total));

        boolean puedeAlquilar = true;

        if (!vehiculo.getEstado().equalsIgnoreCase("Disponible")) {
            vista.mostrarMensaje("No puede alquilarlo: el vehiculo no esta disponible :(");
            puedeAlquilar = false;
        }

        if (!licenciaValida(cliente, vehiculo)) {
            vista.mostrarMensaje("No puede alquilarlo: licencia inadecuada :/");
            puedeAlquilar = false;
        }

        if (contarAlquileresActivos(cliente) >= cliente.limiteAlquileres()) {
            vista.mostrarMensaje("No puede alquilarlo: alcanzo el limite de alquileres activos :/");
            puedeAlquilar = false;
        }

        if (puedeAlquilar) vista.mostrarMensaje("El cliente puede alquilar este vehiculo :D");
    }

    public void confirmarAlquiler(String placa, String id, int dias) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        Cliente cliente = buscarCliente(id);

        if (vehiculo == null) {
            vista.mostrarMensaje("El vehiculo no existe.");
            return;
        }

        if (cliente == null) {
            vista.mostrarMensaje("El cliente no existe.");
            return;
        }

        if (dias <= 0) {
            vista.mostrarMensaje("Los dias deben ser mayores a 0.");
            return;
        }

        if (!vehiculo.getEstado().equalsIgnoreCase("Disponible")) {
            vista.mostrarMensaje("El vehiculo no esta disponible.");
            return;
        }

        if (!licenciaValida(cliente, vehiculo)) {
            vista.mostrarMensaje("El cliente no tiene una licencia adecuada.");
            return;
        }

        if (contarAlquileresActivos(cliente) >= cliente.limiteAlquileres()) {
            vista.mostrarMensaje("El cliente alcanzo su limite de alquileres activos.");
            return;
        }

        double subtotal = vehiculo.calcularSubtotal(dias);
        int confirmados = contarAlquileresConfirmados(cliente);
        double descuento = cliente.calcularDescuento(subtotal, confirmados);
        double total = subtotal - descuento;

        vista.mostrarMensaje(String.format("Subtotal: Q%.2f | Descuento: Q%.2f | Total: Q%.2f", subtotal, descuento, total));

        String respuesta = vista.leerString("Confirmar alquiler? (S/N): ");
        if (!respuesta.equalsIgnoreCase("S")) {
            vista.mostrarMensaje("Alquiler cancelado.");
            return;
        }

        int correlativo = alquileres.size() + 1;
        Alquiler alquiler = new Alquiler(correlativo, cliente, vehiculo, dias, subtotal, descuento, total);

        alquileres.add(alquiler);
        vehiculo.setEstado("Alquilado");
        ingresosTotal += total;
        descuentosTotales += descuento;

        vista.mostrarMensaje("Alquiler confirmado. Correlativo: " + correlativo);
    }

    private boolean licenciaValida(Cliente cliente, Vehiculo vehiculo) {
        String licencia = vehiculo.licenciaNecesaria();

        if (licencia.equalsIgnoreCase("NINGUNA")) return true;
        if (licencia.equalsIgnoreCase("M")) return cliente.tieneLicencia("M");
        if (licencia.equalsIgnoreCase("C")) return cliente.tieneLicencia("C") || cliente.tieneLicencia("B") || cliente.tieneLicencia("A");
        if (licencia.equalsIgnoreCase("B")) return cliente.tieneLicencia("B") || cliente.tieneLicencia("A");

        return false;
    }

    public void registrarDevolucion(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            vista.mostrarMensaje("El vehiculo no existe.");
            return;
        }

        if (!vehiculo.getEstado().equalsIgnoreCase("Alquilado")) {
            vista.mostrarMensaje("El vehiculo no esta alquilado.");
            return;
        }

        Alquiler alquilerActivo = null;

        for (Alquiler alquiler : alquileres) {
            if (alquiler.getVehiculo() == vehiculo && alquiler.getEstado().equalsIgnoreCase("Activo")) {
                alquilerActivo = alquiler;
                break;
            }
        }

        if (alquilerActivo == null) {
            vista.mostrarMensaje("No existe un alquiler activo para este vehiculo.");
            return;
        }

        alquilerActivo.finalizar();
        vehiculo.aumentarDias(alquilerActivo.getDias());

        if (vehiculo.getDiasAcumulados() >= vehiculo.umbralMantenimiento()) vehiculo.setEstado("En mantenimiento");
        else vehiculo.setEstado("Disponible");

        vista.mostrarMensaje("Devolucion registrada. Estado del vehiculo: " + vehiculo.getEstado());
    }

    public void finalizarMantenimiento(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            vista.mostrarMensaje("El vehiculo no existe.");
            return;
        }

        if (!vehiculo.getEstado().equalsIgnoreCase("En mantenimiento")) {
            vista.mostrarMensaje("El vehiculo no esta en mantenimiento.");
            return;
        }

        vehiculo.reiniciarDias();
        vehiculo.setEstado("Disponible");
        vista.mostrarMensaje("Mantenimiento finalizado. El vehiculo esta disponible.");
    }

    public void reporteVehiculos() {
        int autos = 0, motos = 0, camionetas = 0, microbuses = 0;
        int disponibles = 0, alquilados = 0, mantenimiento = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo instanceof Automovil) autos++;
            else if (vehiculo instanceof Motocicleta) motos++;
            else if (vehiculo instanceof CamionetaCarga) camionetas++;
            else if (vehiculo instanceof Microbus) microbuses++;

            if (vehiculo.getEstado().equalsIgnoreCase("Disponible")) disponibles++;
            else if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) alquilados++;
            else if (vehiculo.getEstado().equalsIgnoreCase("En mantenimiento")) mantenimiento++;
        }

        vista.mostrarMensaje(String.format("Automoviles: %d | Motocicletas: %d | Camionetas: %d | Microbuses: %d", autos, motos, camionetas, microbuses));
        vista.mostrarMensaje(String.format("Disponibles: %d | Alquilados: %d | En mantenimiento: %d", disponibles, alquilados, mantenimiento));
    }

    // Este método debe de mejorarse
    public void reporteIngresos() {
        vista.mostrarMensaje("===== INGRESOS POR CATEGORIA =====");

        for (Alquiler alquiler : alquileres) {
            vista.mostrarMensaje(String.format("%s | Q%.2f", alquiler.getVehiculo().categoria(), alquiler.getTotal()));
        }

        vista.mostrarMensaje(String.format("Ingresos totales: Q%.2f", ingresosTotal));
    }

    public void reporteDescuentos() {
        vista.mostrarMensaje(String.format("Descuentos otorgados: Q%.2f", descuentosTotales));
    }

    public void reporteAlquileresActivos() {
        boolean encontrados = false;

        for (Alquiler alquiler : alquileres) {
            if (alquiler.getEstado().equalsIgnoreCase("Activo")) {
                vista.mostrarMensaje(String.format("Alquiler #%d | Cliente: %s | Vehiculo: %s | Total: Q%.2f", alquiler.getCorrelativo(), alquiler.getCliente().getNombre(), alquiler.getVehiculo().getPlaca(), alquiler.getTotal()));
                encontrados = true;
            }
        }

        if (!encontrados) vista.mostrarMensaje("No hay alquileres activos.");
    }

    public void historialCliente(String id) {
        Cliente cliente = buscarCliente(id);

        if (cliente == null) {
            vista.mostrarMensaje("El cliente no existe.");
            return;
        }

        double totalPagado = 0;
        boolean encontrados = false;

        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente() == cliente) {
                vista.mostrarMensaje(String.format("Alquiler #%d | Vehiculo: %s | Dias: %d | Total: Q%.2f | Estado: %s", alquiler.getCorrelativo(), alquiler.getVehiculo().getPlaca(), alquiler.getDias(), alquiler.getTotal(), alquiler.getEstado()));
                totalPagado += alquiler.getTotal();
                encontrados = true;
            }
        }

        if (!encontrados) vista.mostrarMensaje("El cliente no tiene alquileres.");
        vista.mostrarMensaje(String.format("Total pagado: Q%.2f", totalPagado));
    }
}