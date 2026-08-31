package model;

import java.util.ArrayList;
import java.util.List;

public class ControladorEnvio implements Rastreable {
    private List<Pedido> historial = new ArrayList<>();

    public void registrarEntrega(Pedido pedido) {
        historial.add(pedido);
    }

    @Override
    public List<Pedido> verHistorial() {
        return historial;
    }


}
