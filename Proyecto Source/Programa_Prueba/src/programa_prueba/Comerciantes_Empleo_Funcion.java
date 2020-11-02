
package programa_prueba;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.Line;

public class Comerciantes_Empleo_Funcion extends JFrame {
    public int registro2=0;
    ImageIcon imagen2= new ImageIcon("images/comerciante.jpg");
    JLabel eti_imagen2 = new JLabel(imagen2);
 Object record_catch2=null;
    JPanel menu2;
    JPanel Tabla_Panel2;
    public JTable tabla2;
    JMenu contenedor_menu11;
    JMenu contenedor_menu22;
    JMenu contenedor_menu33;
    JMenu contenedor_menu44;
    JMenuBar menu_bar2;
    JMenuItem item_menu11;
    JMenuItem item_menu22;
    int counter=0;
   Business_Logic.Business_Logic logica2;
 java.util.List<String> lista2= new java.util.ArrayList<String>();
  int record_count=0;
  javax.swing.Timer tiempo2;
  java.util.List<Object> lista_boolean_p2= new  ArrayList<Object>();
  ButtonGroup grupo2= new ButtonGroup();  
  Boolean bool2;
  JButton boton_eliminar2= new JButton("Eliminar");
  Comerciantes_Empleo_Funcion Referencia_principal2;
  
   Acceso_A_Datos.Base_De_Datos b;
   java.io.BufferedReader reader_record;
   public int record_All=0;
  Comerciantes_Empleo_Funcion (){     
   super();
        Referencia_principal2=this;
        this.setSize(700,400);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocation(100, 100);
        this.menu2=new JPanel();
        try{      
         logica2= new Business_Logic.Business_Logic();
          logica2.connect("Full");
          this.reader_record = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\record_found.txt"));
          this.record_All=Integer.parseInt(reader_record.readLine());  
        }
        catch(Exception c){
            System.out.println(c.getLocalizedMessage()+""+c.getMessage());
        }
        System.out.println(record_count);          
        String datos[][]={};      
        String columns[]={"Id_Empleo","Nombre Comerciante","Empleo","Funcion","Precio"};
         javax.swing.table.DefaultTableModel model2;
      model2=  new javax.swing.table.DefaultTableModel(datos,columns);
      String []row_header={"Id_Empleo","Nombre Comerciante","Empleo","Funcion","Precio"};  
      model2.setNumRows(record_All);
     tabla2 = new JTable();
     tabla2.setModel(model2);
     try{
         int counter=0;
          java.io.BufferedReader lector0 = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\columna0.txt"));
           java.io.BufferedReader lector1 = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\columna1.txt"));
            java.io.BufferedReader lector2 = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\columna2.txt"));
             java.io.BufferedReader lector3 = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\columna3.txt"));
             java.io.BufferedReader lector4 = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\columna4.txt"));
           String record_to_insert;
         for(int x=0;x<record_All;x++){     
             record_to_insert=lector0.readLine();       
              tabla2.setValueAt(record_to_insert,counter,0);
              record_to_insert=lector1.readLine(); 
              tabla2.setValueAt(record_to_insert,counter,1);
               record_to_insert=lector2.readLine(); 
               tabla2.setValueAt(record_to_insert,counter,2);
               record_to_insert=lector3.readLine();
               tabla2.setValueAt(record_to_insert,counter,3);
               record_to_insert=lector4.readLine();
                tabla2.setValueAt(record_to_insert,counter,4);
                counter++;
         }
     
     }
     catch(Exception ex){
         
         System.out.println(ex.getCause()+""+ex.getLocalizedMessage()+""+ex.getMessage());
     }
     
    tabla2.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               registro2= tabla2.getSelectedRow();
               int columna_selected=tabla2.getSelectedColumn();
               
               if(columna_selected==2){
              Object valor= tabla2.getValueAt(registro2, columna_selected-1);
               Object valor_id=tabla2.getValueAt(registro2,0);
                  javax.swing.JOptionPane.showMessageDialog(null, valor);
                  try{
                   java.io.FileWriter escritor= new java.io.FileWriter("c:\\lista\\list.txt");
                  escritor.write(valor_id.toString());
                  escritor.write(System.getProperty("line.separator"));
                  escritor.write(valor.toString());
                 escritor.flush();
                 Change_column_value();
               
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
                                                   
                  new Actualizar(registro2).setVisible(true);
                  Referencia_principal2.setVisible(false);
               }
               else if(columna_selected==3){
                   try{
                Object valor= tabla2.getValueAt(registro2, columna_selected-2);
               Object valor_id=tabla2.getValueAt(registro2,0);  
               java.io.FileWriter escritor2= new java.io.FileWriter("c:\\lista\\list.txt");
                  escritor2.write(valor_id.toString());                 
                    escritor2.write(System.getProperty("line.separator"));
                    escritor2.write(valor.toString());
                  escritor2.flush();
                  escritor2.close();                    
                java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\list.txt"));      
              String user_delete=lector.readLine();         
              user_delete=lector.readLine();              
              int resultado=   javax.swing.JOptionPane.showConfirmDialog(null,"Deseas Eliminar El Usuario: " +user_delete);             
              if(resultado==0){                
                  logica2.Eliminar(user_delete);
                  JOptionPane.showMessageDialog(null,"Eliminado con exito");
          tabla2.setModel(Actualizar_Modelo_Tabla.Updated_Model());  
  
              }
                           }
                  catch(Exception ex)
                  {
                           System.out.println(ex.getCause()+""+ex.getMessage()+""+ex.getStackTrace());         
                  }
                              
                logica2.Eliminar(null); 
              
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
      JScrollPane scrollpane = new JScrollPane();
       scrollpane.setColumnHeaderView(tabla2.getTableHeader());
      scrollpane.setViewportView(tabla2);       
        tabla2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      this.Tabla_Panel2=new JPanel();      
      JScrollPane scrollpane_Para_Contenedor_tabla = new JScrollPane(Tabla_Panel2);
      scrollpane_Para_Contenedor_tabla .setViewportView(Tabla_Panel2);     
        //creamos paneles
        this.menu_bar2=new JMenuBar();
        menu_bar2.setBackground(Color.WHITE);
        this.item_menu11=new JMenuItem("Salir");
        this.item_menu22=new JMenuItem("Verificar");       
        this.contenedor_menu11=new JMenu("");
        this.contenedor_menu11.setIcon(new ImageIcon("images/setting.jpg"));
        contenedor_menu11.setText("Options");
        this.contenedor_menu44=new JMenu(""); 
        contenedor_menu44.setIcon(new ImageIcon("images/action.jpg"));
        contenedor_menu44.setText("Actions");      
        contenedor_menu44.add(new JMenuItem("Refrescar Aplicacion")).addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                tabla2.setModel(Actualizar_Modelo_Tabla.Updated_Model());
                
                
                Cambiar_celdas_a_Booleanas();
                }
                catch(Exception ez){
           
                }
            }
        });
         contenedor_menu11.add(item_menu11);
         contenedor_menu11.add(item_menu22);
         contenedor_menu11.setBackground(Color.WHITE);
            menu_bar2.add(contenedor_menu11,javax.swing.SwingConstants.CENTER);
           menu_bar2.add(contenedor_menu44);            
       this.menu2=new JPanel();
       menu2.setBackground(Color.WHITE);
        JPanel menu_P=new JPanel();
        menu_P.setLayout(new FlowLayout(FlowLayout.RIGHT));
        menu_P.add(menu_bar2);
        menu_P.setBackground(Color.WHITE);    
        //JPanel menu_bar_panel= new JPanel();
       menu2.setLayout(new GridLayout(1,3));
       menu2.add(this.eti_imagen2);
       menu2.add(menu_P);
       menu2.add(new JLabel());       
        Tabla_Panel2.setLayout(new FlowLayout());
       Tabla_Panel2.add(scrollpane); // cambia tabla
       Tabla_Panel2.setBackground(Color.WHITE);       
        this.setLayout(new GridLayout(3,0));     
        this.add(this.menu2);
         this.add(scrollpane_Para_Contenedor_tabla);
         JPanel panel_botones= new JPanel();
         panel_botones.setBackground(Color.WHITE);
         panel_botones.add(new Button("Actualizar"));
         boton_eliminar2.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                     
            }
        });
         panel_botones.add(new Button("Eliminar"));
         this.add(panel_botones);
         
           this.tiempo2= new javax.swing.Timer(1000, new java.awt.event.ActionListener() {

            @Override
                
            public void actionPerformed(ActionEvent e) {
             DefaultTableModel  modelux=(DefaultTableModel)tabla2.getModel();
             for(int x=1;x<modelux.getRowCount();x++){
               record_catch2=  modelux.getValueAt(x,0);
                  if(record_catch2==null || record_catch2=="") {
                    
                      modelux.removeRow(x);
                      modelux.fireTableDataChanged();
                      tabla2.setModel(modelux);
                  }
                       
                  }
             }
            
            });
         
      this.iniciar_tiempo();
  }
      public void Change_column_value(){     
        try{
          java.io.BufferedReader get_nuevo_valor = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\nuevo_valor.txt"));      
          String got_nuevo_valor=get_nuevo_valor.readLine();      
          tabla2.setValueAt(got_nuevo_valor,registro2 ,1);
          Actualiza_modelo_tabla(Actualizar_Modelo_Tabla.Updated_Model());                 
          }
          catch(Exception ex){
              Change_column_value();
          }
    }
    public void Actualiza_modelo_tabla(javax.swing.table.DefaultTableModel modelo) throws Exception{
          
            this.tabla2.setModel(modelo);
         javax.swing.table.TableColumn tcolumna = this.tabla2.getColumnModel().getColumn(2); 
     tcolumna.setCellRenderer(this.tabla2.getDefaultRenderer(Boolean.class));
tcolumna.setCellEditor(this.tabla2.getDefaultEditor(Boolean.class));
   javax.swing.table.TableColumn cols = this.tabla2.getColumnModel().getColumn(3); 
     cols.setCellRenderer(this.tabla2.getDefaultRenderer(Boolean.class));             
    }
    public void iniciar_tiempo(){      
           tiempo2.start();
    }   
      public void Cambiar_celdas_a_Booleanas(){
        TableColumn tcolumnas = tabla2.getColumnModel().getColumn(2); 
     tcolumnas.setCellRenderer(tabla2.getDefaultRenderer(Boolean.class));
tcolumnas.setCellEditor(tabla2.getDefaultEditor(Boolean.class));
     
     TableColumn tcolumnas3 = tabla2.getColumnModel().getColumn(3); 
     tcolumnas3.setCellRenderer(tabla2.getDefaultRenderer(Boolean.class));
tcolumnas3.setCellEditor(tabla2.getDefaultEditor(Boolean.class));                                
          }         
      
      public static void main(String[]args){
        Comerciantes_Empleo_Funcion com = new Comerciantes_Empleo_Funcion();
        com.setVisible(true);
      }
  }
  


