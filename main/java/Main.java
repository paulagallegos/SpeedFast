import model.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** Crea los pedidos y su asignación inicial,
 * y luego simula el reparto concurrente cargando los pedidos en la
 *  ZonaDeCarga compartida
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("Sistema de Asignación de Repartidores - SpeedFast");
        System.out.println("-------------------------------------------------");

        /**
         * Incializacion de las instancias de pedido
         */
        PedidoComida pedidoComida = new PedidoComida("PC-001", "Av. Providencia 1234, Providencia", 10.5, true);
        PedidoComida pedidoComida2 = new PedidoComida("PC-002", "Av. Salvador 334, Providencia", 12, true);
        PedidoComida pedidoComida3 = new PedidoComida("PC-003", "Av. Apoquindo 124, Las Condes", 15, true);
        PedidoEncomienda pedidoEncomienda = new PedidoEncomienda("PE-002", "Av. Apoquindo 5670, Las Condes", 21.4, 2.5, true);
        PedidoEncomienda pedidoEncomienda2 = new PedidoEncomienda("PE-003", "Av. Simon Bolivar, Ñuñoa", 21.4, 2.5, true);
        PedidoExpress pedidoExpress = new PedidoExpress("PX-003", "Calle Merced 890, Santiago Centro", 5.0, true);
        PedidoExpress pedidoExpress2 = new PedidoExpress("PX-004", "Vecinal 4675, San Joaquín", 6.0, true);



        Pedido[] pedidos = {pedidoComida, pedidoEncomienda, pedidoExpress};
        String[] repartidores = {"Juan Pérez", "Camila Soto", "Luis Díaz"};

        for (int i = 0; i < pedidos.length; i++) {
            try {
                pedidos[i].mostrarResumen();
                System.out.println("Tiempo estimado: " + pedidos[i].calcularTiempoEntrega() + " min");
                pedidos[i].asignarRepartidor(repartidores[i]); // se asume cantidad de repartidores igual a la cantidad de pedidos
                System.out.println();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No hay repartidor disponible para el pedido en la posición " + i);
            }
        }

        System.out.println("--Asignación automática--");
        pedidoComida.asignarRepartidor();
        System.out.println();

        System.out.println("--Resumen tiempos de pedidos--");
        System.out.printf("%-8s %-12s %10s%n", "  ID", " Tipo", "    Tiempo(min)");
        for (Pedido pedido : pedidos) {
            System.out.printf("%-8s %-12s %10d%n",
                    pedido.getIdPedido(), pedido.getTipoPedido(), pedido.calcularTiempoEntrega());
        }
        System.out.println();

        ControladorEnvio controlador = new ControladorEnvio();

        System.out.println("--Despacho de pedido--");
        boolean despachado = pedidoComida.despachar("En camino");
        System.out.println("¿Se despachó? " + despachado);
        if (despachado){
            controlador.registrarEntrega(pedidoComida);
        }
        System.out.println();

        System.out.println("--Cancelación de pedido--");
        boolean cancelado = pedidoExpress.cancelar("Cliente cambió de opinión");
        System.out.println("¿Se canceló? " + cancelado);//no se registra en el historial de despachos
        System.out.println();


        System.out.println("--Historial--");
        System.out.printf("%-8s %-12s%n", "  ID", " Tipo");
        for (Pedido pedido : controlador.verHistorial()) {
            System.out.printf("%-8s %-12s%n", pedido.getIdPedido(), pedido.getTipoPedido());
        }

        System.out.println();
        System.out.println("Entregas de pedidos");
        System.out.println();


        ZonaDeCarga zonaDeCarga= new ZonaDeCarga();
        zonaDeCarga.agregarPedido(pedidoComida);
        zonaDeCarga.agregarPedido(pedidoComida2);
        zonaDeCarga.agregarPedido(pedidoComida3);
        zonaDeCarga.agregarPedido(pedidoEncomienda);
        zonaDeCarga.agregarPedido(pedidoEncomienda2);
        zonaDeCarga.agregarPedido(pedidoExpress);
        zonaDeCarga.agregarPedido(pedidoExpress2);


        /**
         * Incializacion de las instancias de Repartidor
         */
        Repartidor repartidor= new Repartidor ("Juan Pérez",zonaDeCarga);
        Repartidor repartidor2= new Repartidor("Camila Soto",zonaDeCarga );
        Repartidor repartidor3 =new Repartidor("Luis Díaz",zonaDeCarga );

        /**
         * Administrador para manejar los hilos disponibles
         */
        ExecutorService ejecutor= Executors.newFixedThreadPool(3);

        ejecutor.execute(repartidor);
        ejecutor.execute(repartidor2);
        ejecutor.execute(repartidor3);


        /**
         * Espera y cierre del proceso de reparto
         */
        ejecutor.shutdown();                          // para no aceptar tareas nuevas
        try {
            ejecutor.awaitTermination(1, TimeUnit.MINUTES); //Esperar a que las actuales terminen
        } catch (InterruptedException e) {
            System.out.println("La espera fue interrumpida");
        }
        System.out.println();
        System.out.println("===================================================");
        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}