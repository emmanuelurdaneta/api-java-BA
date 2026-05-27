package com.techlab.productos;

public class Comida extends Producto {
    private String tipo;
    private boolean requiereRefrigeracion;

    public Comida(String nombre, double precio, int stock, String tipo, boolean requiereRefrigeracion) {
        super(nombre, precio, stock);
        this.tipo = tipo;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }

    public void setRequiereRefrigeracion(boolean requiereRefrigeracion) {
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: " + tipo + " | Refrigeración: " + (requiereRefrigeracion ? "Sí" : "No");
    }
}
