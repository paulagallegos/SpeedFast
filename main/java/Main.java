
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;


public class Main {

    public static void main(String[] args) {

        System.out.println("Sistema de Asignación de Repartidores - SpeedFast");
        System.out.println("-------------------------------------------------");

        PedidoComida pedidoComida = new PedidoComida("PC-001", "Av. Providencia 1234, Providencia", 10.5,true);
        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("PE-002", "Av. Apoquindo 5670, Las Condes", 21.4,2.5, true);
        PedidoExpress pedidoExpress = new PedidoExpress("PX-003", "Calle Merced 890, Santiago Centro", 5.0,true);

        Pedido[] pedidos = {pedidoComida, pedidoEncomienda, pedidoExpress};
        String[] repartidores = {"Juan Pérez", "Camila Soto", "Luis Díaz"};

        for (int i = 0; i < pedidos.length; i++) {
            pedidos[i].mostrarResumen();
            System.out.println("Tiempo estimado: " + pedidos[i].calcularTiempoEntrega() + " min");
            pedidos[i].asignarRepartidor(repartidores[i]);
            System.out.println();
        }

        System.out.println("--Resumen tiempos de pedidos--");
        System.out.printf("%-8s %-12s %10s%n", "  ID", " Tipo", "    Tiempo(min)");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-8s %-12s %10d%n",
                    pedido.getIdPedido(), pedido.getTipoPedido(), pedido.calcularTiempoEntrega());
        }
    }
    }