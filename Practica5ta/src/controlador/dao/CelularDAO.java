/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.listas.ListaEnlazada;
import java.io.IOException;
import modelo.Celular;

/**
 *
 * @author Andersson
 */
public class CelularDAO extends AdaptadorDAO<Celular> {

    private Celular celular;

    public CelularDAO() {
        super(Celular.class);
    }

    public Celular getCelular() {
        if (this.celular == null) {
            this.celular = new Celular();
        }
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public void guardar() {
        celular.setId(generateID());
        try {
            this.guardar(celular);
        } catch (IOException ex) {
        }
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(celular, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Celular> ordenarMarca(ListaEnlazada<Celular> lista,  Integer tipo) {
        try {
            Celular[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Celular key = matriz[i];
                int j = i - 1;
                switch(tipo){
                    case 0: 
                        
                }
                while (j >= 0 && (matriz[j].getMarca().compareToIgnoreCase(key.getMarca())) > 0) {
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Celular> ordenarID(ListaEnlazada<Celular> lista, Integer tipo) {
        try {
            Celular[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Celular key = matriz[i];
                int j = i - 1;
                switch (tipo) {
                    case 0:
                        while (j >= 0 && (matriz[j].getId() > key.getId())) {
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }

                        break;

                    case 1:
                        while (j >= 0 && (matriz[j].getId() < key.getId())) {
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }
                        break;

                }
            }
            lista.toList(matriz);

        } catch (Exception e) {
        }
        return lista;
    }
    
    public Celular buscarPorMarcas(String dato) throws VacioException, PosicionException{
        Celular resultado = null;
        ListaEnlazada<Celular> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Celular aux = lista.get(i);
            if (aux.getMarca().toLowerCase().startsWith(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }
    
     public Celular buscarPorMarca(String dato) throws VacioException, PosicionException{
        Celular resultado = null;
        ListaEnlazada<Celular> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Celular aux = lista.get(i);
            if (aux.getMarca().toLowerCase().startsWith(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }
}

