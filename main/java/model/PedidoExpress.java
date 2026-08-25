package model;


public class PedidoExpress extends Pedido {
    private static final double umbral_distancia_km = 5.0;
    private static final int tiempo_base_min = 10;
    private static final int tiempo_extra_min = 5;
    private boolean repartidorCercanoDisponible;

    public PedidoExpress(String idPedido, String direccionEntrega, double distanciakm, boolean repartidorCercanoDisponible) {
        super(idPedido, direccionEntrega, distanciakm,"Express");
        this.repartidorCercanoDisponible = repartidorCercanoDisponible;
    }

    public boolean isRepartidorCercanoDisponible() {
        return repartidorCercanoDisponible;
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = tiempo_base_min;
        if (distanciaKm > umbral_distancia_km) {
            tiempo += tiempo_extra_min;
        }
        return tiempo;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor...");
        if (repartidorCercanoDisponible) {
            System.out.println("-> Repartidor más cercano con disponibilidad inmediata encontrado.");
        } else {
            System.out.println("-> No se encontró repartidor cercano con disponibilidad inmediata.");
        }
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        if (repartidorCercanoDisponible) {
            System.out.println("-> Pedido asignado a " + nombreRepartidor);
        } else {
            System.out.println("-> No se pudo asignar a " + nombreRepartidor + ": sin repartidores cercanos disponibles");
        }
    }
}
