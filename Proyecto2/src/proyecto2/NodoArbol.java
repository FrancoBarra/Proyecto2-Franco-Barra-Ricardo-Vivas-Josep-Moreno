/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author frank
 */
public class NodoArbol<T> {
    private int data;
    private NodoArbol izHijo;
    private NodoArbol deHijo;
    private int altura;
    
    public NodoArbol(int data){
        this.data=data;
        this.izHijo=null;
        this.deHijo=null;
        this.altura=1;
    }
    
    public int getData(){
        return data;
    }
    
    public void setData(int data){
        this.data = data;
    }

    public NodoArbol getIzHijo() {
        return izHijo;
    }

    public void setIzHijo(NodoArbol izHijo) {
        this.izHijo = izHijo;
    }

    public NodoArbol getDeHijo() {
        return deHijo;
    }

    public void setDeHijo(NodoArbol deHijo) {
        this.deHijo = deHijo;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
     public boolean isTieneHijo(){
        return this.deHijo!=null || this.izHijo!=null;
    }
   
}
