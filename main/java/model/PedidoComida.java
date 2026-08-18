package model;

public class PedidoComida extends Pedido {

    private boolean tieneMochilaTermica;

    public PedidoComida(String idPedido, String direccionEntrega, boolean tieneMochilaTermica) {
        super(idPedido, direccionEntrega, "Comida");
        this.tieneMochilaTermica = tieneMochilaTermica;
    }

    public boolean isTieneMochilaTermica() {
        return tieneMochilaTermica;
    }

    /**
     * Sobreescritura: valida que el repartidor cuente con mochila
     * térmica antes de continuar con la asignación.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Comida | " + idPedido + "]");
        System.out.println("Asignando repartidor...");
        if (tieneMochilaTermica) {
            System.out.println("-> Verificando mochila térmica... OK");
        } else {
            System.out.println("-> Verificando mochila térmica... PENDIENTE (no se puede asignar sin mochila térmica)");
        }
    }

    /**
     * Sobrecarga: además de la validación propia del tipo de pedido,
     * confirma (o rechaza) la asignación al repartidor indicado.
     */
    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        if (tieneMochilaTermica) {
            System.out.println("-> Pedido asignado a " + nombreRepartidor);
        } else {
            System.out.println("-> No se pudo asignar a " + nombreRepartidor + ": repartidor sin mochila térmica");
        }
    }
}
