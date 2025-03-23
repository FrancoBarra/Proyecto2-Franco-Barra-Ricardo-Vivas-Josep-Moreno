/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author frank
 */
public class ArbolBinario {

    private NodoArbol raiz;
    private String[] datos;
    private int contadorIndice;

    public ArbolBinario() {
        this.raiz = null;
        this.datos = new String[100]; // Tamaño inicial, puedes ajustarlo
        this.contadorIndice = 0;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

     public void add(NodoArbol nuevo, NodoArbol root) {
       
        if (isEmpty()) {
            this.raiz = nuevo;
        } else {

            if (nuevo.getData() < root.getData()) {
                if (root.getIzHijo() == null) {
                    root.setIzHijo(nuevo);

                } else {
                    add(nuevo, root.getIzHijo());
                }

            } 
                else {
                if (root.getDeHijo() == null) {
                    root.setDeHijo(nuevo);


                } else {
                    add(nuevo, root.getDeHijo());
                }

            }
        }

    }

//    private void insertarNodo(NodoArbol nuevoNodo, NodoArbol nodoActual) {
//        if (nodoActual.getIzHijo() == null) {
//            nodoActual.setIzHijo(nuevoNodo);
//        } else if (nodoActual.getDeHijo() == null) {
//            nodoActual.setDeHijo(nuevoNodo);
//        } else {
//            // Lógica adicional si ambos hijos están ocupados
//            // Por ejemplo, insertar en el subárbol con menos nodos
//            if (contarNodos(nodoActual.getIzHijo()) <= contarNodos(nodoActual.getDeHijo())) {
//                insertarNodo(nuevoNodo, nodoActual.getIzHijo());
//            } else {
//                insertarNodo(nuevoNodo, nodoActual.getDeHijo());
//            }
//        }
//    }

//    private int contarNodos(NodoArbol nodo) {
//        if (nodo == null) {
//            return 0;
//        }
//        return 1 + contarNodos(nodo.getIzHijo()) + contarNodos(nodo.getDeHijo());
//    }
//    
//    public NodoArbol buscarInorder(String valor) {
//        return buscarInorderRecursivo(raiz, valor);
//    }
//
//    private NodoArbol buscarInorderRecursivo(NodoArbol nodo, String valor) {
//        if (nodo == null) {
//            return null;
//        }
//
//        NodoArbol nodoIzquierdo = buscarInorderRecursivo(nodo.getIzHijo(), valor);
//        if (nodoIzquierdo != null) {
//            return nodoIzquierdo;
//        }
//
//        if (nodo.getData().equals(valor)) {
//            return nodo;
//        }
//
//        return buscarInorderRecursivo(nodo.getDeHijo(), valor);
//    }
//
//    public String buscarPorIndice(int indice) {
//        if (indice >= 0 && indice < datos.length) {
//            return datos[indice];
//        }
//        return null;
//    }

    public NodoArbol buscarInorder(int i, NodoArbol nodo) {
    if (nodo != null) {
        NodoArbol resultadoIzquierdo = buscarInorder(i, nodo.getIzHijo());
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo;
        }


        if (nodo.getData() == i) {
            return nodo; 
        }

        return buscarInorder(i, nodo.getDeHijo());
    }
    return null; 
}

    public String buscarPlanta(boolean respuesta) {
        NodoArbol nodoActual = raiz;
        while (nodoActual.getIzHijo() != null && nodoActual.getDeHijo() != null) {
            if (respuesta) {
                nodoActual = nodoActual.getIzHijo();
            } else {
                nodoActual = nodoActual.getDeHijo();
            }
        }
        return nodoActual.getInfo();
    }
}