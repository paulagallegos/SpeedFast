package model;

/**
 * Clase base que representa un pedido genérico dentro del sistema de
 * SpeedFast.
 */

public class Pedido {
    protected String idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;

    public Pedido(String idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void asignarRepartidor() {
        System.out.println("[Pedido " + tipoPedido + " | " + idPedido + "]");
        System.out.println("Asignando repartidor...");
        System.out.println("-> Lógica genérica: no hay validaciones especiales para este tipo de pedido.");
    }


    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("-> Pedido asignado a " + nombreRepartidor);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", tipoPedido='" + tipoPedido + '\'' +
                '}';
    }
}
