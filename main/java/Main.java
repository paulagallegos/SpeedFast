
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;


public class Main {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Asignación de Repartidores - SpeedFast ===");
        System.out.println();

        // --- Demostración del método genérico definido en la clase base ---
        Pedido pedidoGenerico = new Pedido("P-000", "Dirección no especificada", "Genérico");
        System.out.println(">> Método genérico heredado desde la clase base Pedido:");
        pedidoGenerico.asignarRepartidor();
        System.out.println();

        // --- Instanciación de al menos un objeto de cada subclase ---
        PedidoComida pedidoComida = new PedidoComida("PC-001", "Av. Providencia 1234, Providencia", true);
        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("PE-002", "Av. Apoquindo 5670, Las Condes", 2.5, true);
        PedidoExpress pedidoExpress = new PedidoExpress("PX-003", "Calle Merced 890, Santiago Centro", true);

        // Se guardan en un arreglo de tipo Pedido para demostrar polimorfismo
        Pedido[] pedidos = { pedidoComida, pedidoEncomienda, pedidoExpress };
        String[] repartidores = { "Juan Pérez", "Camila Soto", "Luis Díaz" };

        // --- Demostración de la versión SOBRESCRITA (sin parámetros) ---
        System.out.println(">> Polimorfismo - método SOBRESCRITO asignarRepartidor():");
        System.out.println("--------------------------------------------------------");
        for (Pedido p : pedidos) {
            p.asignarRepartidor();
            System.out.println();
        }

        // --- Demostración de la versión SOBRECARGADA (con nombre de repartidor) ---
        System.out.println(">> Polimorfismo - método SOBRECARGADO asignarRepartidor(String):");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < pedidos.length; i++) {
            pedidos[i].asignarRepartidor(repartidores[i]);
            System.out.println();
        }

        // --- Caso adicional: pedido de comida sin mochila térmica disponible ---
        System.out.println(">> Caso adicional - Pedido de comida sin mochila térmica:");
        System.out.println("--------------------------------------------------------");
        PedidoComida pedidoComidaSinMochila = new PedidoComida("PC-004", "Calle Huérfanos 500, Santiago Centro", false);
        pedidoComidaSinMochila.asignarRepartidor("Pedro Ramírez");
    }
}

