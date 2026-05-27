package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private List<LineaPedido> lineas;
    private double total;

    public Pedido() {
        this.id = contadorId++;
        this.lineas = new ArrayList<>();
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public double getTotal() {
        return total;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
    }

    public void mostrarDetalle() {
        System.out.println("=== PEDIDO #" + id + " ===");
        System.out.println("Productos:");
        for (LineaPedido linea : lineas) {
            System.out.println("  - " + linea.toString());
        }
        System.out.println("TOTAL: $" + String.format("%.2f", total));
        System.out.println("====================");
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Total: $" + String.format("%.2f", total) + " - Items: " + lineas.size();
    }
}
