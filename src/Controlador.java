import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private Vista vista;
    private double ingresosTotal;
    private double descuentosTotales;
    private List<Vehiculo> vehiculos;
    private List<Cliente> clientes;
    private List<Alquiler> alquileres;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.ingresosTotal = 0;
        this.descuentosTotales = 0;
        this.vehiculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.alquileres = new ArrayList<>();

        datosIniciales(); 
    }

    public void iniciar() {
         
        boolean continuar = true; 
        while(continuar) {
            int opcion = vista.mostrarMenu(); 
            

            switch (opcion) {
                case 1:
                    nuevoRegistroVehiculo();
                    break;

                case 2: 
                    nuevoRegistroCliente();
                    break; 

                case 3: 
                    consultarFlota(); 
                    break; 

                case 4: 
                    consultarClientes();
                    break; 

                case 5: {
                    cotizarAlquiler(); 
                    break; 
                }

                case 6: {
                    confirmarAlquiler();
                    break; 
                }
                
                case 7: 
                {
                    String placa = vista.leerString("Placa del vehiculo a devolver: "); 
                    registrarDevolucion(placa);
                    break; 
                }
                
                case 8: 
                {
                    String placa = vista.leerString("Placa: "); 
                    finalizarMantenimiento(placa);
                    break;
                }
        

                case 9: 
                    reporteVehiculos();
                    break; 

                case 10: 
                    reporteIngresos(); 
                    break; 

                case 11: 
                    reporteDescuentos();
                    break; 

                case 12: 
                    reporteAlquileresActivos(); 
                    break; 
                
                case 13: 
                    String id = vista.leerString("Identificacion del cliente: "); 
                    historialCliente(id); 
                    break; 

                case 0: 
                    vista.mostrarMensaje("Gracias por visitar el programa :D");
                    continuar = false;
                    break;

                default: 
                    vista.mostrarMensaje("opcion no válida");
            }
        }
    }
        

    private void datosIniciales() {
        // Automóviles
        Automovil auto1 = new Automovil(
            "P001AAA",
            "Toyota",
            "Corolla",
            250,
            5,
            "Automatica"
        );

        Automovil auto2 = new Automovil(
            "P002AAA",
            "Honda",
            "Civic",
            225,
            5,
            "Manual"
        );

        // Queda cerca del umbral de 30 días.
        auto1.aumentarDias(29);

        vehiculos.add(auto1);
        vehiculos.add(auto2);

        // Motocicletas
        Motocicleta moto1 = new Motocicleta(
            "M001AAA",
            "Honda",
            "CBR",
            125,
            300
        );

        Motocicleta moto2 = new Motocicleta(
            "M002AAA",
            "Yamaha",
            "FZ",
            100,
            200
        );

        vehiculos.add(moto1);
        vehiculos.add(moto2);

        // Camionetas de carga
        CamionetaCarga camioneta1 =
                new CamionetaCarga(
                    "C001AAA",
                    "Ford",
                    "Ranger",
                    200,
                    1.5
                );

        CamionetaCarga camioneta2 =
                new CamionetaCarga(
                    "C002AAA",
                    "Toyota",
                    "Hilux",
                    225,
                    2.0
                );

        vehiculos.add(camioneta1);
        vehiculos.add(camioneta2);

        // Microbuses
        Microbus microbus1 = new Microbus(
            "B001AAA",
            "Toyota",
            "Hiace",
            450,
            15,
            true
        );

        Microbus microbus2 = new Microbus(
            "B002AAA",
            "Hyundai",
            "H1",
            375,
            12,
            false
        );

        vehiculos.add(microbus1);
        vehiculos.add(microbus2);

        // Licencias
        ArrayList<String> licenciasA =
                new ArrayList<>();
        licenciasA.add("A");

        ArrayList<String> licenciasM =
                new ArrayList<>();
        licenciasM.add("M");

        ArrayList<String> licenciasB =
                new ArrayList<>();
        licenciasB.add("B");

        ArrayList<String> licenciasC =
                new ArrayList<>();
        licenciasC.add("C");

        // Clientes individuales
        ClienteIndividual individual1 =
                new ClienteIndividual(
                    "1234567890123",
                    "Ana López",
                    licenciasA
                );

        ClienteIndividual individual2 =
                new ClienteIndividual(
                    "9876543210123",
                    "Carlos Pérez",
                    licenciasM
                );

        clientes.add(individual1);
        clientes.add(individual2);

        // Clientes corporativos
        ClienteCorporativo corporativo1 =
                new ClienteCorporativo(
                    "1234567-8",
                    "Transportes GT",
                    licenciasB,
                    "María García"
                );

        ClienteCorporativo corporativo2 =
                new ClienteCorporativo(
                    "8765432-1",
                    "Servicios Unidos",
                    licenciasC,
                    "José López"
                );

        clientes.add(corporativo1);
        clientes.add(corporativo2);

        // Tres alquileres anteriores de individual1.
        // Permiten demostrar el descuento en su cuarto alquiler.
        Alquiler anterior1 = new Alquiler(
            1,
            individual1,
            auto2,
            1,
            225,
            0,
            225
        );

        Alquiler anterior2 = new Alquiler(
            2,
            individual1,
            moto2,
            2,
            200,
            0,
            200
        );

        Alquiler anterior3 = new Alquiler(
            3,
            individual1,
            camioneta1,
            1,
            350,
            0,
            350
        );

        anterior1.finalizar();
        anterior2.finalizar();
        anterior3.finalizar();

        alquileres.add(anterior1);
        alquileres.add(anterior2);
        alquileres.add(anterior3);
    }

    public void nuevoRegistroVehiculo(){
        vista.mostrarMensaje("""
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                              VEHICULOS 
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                1. Automovil
                2. Motocicleta 
                3. Camioneta de carga 
                4. Microbus""");

        int tipo = vista.leerEntero("Selecciona el tipo de vehículo :): "); 
        
        if (tipo < 1 || tipo > 4){
            vista.mostrarMensaje("Tipo invalido");
            return; 
        }

        String placa = vista.leerString("Placa: "); 
        String marca = vista.leerString("Marca: ");
        String modelo = vista.leerString("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: "); 
        try { 
            Vehiculo vehiculo;
            switch (tipo){
                case 1: 
                    int pasajeros = vista.leerEntero("Cantidad de pasajeros: "); 
                    String transmision = vista.leerString("Transmision: "); 
                    vehiculo = new Automovil(placa, marca, modelo, tarifa, pasajeros, transmision);
                    break; 

                case 2: 
                    int cilindraje =vista.leerEntero("Cilindraje: "); 
                    vehiculo = new Motocicleta(placa, marca, modelo, tarifa, cilindraje); 
                    break; 

                case 3: 
                    double capMax = vista.leerDouble("Capacidad maxima (toneladas) : "); 
                    vehiculo = new CamionetaCarga(placa, marca, modelo, tarifa, capMax);
                    break; 
                case 4: 
                    int pasajerosMicrobus = vista.leerEntero("Cantidad de pasajeros: ");
                    String pilotoRespuesta = vista.leerString("¿Incluye piloto?(Si/No): ");
                    if (!pilotoRespuesta.equalsIgnoreCase("Si")&& !pilotoRespuesta.equalsIgnoreCase("Sí") 
                        && !pilotoRespuesta.equalsIgnoreCase("No") ){
                        vista.mostrarMensaje("Debe responder Si o No ");
                        return; 
                    }

                    boolean piloto = pilotoRespuesta.equalsIgnoreCase("Si") || pilotoRespuesta.equalsIgnoreCase("Sí"); 
                    vehiculo = new Microbus(placa, marca, modelo, tarifa, pasajerosMicrobus, piloto);
                    break; 
                default: 
                    return; 
            }
            registrarVehiculo(vehiculo);
        } catch (IllegalArgumentException e){
            vista.mostrarMensaje("No se ha podido registrar. " +e.getMessage());
        }
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

    public void nuevoRegistroCliente(){
        vista.mostrarMensaje("""
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                              CLIENTES
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                1. Individual
                2. Corporativo 
                """);
        int tipo = vista.leerEntero ("Selecciona el tipo de cliente: ") ;

        if (tipo <1 || tipo >2) {
            vista.mostrarMensaje("Tipo de cliente invalido");
            return; 
        }

        String id = vista.leerString("Identificación: "); 
        String nombre; 

        if (tipo == 1){
            nombre = vista.leerString("Nombre del cliente: "); 
        } else {
            nombre = vista.leerString("Nombre de la empresa: ") ;

        }
        int licenciasCantidad = vista.leerEntero("Ingresa cuantas licencias tienes (cantidad :D): "); 
        if (licenciasCantidad<= 0){
            vista.mostrarMensaje("Debes ingresar al menos 1 licencia -_-"); 
            return; 
        }

        ArrayList<String> licencias = new ArrayList<>();
        for (int i = 1; i <= licenciasCantidad; i++){
            String licencia = vista.leerString("Licencia" +i + "(A, B, C, o M)"); 
            licencia = licencia.toUpperCase(); 

            switch (licencia) {
            case "A":
            case "B":
            case "C":
            case "M":
                if (licencias.contains(licencia)) {
                    vista.mostrarMensaje("La licencia ya fue ingresada.");
                    i--;
                } else {
                    licencias.add(licencia);
                }
                break;

            default:
                vista.mostrarMensaje(
                    "Licencia inválida."
                );
                i--;
            }
        }
        try {
            Cliente cliente; 
            switch (tipo) {
                case 1: 
                cliente = new ClienteIndividual(id, nombre, licencias);
                break; 

                case 2: 
                String nombreContacto = vista.leerString("Nombre de contacto: "); 
                cliente = new ClienteCorporativo(id, nombre, licencias, nombreContacto);
                break; 
                
                default: 
                return; 
            }
            registrarCliente(cliente); 

        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Registro fallido :( " + e.getMessage()); 
        }
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
            vista.mostrarMensaje("No hay vehiculos registrados.");
            return;
        }

        vista.mostrarMensaje("""
                
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
                                FLOTA DE RENTAMOVIL
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-""");

        int numero = 1;

        for (Vehiculo vehiculo : vehiculos) {
            vista.mostrarMensaje(
                "\n[" + numero + "] " + vehiculo.informacion()
            );

            vista.mostrarMensaje(
                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-"
            );

            numero++;
        }

        vista.mostrarMensaje(
            "\nTotal de vehiculos: " + vehiculos.size()
        );

        vista.mostrarMensaje(
            "============================================================\n"
        );
    }

    public void consultarClientes() {
        if (clientes.isEmpty()) {
            vista.mostrarMensaje("No hay clientes registrados.");
            return;
        }

        vista.mostrarMensaje(
            "\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
            "              CLIENTES DE RENTAMOVIL\n" +
            "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n"
        );

        int numero = 1;

        for (Cliente cliente : clientes) {
            vista.mostrarMensaje(
                "[" + numero + "] " + cliente.informacion()
            );

            vista.mostrarMensaje("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
            numero++;
        }

        vista.mostrarMensaje("Total de clientes: " + clientes.size());

        vista.mostrarMensaje("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
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

    public void cotizarAlquiler() {

        Vehiculo vehiculo = null;
        Cliente cliente = null;
        int dias = 0;

        while (vehiculo == null) {
            try {
                String placa = vista.leerString("Ingrese la placa del vehiculo: ");
                vehiculo = buscarVehiculo(placa);

                if (vehiculo == null) {
                    throw new IllegalArgumentException(
                            "El vehiculo no existe -_-"
                    );
                }

            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }

        while (cliente == null) {
            try {
                String id = vista.leerString("Ingrese el ID del cliente: ");
                cliente = buscarCliente(id);

                if (cliente == null) {
                    throw new IllegalArgumentException("El cliente no existe -_-");
                }

            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }

        boolean diasValidos = false;

        while (!diasValidos) {
            try {
                dias = vista.leerEntero("Ingrese la cantidad de dias: ");

                if (dias <= 0) {
                    throw new IllegalArgumentException("Los dias deben ser mayores a 0 -_-");
                }

                diasValidos = true;

            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }

        double subtotal = vehiculo.calcularSubtotal(dias);

        int confirmados = contarAlquileresConfirmados(cliente);

        double descuento = cliente.calcularDescuento(subtotal, confirmados);

        double total = subtotal - descuento;

        vista.mostrarMensaje(vehiculo.informacion());

        vista.mostrarMensaje(String.format(
                "Subtotal: Q%.2f | Descuento: Q%.2f | Total: Q%.2f",
                subtotal,
                descuento,
                total
        ));

        boolean puedeAlquilar = true;

        if (!vehiculo.getEstado().equalsIgnoreCase("Disponible")) {
            vista.mostrarMensaje(
                    "No puede alquilarlo: el vehiculo no esta disponible :("
            );
            puedeAlquilar = false;
        }

        if (!licenciaValida(cliente, vehiculo)) {
            vista.mostrarMensaje(
                    "No puede alquilarlo: licencia inadecuada :/"
            );
            puedeAlquilar = false;
        }

        if (contarAlquileresActivos(cliente) >= cliente.limiteAlquileres()) {
            vista.mostrarMensaje(
                    "No puede alquilarlo: alcanzo el limite de alquileres activos :/"
            );
            puedeAlquilar = false;
        }

        if (puedeAlquilar) {
            vista.mostrarMensaje(
                    "El cliente puede alquilar este vehiculo :D"
            );
        }
    }

    public void confirmarAlquiler() {
        Vehiculo vehiculo = null;
        Cliente cliente = null;
        int dias = 0;

        while (vehiculo == null) {
            try {
                String placa = vista.leerString("Placa: ");
                vehiculo = buscarVehiculo(placa);
                if (vehiculo == null) throw new IllegalArgumentException("El vehiculo no existe.");
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }

        while (cliente == null) {
            try {
                String id = vista.leerString("Identificacion del cliente: ");
                cliente = buscarCliente(id);
                if (cliente == null) throw new IllegalArgumentException("El cliente no existe.");
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
        }

        boolean diasValidos = false;
        while (!diasValidos) {
            try {
                dias = vista.leerEntero("Dias a alquilar: ");
                if (dias <= 0) throw new IllegalArgumentException("Los dias deben ser mayores a 0.");
                diasValidos = true;
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje(e.getMessage());
            }
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
            switch (vehiculo.categoria()) {
                case "Automovil":
                    autos++;
                    break;

                case "Motocicleta":
                    motos++;
                    break;

                case "Camioneta de carga":
                camionetas++;
                break;

                case "Microbus":
                microbuses++;
                break;
            }


            if (vehiculo.getEstado().equalsIgnoreCase("Disponible")) disponibles++;
            else if (vehiculo.getEstado().equalsIgnoreCase("Alquilado")) alquilados++;
            else if (vehiculo.getEstado().equalsIgnoreCase("En mantenimiento")) mantenimiento++;
        }

        vista.mostrarMensaje(String.format("Automoviles: %d | Motocicletas: %d | Camionetas: %d | Microbuses: %d", autos, motos, camionetas, microbuses));
        vista.mostrarMensaje(String.format("Disponibles: %d | Alquilados: %d | En mantenimiento: %d", disponibles, alquilados, mantenimiento));
    }

    public void reporteIngresos() {
        double automoviles = 0;
        double motocicletas = 0;
        double camionetas = 0;
        double microbuses = 0;

        for (Alquiler alquiler : alquileres) {
            String categoria = alquiler.getVehiculo().categoria();

            switch (categoria) {
                case "Automovil":
                    automoviles += alquiler.getTotal();
                    break;

                case "Motocicleta":
                    motocicletas += alquiler.getTotal();
                    break;

                case "Camioneta de carga":
                    camionetas += alquiler.getTotal();
                    break;

                case "Microbus":
                    microbuses += alquiler.getTotal();
                    break;
            }
        }

        vista.mostrarMensaje(String.format("""
                
                +-+-+-+-+-+ REPORTE DE INGRESOS +-+-+-+-+-+
                Automoviles:          Q%.2f
                Motocicletas:         Q%.2f
                Camionetas de carga:  Q%.2f
                Microbuses:           Q%.2f
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                Total de ingresos:    Q%.2f
                +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
                """,
                automoviles,
                motocicletas,
                camionetas,
                microbuses,
                ingresosTotal));
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