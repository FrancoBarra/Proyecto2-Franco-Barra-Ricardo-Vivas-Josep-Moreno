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

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean isEmthy() {
        return raiz == null;
    }

    public void add(int data, NodoArbol root) {
        NodoArbol nuevo = new NodoArbol(data);
        if (isEmthy()) {
            this.raiz = nuevo;
        } else {

            if (data < root.getData()) {
                if (root.getIzHijo() == null) {
                    root.setIzHijo(nuevo);
                } else {
                    add(data, root.getIzHijo());
                }

            } else {
                if (root.getDeHijo() == null) {
                    root.setDeHijo(nuevo);
                } else {
                    add(data, root.getDeHijo());
                }

            }
        }
    }

    public NodoArbol delete(int data, NodoArbol root) {

        if (!isEmthy()) {
            if (root.isTieneHijo()) {
                if (data < root.getData()) {
                    root.setIzHijo(delete(data, root.getIzHijo()));
                } else if (data > root.getData()) {
                    root.setIzHijo(delete(data, root.getDeHijo()));
                } else {
                    NodoArbol aux = this.minimo(root.getDeHijo());
                    if (aux != null) {
                        root.setData(aux.getData());
                        delete(aux.getData(), root.getDeHijo());
                    }
                    return aux;
                }
                return root;
            }

        }
        return null;
    }

    public NodoArbol minimo(NodoArbol actual) {
        if (actual == null || actual.getIzHijo() == null) {
            return actual;
        } else {
            return minimo(actual.getIzHijo());
        }
    }
}

