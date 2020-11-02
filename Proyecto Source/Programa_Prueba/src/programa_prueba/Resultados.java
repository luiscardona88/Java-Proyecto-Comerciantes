
package programa_prueba;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.Line;
public class Resultados extends JFrame {
  public int registro=0;
    ImageIcon imagen= new ImageIcon("images/imagen.png");
    JLabel eti_imagen = new JLabel(imagen);
 Object record_catch=null;
    JPanel menu;
    JPanel Tabla_Panel;
    public JTable tabla;
    JMenu contenedor_menu;
    JMenu contenedor_menu2;
    JMenu contenedor_menu3;
    JMenu contenedor_menu4;
    JMenuBar menu_bar;
    JMenuItem item_menu;
    JMenuItem item_menu2;
    int counter=0;
   Business_Logic.Business_Logic logica;
 java.util.List<String> lista= new java.util.ArrayList<String>();
  int record_count=0;
  javax.swing.Timer tiempo;
  java.util.List<Object> lista_boolean_p= new  ArrayList<Object>();
  ButtonGroup grupo= new ButtonGroup();  
  Boolean bool;
  JButton boton_eliminar= new JButton("Eliminar");
  Resultados Referencia_principal;
    Resultados(){
        super();
        Referencia_principal=this;
        this.setSize(1100,400);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.menu=new JPanel();
        try{
        logica= new Business_Logic.Business_Logic();
        logica.connect("Normal");
        lista= logica.Listar_entidades_Acceso();
     
        }
        catch(Exception c){           
        }
        this.record_count=logica.Result_record();
        System.out.println(record_count);          
        String datos[][]={};      
        String columns[]={"Identificador","Nombre","Actualizar","Eliminar"};
         javax.swing.table.DefaultTableModel model;
      model=  new javax.swing.table.DefaultTableModel(datos,columns);
      String []row_header={"Identificador","Nombre","Actualizar","Eliminar"};    
       lista= logica.Listar_entidades_Acceso();         
        model.setNumRows(lista.size());
      for(int x=0;x<lista.size();x++){
          
         int output=x%2;
        
         if(output==0){
              model.setValueAt(lista.toArray()[x], x, 1);
            
         }
         else{
             model.setValueAt(lista.toArray()[x], x-1, 0);    
         }
      
      }    
     javax.swing.JCheckBox check= new javax.swing.JCheckBox();
     lista_boolean_p=logica.Boolean_value();     
      for(int x=0;x<lista.size();x++){
        this.bool= new Boolean(false);
          model.setValueAt(bool, x, 2);      
           model.setValueAt(bool, x, 3);  
      }    
     this.tabla = new JTable();
   tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);   
     tabla.setModel(model);
    tabla.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               registro= tabla.getSelectedRow();
               int columna_selected=tabla.getSelectedColumn();              
               if(columna_selected==2){
                   java.io.FileWriter escritor;
              Object valor= tabla.getValueAt(registro, columna_selected-1);
               Object valor_id=tabla.getValueAt(registro,0);
                  javax.swing.JOptionPane.showMessageDialog(null, valor);
                  try{
                   escritor= new java.io.FileWriter("c:\\lista\\list.txt");
                  escritor.write(valor_id.toString());
                  escritor.write(System.getProperty("line.separator"));
                  escritor.write(valor.toString());
                 escritor.flush();
                 escritor.close();
                 Change_column_value();
                new programa_prueba.Actualizar(registro).setVisible(true);
                  Referencia_principal.setVisible(false);
                  }
                  catch(Exception ex){
                       System.out.println(ex.getCause()+""+ex.getMessage()+""+ex.getStackTrace());    
                  }
                  try{
                           
                  }
                  catch(Exception ex)
                  {
                            System.out.println(ex.getCause()+""+ex.getMessage()+""+ex.getStackTrace());    
                            
                  }
                                                   
                 finally{
                      
                     
                  }
               }
               else if(columna_selected==3){
                   try{
                Object valor= tabla.getValueAt(registro, columna_selected-2);
               Object valor_id=tabla.getValueAt(registro,0);  
               java.io.FileWriter escritor= new java.io.FileWriter("c:\\lista\\list.txt");
                  escritor.write(valor_id.toString());                 
                    escritor.write(System.getProperty("line.separator"));
                    escritor.write(valor.toString());
                  escritor.flush();
                  escritor.close();                    
                java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\list.txt"));      
              String user_delete=lector.readLine();         
              user_delete=lector.readLine();              
              int resultado=   javax.swing.JOptionPane.showConfirmDialog(null,"Deseas Eliminar El Usuario: " +user_delete);             
              if(resultado==0){                
                  logica.Eliminar(user_delete);
                  JOptionPane.showMessageDialog(null,"Eliminado con exito");
                  
                  //Actualizamos modelo de Tabla             
          tabla.setModel(Actualizar_Modelo_Tabla.Updated_Model());  
          
           Cambiar_celdas_a_Booleanas();
  
              }
                           }
                  catch(Exception ex)
                  {
                           System.out.println(ex.getCause()+""+ex.getMessage()+""+ex.getStackTrace());         
                  }
                              
                logica.Eliminar(null); 
              
               }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                     
            }

            @Override
            public void mouseReleased(MouseEvent e) {             
            }

            @Override
            public void mouseEntered(MouseEvent e) {            
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });
     TableColumn tcolumnas = tabla.getColumnModel().getColumn(2); 
     tcolumnas.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
tcolumnas.setCellEditor(tabla.getDefaultEditor(Boolean.class));    
     TableColumn tcolumnas3 = tabla.getColumnModel().getColumn(3); 
     tcolumnas3.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
tcolumnas3.setCellEditor(tabla.getDefaultEditor(Boolean.class));
      JScrollPane scrollpane = new JScrollPane();
       scrollpane.setColumnHeaderView(tabla.getTableHeader());
      scrollpane.setViewportView(tabla);       
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      this.Tabla_Panel=new JPanel();      
      JScrollPane scrollpane_Para_Contenedor_tabla = new JScrollPane(Tabla_Panel);
      scrollpane_Para_Contenedor_tabla .setViewportView(Tabla_Panel);
       
        //creamos paneles
        this.menu_bar=new JMenuBar();
        menu_bar.setBackground(Color.WHITE);
        this.item_menu=new JMenuItem("Salir");
        this.item_menu2=new JMenuItem("Verificar");       
        this.contenedor_menu=new JMenu("");
        this.contenedor_menu.setIcon(new ImageIcon("images/setting.jpg"));
        contenedor_menu.setText("Options");
        this.contenedor_menu2=new JMenu();
        contenedor_menu2.setIcon(new ImageIcon("images/me.png"));
        contenedor_menu2.setText("About");
        this.contenedor_menu3=new JMenu("");
        contenedor_menu3.setIcon(new ImageIcon("images/version.jpg"));
        contenedor_menu3.setText("Version");
        this.contenedor_menu4=new JMenu(""); 
        contenedor_menu4.setIcon(new ImageIcon("images/action.jpg"));
        contenedor_menu4.setText("Actions");
        contenedor_menu4.add(new JMenuItem("Ver  Comerciantes-Empleo-Funcion")).addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new programa_prueba.Comerciantes_Empleo_Funcion().setVisible(true);
            }
        });
        
        contenedor_menu4.add(new JMenuItem("Refrescar Aplicacion")).addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                tabla.setModel(Actualizar_Modelo_Tabla.Updated_Model());
                
                
                Cambiar_celdas_a_Booleanas();
                }
                catch(Exception ez){

                }
            }
        });
        contenedor_menu4.add(new JMenuItem("Buscador")).addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
               new Buscador().setVisible(true);
                }
                catch(Exception ex){
                    
                }
            }
        });
         contenedor_menu.add(item_menu);
         contenedor_menu.add(item_menu2);
         contenedor_menu.setBackground(Color.WHITE);
            menu_bar.add(contenedor_menu,javax.swing.SwingConstants.CENTER);
             menu_bar.add(contenedor_menu2);
              menu_bar.add(contenedor_menu3);
               menu_bar.add(contenedor_menu4);            
       this.menu=new JPanel();
       menu.setBackground(Color.WHITE);
        JPanel menu_P=new JPanel();
        menu_P.setLayout(new FlowLayout(FlowLayout.RIGHT));
        menu_P.add(menu_bar);
        menu_P.setBackground(Color.WHITE); 
       menu.setLayout(new GridLayout(1,3));
       menu.add(this.eti_imagen);
       menu.add(menu_P);
       menu.add(new JLabel());       
        Tabla_Panel.setLayout(new FlowLayout());
       Tabla_Panel.add(scrollpane); // cambia tabla
       Tabla_Panel.setBackground(Color.WHITE);       
        this.setLayout(new GridLayout(3,0));     
        this.add(this.menu);
         this.add(scrollpane_Para_Contenedor_tabla);
         JPanel panel_botones= new JPanel();
         panel_botones.setBackground(Color.WHITE);
         panel_botones.add(new Button("Actualizar"));
         boton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     
            }
        });
         panel_botones.add(new Button("Eliminar"));
         this.add(panel_botones);
         
           this.tiempo= new javax.swing.Timer(1000, new java.awt.event.ActionListener() {

            @Override
                
            public void actionPerformed(ActionEvent e) {
             DefaultTableModel  modelux=(DefaultTableModel)tabla.getModel();
             for(int x=1;x<modelux.getRowCount();x++){
               record_catch=  modelux.getValueAt(x,0);
                  if(record_catch==null || record_catch=="") {
                    
                      modelux.removeRow(x);
                      modelux.fireTableDataChanged();
                      tabla.setModel(modelux);
                  }
                       
                  }
             }
            
              
            });
         
      this.iniciar_tiempo();
  
    }
    
    public void Change_column_value(){
      
        try{
          java.io.BufferedReader get_nuevo_valor = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\list.txt"));      
          String got_nuevo_valor=get_nuevo_valor.readLine();      
          tabla.setValueAt(got_nuevo_valor,registro ,1);
          Actualiza_modelo_tabla(Actualizar_Modelo_Tabla.Updated_Model());
                    
          }
          catch(Exception ex){
              Change_column_value();
          }
    }
    public void Actualiza_modelo_tabla(javax.swing.table.DefaultTableModel modelo) throws Exception{
          
            this.tabla.setModel(modelo);
         javax.swing.table.TableColumn tcolumna = this.tabla.getColumnModel().getColumn(2); 
     tcolumna.setCellRenderer(this.tabla.getDefaultRenderer(Boolean.class));
tcolumna.setCellEditor(this.tabla.getDefaultEditor(Boolean.class));
   javax.swing.table.TableColumn cols = this.tabla.getColumnModel().getColumn(3); 
     cols.setCellRenderer(this.tabla.getDefaultRenderer(Boolean.class));
                
    }
    public void iniciar_tiempo(){
        
           tiempo.start();
    }   
      public void Cambiar_celdas_a_Booleanas(){
        TableColumn tcolumnas = tabla.getColumnModel().getColumn(2); 
     tcolumnas.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
tcolumnas.setCellEditor(tabla.getDefaultEditor(Boolean.class));
     
     TableColumn tcolumnas3 = tabla.getColumnModel().getColumn(3); 
     tcolumnas3.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
tcolumnas3.setCellEditor(tabla.getDefaultEditor(Boolean.class));                          
          }      
      
      public static void main(String[]args){
          new Resultados().setVisible(true);
      }
}
