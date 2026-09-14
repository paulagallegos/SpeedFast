package model;


import java.util.List;

/**
 * Representa un repartidor de SpeedFast que entrega sus pedidos asignados de forma secuencial en un hilo propio, permitiendo que varios repartidores
 * trabajen en paralelo dentro del sistema.
 */

    public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    /**
     * Crea un repartidor con su nombre y la lista de pedidos que debe entregar.
     *
     * @param nombre      nombre del repartidor
     * @param zonaDeCarga referencia al objeto ZonaDeCarga
     */

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        Pedido pedido = zonaDeCarga.retirarPedido();
        while (pedido != null) {
            System.out.println(nombre + " está retirando de la zona de carga el pedido #" + pedido.getIdPedido());

            try {
                Thread.sleep((long) (Math.random() * 1000));
                pedido.setEstadoPedido(EstadoPedido.EN_REPARTO);
                System.out.println("El pedido #"+pedido.getIdPedido()+" se encuentra en reparto");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(" No se pudo entregar el pedido");
            }

            pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
            System.out.println(nombre + " entregó correctamente el pedido #" + pedido.getIdPedido());

            pedido = zonaDeCarga.retirarPedido();
        }
        }
    }


