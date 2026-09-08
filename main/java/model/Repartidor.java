package model;


import java.util.List;

/**
 * Representa un repartidor de SpeedFast que entrega sus pedidos asignados de forma secuencial en un hilo propio, permitiendo que varios repartidores
 * trabajen en paralelo dentro del sistema.
 */

    public class Repartidor implements Runnable {
        private String nombre;
        private List<Pedido> pedidosAsignados;

    /**
     * Crea un repartidor con su nombre y la lista de pedidos que debe entregar.
     * @param nombre nombre del repartidor
     * @param pedidosAsignados lista de pedidos asignados a este repartidor
     */

        public Repartidor(String nombre, List<Pedido> pedidosAsignados) {
            this.nombre = nombre;
            this.pedidosAsignados = pedidosAsignados;
        }

        @Override
        public void run() {
            for (Pedido pedido:pedidosAsignados) {
                System.out.println(nombre + " está entregando el pedido #" + pedido.getIdPedido());
                try {
                    Thread.sleep((long) (Math.random() * 1000));

                } catch (InterruptedException e) {
                    System.out.println(" No se pudo entregar el pedido");
                }
                System.out.println(nombre + " entregó correctamente el pedido #" + pedido.getIdPedido());
            }

        }
    }
