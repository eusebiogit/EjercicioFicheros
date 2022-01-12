/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioficheros;

import java.util.Scanner;

/**
 *
 * @author ordenador
 */
public class EjercicioFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        int filas;
        int menu;
        int billetesComprar;
        double precio;
        System.out.println("**Bienvenido**");
        System.out.println("Introduzca filas del avion ");
        filas = sc.nextInt();
        System.out.println("Introduzca precio del billete ");
        precio = sc.nextDouble();
        boolean asientos[][] = new boolean[filas][4];
        int asientosTotales = filas * 4;
        iniciarAsientos(asientos);
        do {
            System.out.println("0. Salir\n1. Venta de billetes\n2. Consultas de asientos libres\n 3. Anulación de billetes.");
            menu = sc.nextInt();
            switch (menu) {
                case 0: {
                    salir = true;
                    break;
                }
                case 1: {
                    int fila;
                    int columna;
                    double coste = 0;
                    boolean descuento = false;
                    do {
                        System.out.println("Indique cuantos billetes desea comprar: valor ne [1,10]");
                        billetesComprar = sc.nextInt();
                        if (billetesComprar > 10) {
                            System.out.println("No puedes comprar más de 10");
                        }
                    } while (billetesComprar > 10);
                    mostrarAsientos(asientos);
                    boolean asientosComprados[][] = new boolean[filas][4];
                    iniciarAsientos(asientosComprados);
                    for (int i = 0; i < billetesComprar; i++) {
                        System.out.println("Indique la fila del asiento" + i + " a comprar (Valor en rango) [0, 4]");
                        fila = sc.nextInt();
                        System.out.println("Indique la columna del asiento" + i + " a comprar [0, " + filas + "]");
                        columna = sc.nextInt();
                        coste = coste + precio;
                        if (asientosComprados[fila][columna]) {
                            asientosComprados[fila][columna] = false;
                            asientos[fila][columna] = false;
                        } else {
                            System.out.println("Ese asiento no está libre");
                            mostrarAsientos(asientos);
                        }
                    }
                    if (aplicarDescuento(asientosComprados)) {
                        coste = coste * 0.95;
                        descuento = true;
                    }
                    System.out.println("Precio final: " + coste + (descuento ? "Se ha aplicado descuento" : "No se ha aplicado descuento"));
                    break;
                }
                case 2: {
                    int fila;
                    int columna;
                    System.out.println("Consultar asiento libre");
                    System.out.println("Introduzca fila del asiento");
                    fila = sc.nextInt();
                    System.out.println("Introduzca columna del asiento");
                    columna = sc.nextInt();
                    if (asientos[fila][columna]) {
                        System.out.println("El asiento está libre");
                    } else {
                        System.out.println("El asiento está ocupado");
                    }
                    break;
                }
                case 3: {
     
                    
                    int fila;
                    int columna;
                    System.out.println("Anular billete");
                    System.out.println("Introduzca fila del asiento");
                    fila = sc.nextInt();
                    System.out.println("Introduzca columna del asiento");
                    columna = sc.nextInt();
                    if (!asientos[fila][columna]) {
                        asientos[fila][columna] = true;
                    }
                }
            }
        } while (!salir);
    }

    private static boolean aplicarDescuento(boolean[][] asientosComprados) {
        boolean aplicarDescuento = false;
        for (int i = 0; i < asientosComprados.length && !aplicarDescuento; i++) {
            aplicarDescuento = true;
            for (int j = 0; j < asientosComprados[0].length && aplicarDescuento; j++) {
                aplicarDescuento = aplicarDescuento && !asientosComprados[i][j];
            }
        }
        return aplicarDescuento;
    }

    private static void mostrarAsientos(boolean[][] asientos) {
        for (int i = 0; i < asientos.length; i++) {
            System.out.print("Fila " + i + ":");
            for (int j = 0; j < asientos[0].length; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void iniciarAsientos(boolean[][] asientos) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                asientos[i][j] = true;
            }
        }
    }

}
