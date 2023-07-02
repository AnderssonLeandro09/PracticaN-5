package vista.utilidades;

import controlador.dao.AdaptadorDAO;
import controlador.dao.CelularDAO;
import controlador.ed.listas.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import javax.swing.JComboBox;
import modelo.Celular;

/**
 *
 * @author Andersson
 */
public class Utilidades {
    public static void cargarEquipo(JComboBox cbx, CelularDAO ed) throws VacioException, PosicionException {
        cbx.removeAllItems();
        ListaEnlazada<Celular> lista = ed.ordenarMarca(ed.listar(), AdaptadorDAO.ASCENDENTE);
        for(int i = 0; i < lista.size(); i++){
            cbx.addItem(lista.get(i).getMarca());
        }
        
    }
}
