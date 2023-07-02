/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.ed.listas.ListaEnlazada;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.lista.exception.PosicionException;
import java.io.IOException;
import modelo.Celular;
import modelo.Marca;

/**
 *
 * @author Andersson
 */
public class MarcaDAO extends AdaptadorDAO<Marca> {

    private Marca marca;

    public MarcaDAO() {
        super(Marca.class);
    }

    public Marca getMarca() {
        if (this.marca == null) {
            this.marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void guardar() throws IOException {
        marca.setId(generateID());
        this.guardar(marca);
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(marca, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Marca> ordenarMarca(ListaEnlazada<Marca> lista) {
        try {
            Marca[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Marca key = lista.get(i);
                int j = i - 1;
                while (j >= 0 && (matriz[j].getVersion().compareToIgnoreCase(key.getVersion())) > 0) {
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

    public ListaEnlazada<Marca> ordenarID(ListaEnlazada<Marca> lista, Integer tipo) {
        try {
            Marca[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Marca key = matriz[i];
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

    public ListaEnlazada<Marca> buscarPorMarcas(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Marca> resultado = new ListaEnlazada<>();
        ListaEnlazada<Marca> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Marca aux = lista.get(i);
            if (aux.getVersion().toLowerCase().startsWith(dato)) {
                resultado.insertar(aux);
            }
        }
        return resultado;
    }

//Busqueda Lineal Marca
    public ListaEnlazada<Marca> busquedaLinealMarca(String texto) throws Exception {
        ListaEnlazada<Marca> result = new ListaEnlazada<>();
        ListaEnlazada<Marca> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Marca aux = lista.get(i);
            if (aux.getVersion().toLowerCase().startsWith(texto.toLowerCase())) {
                result.insertar(aux);
            }            
        }
        return result;
    }
    public ListaEnlazada<Marca> busquedaLinealSiglas(String texto) throws Exception {
        ListaEnlazada<Marca> result = new ListaEnlazada<>();
        ListaEnlazada<Marca> lista = listar();
        for (int i = 0; i < lista.size(); i++) {   
            Marca aux = lista.get(i);
            if (aux.getSiglas().toLowerCase().startsWith(texto.toLowerCase())) {
                result.insertar(aux);
            }
        }
        return result;
    }
    public ListaEnlazada<Marca> busquedaLinealIdMarca(String texto) throws Exception {
        Celular celular = new CelularDAO().buscarPorMarcas(texto);
        ListaEnlazada<Marca> result = new ListaEnlazada<>();
        ListaEnlazada<Marca> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Marca aux = lista.get(i);
            if(celular != null){
                if (aux.getId_celular().intValue() == celular.getId().intValue()) {
                    result.insertar(aux);
                }
            }
        }
        return result;
    }
//Busqueda Binaria Marca
    public Marca busquedaBinariaMarca(String texto) throws VacioException, PosicionException {
        ListaEnlazada<Marca> lista = listar();
        ListaEnlazada<Marca> listaOrdenada =  ordenarMarca(lista);
        Marca[] matriz = listaOrdenada.toArray();
        int der = 0;
        int izq = listaOrdenada.size() - 1;
        while (der <= izq) {
            int medio = der + (izq - der) / 2;
            Marca aux = matriz[medio];            
            if (aux.getVersion().compareToIgnoreCase(texto) == 0) {
                return aux;
            } else {
                if (aux.getVersion().compareToIgnoreCase(texto) < 0) {
                    der = medio + 1; 
                } else {
                    izq = medio - 1; 
                }
            }
        }
        return null;
    }
    public Marca busquedaBinariaSiglas(String texto) throws VacioException, PosicionException {
        ListaEnlazada<Marca> lista = listar();
        ListaEnlazada<Marca> listaOrdenada =  ordenarMarca(lista);
        Marca[] matriz = listaOrdenada.toArray();
        int der = 0;
        int izq = listaOrdenada.size() - 1;
        while (der <= izq) {
            int medio = der + (izq - der) / 2;
            Marca aux = matriz[medio];                        
            if (aux.getSiglas().compareToIgnoreCase(texto) == 0) {
                return aux;
            } else {
                if (aux.getSiglas().compareToIgnoreCase(texto) < 0) {
                    der = medio + 1; 
                } else {
                    izq = medio - 1; 
                }
            }
        }
        return null; 
    }
    public Marca busquedaBinariaIdMarca(String texto) throws VacioException, PosicionException {
        Celular a = new CelularDAO().buscarPorMarca(texto);
        ListaEnlazada<Marca> lista = listar();
        ListaEnlazada<Marca> listaOrdenada =  ordenarMarca(lista);
        Marca[] matriz = listaOrdenada.toArray();
        int der = 0;
        int izq = listaOrdenada.size() - 1;
        while (der <= izq) {
            int medio = der + (izq - der) / 2;
            Marca aux = matriz[medio];                        
            if(a != null){
                if (aux.getId_celular().intValue() == a.getId().intValue()) {
                    return aux;
                } else {
                    if (aux.getId_celular().intValue() < a.getId().intValue()) {
                        der = medio + 1; 
                    } else {
                        izq = medio - 1; 
                    }
                }
            }
        }
        return null; 
    }
}
