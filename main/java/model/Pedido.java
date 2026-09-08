package model;

/**
 * Representa un pedido genérico de SpeedFast
 */

public abstract class Pedido implements Cancelable,Despachable {
    protected String idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;
    protected double distanciaKm;

    /**
     *
     * @param idPedido identificacion de un pedido
     * @param direccionEntrega dirección del lugar de entrega de un pedido
     * @param distanciaKm distancia en km del lugar de entrega de un pedido
     * @param tipoPedido tipo de pedido realizado
     */
    public Pedido(String idPedido, String direccionEntrega,double distanciaKm, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm=distanciaKm;
    }

    /**
     *
     * @return el identificador único del pedido
     */
    public String getIdPedido() {
        return idPedido;
    }

    /**
     *
     * @return la direccion de entrega del pedido
     */
    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    /**
     *
     * @return el tipo de pedido
     */
    public String getTipoPedido() {
        return tipoPedido;
    }

    /**
     * Muestra en consola un resumen del pedido: tipo, identificador,
     * dirección de entrega y distancia.
     */
    public void mostrarResumen(){
        System.out.println("Pedido " + tipoPedido + " | " + idPedido);
        System.out.println("Dirección de entrega: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    /**
     * Calcula el tiempo estimado de entrega de este pedido. Cada subclase
     * define su propia fórmula según el tipo de pedido.
     *
     * @return tiempo estimado de entrega, en minutos
     */
    public abstract int calcularTiempoEntrega();

    /**
     * Asigna un repartidor a este pedido. El comportamiento por defecto
     * solo informa que se está asignando; las subclases pueden agregar
     * validaciones propias antes de la asignación.
     */
    public void asignarRepartidor() {
        System.out.println("Pedido " + tipoPedido + " | " + idPedido);
        System.out.println("Asignando repartidor...");
    }

    /**
     * Asigna un repartidor específico a este pedido.
     *
     * @param nombreRepartidor nombre del repartidor al que se intenta asignar el pedido
     */
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        System.out.println("-> Pedido asignado a " + nombreRepartidor);
    }

    /**
     * @return una representación en texto del pedido, con su id, dirección y tipo
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", tipoPedido='" + tipoPedido + '\'' +
                '}';
    }
}
