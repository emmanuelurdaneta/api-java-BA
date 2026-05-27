package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " - $" + String.format("%.2f", subtotal);
    }
}
