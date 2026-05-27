package com.techlab.productos;

public class Bebida extends Producto {
    private double volumenLitros;
    private String fechaVencimiento;

    public Bebida(String nombre, double precio, int stock, double volumenLitros, String fechaVencimiento) {
        super(nombre, precio, stock);
        this.volumenLitros = volumenLitros;
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getVolumenLitros() {
        return volumenLitros;
    }

    public void setVolumenLitros(double volumenLitros) {
        this.volumenLitros = volumenLitros;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() + " | Volumen: " + volumenLitros + "L | Vencimiento: " + fechaVencimiento;
    }
}
