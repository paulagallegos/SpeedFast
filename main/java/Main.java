import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * Ejecuta la entrega secuencial de todos los pedidos asignados a este repartidor. Por cada pedido, informa en consola el inicio y término
     * de la entrega, simulando el tiempo de viaje con una pausa aleatoria.
     */


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

        List<Pedido> pedidosJuan=new ArrayList<>();
        pedidosJuan.add(pedidoComida);
        pedidosJuan.add(pedidoComida2);
        pedidosJuan.add(pedidoComida3);

        List<Pedido> pedidosCamila= new ArrayList<>();
        pedidosCamila.add(pedidoEncomienda);
        pedidosCamila.add(pedidoEncomienda2);

        List<Pedido> pedidosLuis=new ArrayList<>();
        pedidosLuis.add(pedidoExpress);
        pedidosLuis.add(pedidoExpress2);

        /**
         * Incializacion de las instancias de Repartidor
         */
        Repartidor repartidorJuan= new Repartidor("Juan Pérez",pedidosJuan);
        Repartidor repartidorCamila=new Repartidor("Camila Soto",pedidosCamila);
        Repartidor repartidorLuis= new Repartidor("Luis Díaz", pedidosLuis);

        /**
         * Administrador para manejar los hilos disponibles
         */
        ExecutorService ejecutor= Executors.newFixedThreadPool(3);

        ejecutor.execute(repartidorJuan);
        ejecutor.execute(repartidorCamila);
        ejecutor.execute(repartidorLuis);


        /**
         * Espera y cierre del proceso de reparto
         */
        ejecutor.shutdown();                          // 1. no acepto tareas nuevas
        try {
            ejecutor.awaitTermination(1, TimeUnit.MINUTES); // 2. espero a que las actuales terminen
        } catch (InterruptedException e) {
            System.out.println("La espera fue interrumpida");
        }
        System.out.println();
        System.out.println("Todas las entregas finalizaron");
    }
}