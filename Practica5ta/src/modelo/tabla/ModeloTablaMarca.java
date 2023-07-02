package modelo.tabla;

import controlador.dao.CelularDAO;
import controlador.ed.listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Marca;

/**
 *
 * @author Andersson
 */
public class ModeloTablaMarca extends AbstractTableModel{
    
    ListaEnlazada<Marca> dato;

    public ListaEnlazada<Marca> getDato() {
        return dato;
    }

    public void setDato(ListaEnlazada<Marca> dato) {
        this.dato = dato;
    }

    @Override
    public int getRowCount() {
        return dato.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Marca ma = null;
        
        try {
            ma = dato.get(i);
        } catch (Exception ex) {
        }
        switch (i1) {
            case 0: return (ma!=null)? ma.getId_celular(): "No determinado";
            case 1: return (ma!=null)? ma.getVersion(): "No determinado";
            case 2: return (ma!=null)? ma.getSiglas() : "No determinado";
            case 3: return (ma!=null)? new CelularDAO().obtener(ma.getId_celular()) : "No determinado";
            
            default:
                return null;
        }
    }
    
    public String getColumnName(int column){
        switch (column) {
            case 0: return "ID";
            case 1: return "VERSION";
            case 2: return "SIGLAS";
            case 3: return "MARCA";
            
            default:
                return null;
        }
    } 
}
