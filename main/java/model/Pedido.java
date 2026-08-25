package model;

public abstract class Pedido {
    protected String idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;
    protected double distanciaKm;

    public Pedido(String idPedido, String direccionEntrega,double distanciaKm, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm=distanciaKm;
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

    public void mostrarResumen(){
        System.out.println("Pedido " + tipoPedido + " | " + idPedido);
        System.out.println("Dirección de entrega: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    public abstract int calcularTiempoEntrega();

    public void asignarRepartidor() {
        System.out.println("Pedido " + tipoPedido + " | " + idPedido);
        System.out.println("Asignando repartidor...");
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
