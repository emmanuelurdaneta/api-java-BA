package com.techlab.servicios;

import com.techlab.productos.Producto;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private List<Producto> productos;
    private Scanner scanner;

    public ProductoService() {
        this.productos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void agregarProducto() {
        System.out.println("\n=== AGREGAR PRODUCTO ===");
        System.out.print("Tipo de producto (1: Producto básico, 2: Bebida, 3: Comida): ");
        
        try {
            int tipo = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nombre del producto: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Stock: ");
            int stock = Integer.parseInt(scanner.nextLine());
            
            Producto producto;
            
            switch (tipo) {
                case 1:
                    producto = new Producto(nombre, precio, stock);
                    break;
                case 2:
                    System.out.print("Volumen en litros: ");
                    double volumen = Double.parseDouble(scanner.nextLine());
                    System.out.print("Fecha de vencimiento: ");
                    String fechaVencimiento = scanner.nextLine();
                    producto = new Bebida(nombre, precio, stock, volumen, fechaVencimiento);
                    break;
                case 3:
                    System.out.print("Tipo de comida: ");
                    String tipoComida = scanner.nextLine();
                    System.out.print("¿Requiere refrigeración? (true/false): ");
                    boolean refrig = Boolean.parseBoolean(scanner.nextLine());
                    producto = new Comida(nombre, precio, stock, tipoComida, refrig);
                    break;
                default:
                    System.out.println("Tipo no válido. Se creará producto básico.");
                    producto = new Producto(nombre, precio, stock);
            }
            
            productos.add(producto);
            System.out.println("Producto agregado exitosamente: " + producto);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para precio, stock o tipo.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarProductos() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public void buscarActualizarProducto() {
        System.out.println("\n=== BUSCAR/ACTUALIZAR PRODUCTO ===");
        System.out.print("Buscar por (1: ID, 2: Nombre): ");
        
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            Producto producto = null;
            
            if (opcion == 1) {
                System.out.print("Ingrese el ID del producto: ");
                int id = Integer.parseInt(scanner.nextLine());
                producto = buscarProductoPorId(id);
            } else if (opcion == 2) {
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = scanner.nextLine();
                producto = buscarProductoPorNombre(nombre);
            } else {
                System.out.println("Opción no válida.");
                return;
            }
            
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }
            
            System.out.println("Producto encontrado: " + producto);
            System.out.print("¿Desea actualizar este producto? (s/n): ");
            String respuesta = scanner.nextLine();
            
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Nuevo precio (actual: " + producto.getPrecio() + "): ");
                String precioStr = scanner.nextLine();
                if (!precioStr.isEmpty()) {
                    double nuevoPrecio = Double.parseDouble(precioStr);
                    producto.setPrecio(nuevoPrecio);
                }
                
                System.out.print("Nuevo stock (actual: " + producto.getStock() + "): ");
                String stockStr = scanner.nextLine();
                if (!stockStr.isEmpty()) {
                    int nuevoStock = Integer.parseInt(stockStr);
                    producto.setStock(nuevoStock);
                }
                
                System.out.println("Producto actualizado: " + producto);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarProducto() {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        listarProductos();
        
        if (productos.isEmpty()) {
            return;
        }
        
        System.out.print("Ingrese el ID del producto a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Producto producto = buscarProductoPorId(id);
            
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }
            
            System.out.println("Producto a eliminar: " + producto);
            System.out.print("¿Confirmar eliminación? (s/n): ");
            String respuesta = scanner.nextLine();
            
            if (respuesta.equalsIgnoreCase("s")) {
                productos.remove(producto);
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un ID válido.");
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
