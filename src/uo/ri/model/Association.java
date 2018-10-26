package uo.ri.model;

public class Association {

    public static class Poseer {
        public static void link(Cliente cliente, Vehiculo vehiculo) {
            vehiculo._setCliente(cliente);
            cliente._getVehiculos().add(vehiculo);
        }

        public static void unlink(Cliente cliente, Vehiculo vehiculo) {
            cliente._getVehiculos().remove(vehiculo);
            vehiculo._setCliente(null);
        }
    }

    public static class Clasificar {
        public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo) {
            vehiculo._setTipo(tipoVehiculo);
            tipoVehiculo._getVehiculos().add(vehiculo);
        }

        public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo) {
            tipoVehiculo._getVehiculos().remove(vehiculo);
            vehiculo._setTipo(null);
        }
    }

    public static class Pagar {
    }

    public static class Averiar {
        public static void link(Vehiculo vehiculo, Averia averia) {
            averia._setVehiculo(vehiculo);
            vehiculo._getAverias().add(averia);
        }

        public static void unlink(Vehiculo vehiculo, Averia averia) {
            vehiculo._getAverias().remove(averia);
            averia._setVehiculo(null);
        }
    }

    public static class Facturar {
    }

    public static class Cargar {
    }

    public static class Asignar {
        public static void link(Mecanico mecanico, Averia averia) {
            mecanico._setAveria(averia);
            averia._setMecanico(mecanico);
        }

        public static void unlink(Mecanico mecanico, Averia averia) {
            averia._setMecanico(null);
            mecanico._setAveria(null);
        }
    }

    public static class Intervenir {
    }

    public static class Sustituir {
    }

}
