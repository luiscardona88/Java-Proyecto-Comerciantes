
package programa_prueba;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Actualizar_Modelo_Tabla {
    java.util.List<String>values_comerciantes2=new ArrayList<String>();
    java.util.List<Integer>ids2=new ArrayList<Integer>();
    static java.util.List<String> lista_results= new java.util.ArrayList<String>();
    static Business_Logic.Business_Logic logica= new Business_Logic.Business_Logic() ;
  static java.util.List<Object> lista_boolean_p_2= new  ArrayList<Object>();  
  static Boolean bool2;
   public static javax.swing.table.DefaultTableModel Updated_Model() throws Exception{
       logica.connect("Normal");
        String datos[][]={};
        
        String columns[]={"Identificador","Nombre","Actualizar","Eliminar"};
         javax.swing.table.DefaultTableModel model;
      model=  new javax.swing.table.DefaultTableModel(datos,columns);   
    lista_results= logica.Listar_entidades_Acceso();      
    lista_results= logica.Listar_entidades_Acceso();
        model.setNumRows(lista_results.size());         
      for(int x=0;x<lista_results.size();x++){         
         int output=x%2;
         if(output==0){
              model.setValueAt(lista_results.toArray()[x], x, 1);

         }
         else{
             model.setValueAt(lista_results.toArray()[x], x-1, 0);
             
         }
      
      }
     
     javax.swing.JCheckBox check= new javax.swing.JCheckBox();
     lista_boolean_p_2=logica.Boolean_value();
      
      for(int x=0;x<lista_results.size();x++){
    
         model.setValueAt(new Boolean(false), x, 2);      
           model.setValueAt(new Boolean(false), x, 3);  
      }
     
         model.fireTableDataChanged();
      return model;
   }
}
