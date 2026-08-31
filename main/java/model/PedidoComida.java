package model;

public class PedidoComida extends Pedido {

    private boolean tieneMochilaTermica;

    public PedidoComida(String idPedido, String direccionEntrega, double distanciakm,boolean tieneMochilaTermica) {
        super(idPedido, direccionEntrega, distanciakm,"Comida");
        this.tieneMochilaTermica = tieneMochilaTermica;
    }

    public boolean isTieneMochilaTermica() {
        return tieneMochilaTermica;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(15 + 2 * distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor...");
        if (tieneMochilaTermica) {
            System.out.println("-> Verificando mochila térmica... OK");
        } else {
            System.out.println("-> Verificando mochila térmica... PENDIENTE (no se puede asignar sin mochila térmica)");
        }
    }


    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        if (tieneMochilaTermica) {
            System.out.println("-> Pedido asignado a " + nombreRepartidor);
        } else {
            System.out.println("-> No se pudo asignar a " + nombreRepartidor + ": repartidor sin mochila térmica");
        }
    }

    @Override
    public boolean despachar(String estadoEnvio) {
        System.out.println("[Pedido " + tipoPedido + " | " + idPedido + "] Despachando... Estado: " + estadoEnvio);
        return true;
    }

    @Override
    public boolean cancelar(String motivoCancelacion) {
        System.out.println("[Pedido " + tipoPedido + " | " + idPedido + "] Cancelando... Motivo: " + motivoCancelacion);
        return true;
    }
}
