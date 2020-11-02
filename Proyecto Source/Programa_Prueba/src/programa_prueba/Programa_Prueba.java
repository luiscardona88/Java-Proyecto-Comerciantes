
package programa_prueba;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.*;

public class Programa_Prueba extends JFrame {
    ButtonGroup grupo_radios;
    static Object objeto_id=null;
 static Integer id_comerciante=0;
 static String id_comer="";
 static String names=null;
 JMenu menu;
 JPanel panel_ids= new JPanel();
    JPanel panel;
    JPanel panel_boton;
    JButton boton;
    JTextArea area;
    java.util.List<Entidad_de_Negocios.Comerciante> lista_recuperar;
    java.util.List<String>values_comerciantes=new ArrayList<String>();
    java.util.List<Integer>ids=new ArrayList<Integer>();
    JButton update;
    Business_Logic.Business_Logic b;
    JPanel combo_panel;
    JComboBox combo;
    JPanel grupo;
    JPanel panel_grupo= new JPanel();
    JRadioButton radio1= new JRadioButton("Alumnos");
     JRadioButton radio2= new JRadioButton("Profesores");
      JRadioButton radio3= new JRadioButton("Materias");
      ImageIcon imagen= new ImageIcon("images/indice.gif");
      JLabel label_imagen = new JLabel(imagen);
    Programa_Prueba(){
        
        inicio_aplicacion();
        
    }
   Programa_Prueba(int id_comer,String nombre_comer)throws Exception{         
       super();
       //this.getContentPane().setBackground(Color.WHITE);
       this.combo= new JComboBox();
       panel_ids.setLayout(new GridLayout(4,0));
       
        panel_ids.add(new JLabel("Selecciona alumno por ids"));
        panel_ids.add(new JComboBox());
        panel_ids.add(new JLabel("Selecciona Maestro por ids"));
        panel_ids.add(combo);
        
       this.grupo_radios= new ButtonGroup();
       panel_grupo.setLayout(new FlowLayout());
       this.grupo= new JPanel();
       
       grupo_radios.add(radio1);
        grupo_radios.add(radio2);
         grupo_radios.add(radio3);
         
       grupo.setLayout(new GridLayout(3,0));
       grupo.add(radio1);
       grupo.add(radio2);
       grupo.add(radio3);
        panel_grupo.add(grupo);
        
       final int id_comerciador=id_comer;
      final String name_comer=nombre_comer;
       b= new Business_Logic.Business_Logic();
       b.connect("Normal");      
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(500, 300);
       this.setVisible(true);
       BorderLayout layout = new BorderLayout();
       this.setLayout(new GridLayout(2,3));
       //creamos componentes
       this.panel= new JPanel();
       this.panel_boton=new JPanel();
       
       combo_panel= new JPanel();
       
       this.area= new JTextArea();
       this.boton= new JButton("Conectar");
       this.update=new JButton("Actualizar");
            
       update.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
               b.add_new_record(id_comerciador, name_comer);
                }
                catch(Exception ex)
                { 
         System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace()+ex.getClass().toString());
                                                        
                            }
            }
        });
       boton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola mundo");
                try{
                
                
             values_comerciantes=b.Listar_entidades_Acceso();
             Iterator iterar=values_comerciantes.iterator();
             while(iterar.hasNext()){
                 System.out.println(iterar.next()); 
                 
             }
                 
                }
                catch(Exception ex){
                   System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace());
                }
                finally{
                    b.close_conexion();
                }
            }
        });
       this.area= new JTextArea("Hola escribe tu texto");
       panel.setLayout(new FlowLayout());
       panel_boton.setLayout(new GridLayout(1,2));   
       panel_boton.add(boton);
       panel_boton.add(update);
       panel.add(area);     
       this.add(label_imagen);
       this.add(this.panel_grupo);
       this.add(panel_ids);
       this.add(new Label());
       this.add(panel_boton);   
      this.ids= b.Listar_ids();
      Iterator its=ids.iterator();
     while(its.hasNext()){
       this.combo.addItem(its.next());     
     }
     
     this.combo.addItemListener(new java.awt.event.ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
               JOptionPane.showMessageDialog(null, e.getItem().toString());
            }
        });
   }
      public static int  return_id(){
          
          int nuevo_id=0;
          try{
              System.out.println("dame tu Id");
          java.io.BufferedReader leer_id= new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
          
             nuevo_id = Integer.parseInt(leer_id.readLine());            
          }
          catch(Exception ex){            
              nuevo_id=0;          
          }
          return nuevo_id;
      }
    public static void main(String[] args) {     
    }  
    public void inicio_aplicacion(){      
        
          try{              
     id_comerciante=  return_id();
       if(id_comerciante==0){
      id_comerciante=  return_id();
       }             
           System.out.println("dame tu nombre");
          java.io.BufferedReader leer= new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
         names= leer.readLine();
                 
        new Programa_Prueba(id_comerciante,names).setVisible(true);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage()+""+ex.getCause());
        }                  
    }
    }