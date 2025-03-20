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

    public NodoArbol add(int data, NodoArbol root) {
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


            } 
                else {
                if (root.getDeHijo() == null) {
                    root.setDeHijo(nuevo);


                } else {
                    add(data, root.getDeHijo());
                }

            }
        }
   
        root.setAltura(this.max(root.getIzHijo().getAltura(), root.getDeHijo().getAltura()));

        int dif = this.diferencia(root);

        if (dif > 1 && data < root.getIzHijo().getData()) {
            return this.rotar_der(root);
        }

        if (dif < -1 && data > root.getDeHijo().getData()) {
            return this.rotar_izq(root);
        }

        if (dif > 1 && data > root.getIzHijo().getData()) {
            root.setIzHijo(this.rotar_izq(root.getIzHijo()));
            return this.rotar_der(root);
        }

        if (dif < -1 && data < root.getDeHijo().getData()) {
            root.setDeHijo(this.rotar_der(root.getDeHijo()));
            return this.rotar_izq(root);
        }
        
        return root;
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    public NodoArbol rotar_izq(NodoArbol nodo) {
        NodoArbol aux = nodo.getDeHijo();
        NodoArbol aux2 = nodo.getIzHijo();

        aux.setIzHijo(nodo);
        nodo.setDeHijo(aux2);

        nodo.setAltura(1 + max(nodo.getDeHijo().getData(), nodo.getIzHijo().getData()));
        aux.setAltura(1 + max(aux.getDeHijo().getData(), aux.getIzHijo().getData()));
        return aux;
    }

    public NodoArbol rotar_der(NodoArbol nodo) {
        NodoArbol aux = nodo.getIzHijo();
        NodoArbol aux2 = nodo.getDeHijo();

        aux.setDeHijo(nodo);
        nodo.setIzHijo(aux2);

        nodo.setAltura(1 + max(nodo.getDeHijo().getData(), nodo.getIzHijo().getData()));
        aux.setAltura(1 + max(aux.getDeHijo().getData(), aux.getIzHijo().getData()));

        return aux;
    }

    public int diferencia(NodoArbol root) {
        if (root != null) {
            return (root.getIzHijo().getAltura() - root.getDeHijo().getAltura());
        }
        return 0;
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

    public NodoArbol buscarPreorder(int i, NodoArbol nodo) {
    if (nodo != null) {
        if (nodo.getData() == i) {
            return nodo;
        }
        NodoArbol resultadoIzquierdo = buscarPreorder(i, nodo.getIzHijo());
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo; 
        }

        return buscarPreorder(i, nodo.getDeHijo());
    }
    return null;
}

     public NodoArbol buscarPostorder(int i, NodoArbol nodo) {
    if (nodo != null) {

        NodoArbol resultadoIzquierdo = buscarPostorder(i, nodo.getIzHijo());
        if (resultadoIzquierdo != null) {
            return resultadoIzquierdo; 
        }


        NodoArbol resultadoDerecho = buscarPostorder(i, nodo.getDeHijo());
        if (resultadoDerecho != null) {
            return resultadoDerecho; 
        }

       
        if (nodo.getData() == i) {
            return nodo;
        }
    }
    return null;
}
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
}

