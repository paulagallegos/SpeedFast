package model;

/**
 * Compra Express (supermercado o farmacia). Debe asignarse al
 * repartidor más cercano con disponibilidad inmediata.
 */
public class PedidoExpress extends Pedido {

    private boolean repartidorCercanoDisponible;

    public PedidoExpress(String idPedido, String direccionEntrega, boolean repartidorCercanoDisponible) {
        super(idPedido, direccionEntrega, "Express");
        this.repartidorCercanoDisponible = repartidorCercanoDisponible;
    }

    public boolean isRepartidorCercanoDisponible() {
        return repartidorCercanoDisponible;
    }

    /**
     * Sobreescritura: busca al repartidor más cercano con
     * disponibilidad inmediata.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express | " + idPedido + "]");
        System.out.println("Asignando repartidor...");
        if (repartidorCercanoDisponible) {
            System.out.println("-> Repartidor más cercano con disponibilidad inmediata encontrado.");
        } else {
            System.out.println("-> No se encontró repartidor cercano con disponibilidad inmediata.");
        }
    }

    /**
     * Sobrecarga: además de la validación propia del tipo de pedido,
     * confirma (o rechaza) la asignación al repartidor indicado.
     */
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
