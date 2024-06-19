/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unidad02;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sheco
 */
public class ModeloProductos extends AbstractTableModel{
    
    private  ListaDeProductos productos;
    
    public ModeloProductos() {
        productos=new ListaDeProductos();
        productos.cargarProductos();
    }

    @Override
    public int getRowCount() {
        return productos.total();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto aux=productos.obtener(rowIndex);
        switch(columnIndex){
            case 0: return aux.getCodigo();
            case 1: return aux.getNombre();
            case 2: return aux.getDescripcion();
            case 3: return aux.getPrecio();
            default: return aux.getExistencias();
        }
    }
    
      @Override
    public String getColumnName(int col) {
        switch(col){
            case 0: return "CÓDIGO";
            case 1: return "NOMBRE";
            case 2: return "DESCRIPCIÓN";
            case 3: return "PRECIO";
            default: return "EXISTENCIAS";
        }
    }
    
    @Override
    public Class getColumnClass(int col) {
        switch(col){
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return Float.class;
            default: return Integer.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return true;        
    }
  
    @Override
    public void setValueAt(Object value, int fila, int columna) {
        Producto aux=productos.obtener(fila);
        switch(columna){
            case 0: aux.setCodigo((String)value);
                    break;
            case 1: aux.setNombre((String)value);
                    break;
            case 2: aux.setDescripcion((String)value);
                    break;
            case 3: aux.setPrecio((float)value);
                    break;
            default: aux.setExistencias((int)value);
        }
        fireTableCellUpdated(fila, columna);
    }
    
    public void agregarProducto(Producto producto){
        productos.agregar(producto);
        this.fireTableDataChanged();  
    }
    
    public void eliminarProducto(int indice){
        productos.eliminar(indice);
        this.fireTableDataChanged();
    }
    
    public void guardarEnArchivo(){
       productos.guardarEnArchivo();
    }
    
}
