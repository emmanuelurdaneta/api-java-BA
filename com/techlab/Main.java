package com.techlab;

import com.techlab.servicios.ProductoService;
import com.techlab.servicios.PedidoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService(productoService);
        
        boolean ejecutando = true;
        
        while (ejecutando) {
            mostrarMenu();
            
            try {
                System.out.print("Elija una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        productoService.agregarProducto();
                        break;
                    case 2:
                        productoService.listarProductos();
                        break;
                    case 3:
                        productoService.buscarActualizarProducto();
                        break;
                    case 4:
                        productoService.eliminarProducto();
                        break;
                    case 5:
                        pedidoService.crearPedido();
                        break;
                    case 6:
                        pedidoService.listarPedidos();
                        break;
                    case 7:
                        System.out.println("\n=== Saliendo del sistema ===");
                        System.out.println("¡Gracias por usar el Sistema de Gestión - TECHLAB!");
                        ejecutando = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 7.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
            
            if (ejecutando) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================");
        System.out.println();
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar/Actualizar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("5) Crear un pedido");
        System.out.println("6) Listar pedidos");
        System.out.println("7) Salir");
        System.out.println();
        System.out.println("====================================================================================================");
    }
}
