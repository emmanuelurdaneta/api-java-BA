package com.techlab.servicios;

import com.techlab.pedidos.Pedido;
import com.techlab.pedidos.LineaPedido;
import com.techlab.productos.Producto;
import com.techlab.excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private List<Pedido> pedidos;
    private ProductoService productoService;
    private Scanner scanner;

    public PedidoService(ProductoService productoService) {
        this.pedidos = new ArrayList<>();
        this.productoService = productoService;
        this.scanner = new Scanner(System.in);
    }

    public void crearPedido() {
        System.out.println("\n=== CREAR PEDIDO ===");
        
        List<Producto> productos = productoService.getProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles. Primero agregue productos.");
            return;
        }
        
        productoService.listarProductos();
        
        Pedido pedido = new Pedido();
        boolean continuar = true;
        
        while (continuar) {
            System.out.print("\nIngrese el ID del producto (0 para finalizar): ");
            
            try {
                int idProducto = Integer.parseInt(scanner.nextLine());
                
                if (idProducto == 0) {
                    continuar = false;
                    break;
                }
                
                Producto producto = productoService.buscarProductoPorId(idProducto);
                
                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                    continue;
                }
                
                System.out.print("Cantidad deseada: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                    continue;
                }
                
                if (cantidad > producto.getStock()) {
                    throw new StockInsuficienteException(
                        "Stock insuficiente. Disponible: " + producto.getStock() + ", Solicitado: " + cantidad
                    );
                }
                
                LineaPedido linea = new LineaPedido(producto, cantidad);
                pedido.agregarLinea(linea);
                producto.reducirStock(cantidad);
                
                System.out.println("Producto agregado al pedido: " + linea);
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        if (!pedido.getLineas().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("\nPedido creado exitosamente:");
            pedido.mostrarDetalle();
        } else {
            System.out.println("\nEl pedido no tiene productos. No se creó el pedido.");
        }
    }

    public void listarPedidos() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido pedido : pedidos) {
                pedido.mostrarDetalle();
            }
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
