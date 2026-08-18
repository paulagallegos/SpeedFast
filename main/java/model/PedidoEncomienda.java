package model;

public class PedidoEncomienda extends Pedido {

    private static final double PESO_MAXIMO_KG = 20.0;

    private double pesoKg;
    private boolean embalajeValido;

    public PedidoEncomienda(String idPedido, String direccionEntrega, double pesoKg, boolean embalajeValido) {
        super(idPedido, direccionEntrega, "Encomienda");
        this.pesoKg = pesoKg;
        this.embalajeValido = embalajeValido;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public boolean isEmbalajeValido() {
        return embalajeValido;
    }

    private boolean cumplePesoMaximo() {
        return pesoKg <= PESO_MAXIMO_KG;
    }

    /**
     * Sobreescritura: valida peso y embalaje antes de continuar con
     * la asignación.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda | " + idPedido + "]");
        System.out.println("Asignando repartidor...");
        if (cumplePesoMaximo() && embalajeValido) {
            System.out.println("-> Validando peso y embalaje... OK");
        } else {
            System.out.println("-> Validando peso y embalaje... ERROR (peso: " + pesoKg + " kg, embalaje válido: "
                    + embalajeValido + ")");
        }
    }

    /**
     * Sobrecarga: además de la validación propia del tipo de pedido,
     * confirma (o rechaza) la asignación al repartidor indicado.
     */
    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        asignarRepartidor();
        if (cumplePesoMaximo() && embalajeValido) {
            System.out.println("-> Pedido asignado a " + nombreRepartidor);
        } else {
            System.out.println("-> No se pudo asignar a " + nombreRepartidor + ": peso o embalaje no cumplen los requisitos");
        }
    }
}
