
package programa_prueba;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
public class Buscador extends JFrame {
    JPanel panel_buscador;
    JPanel panel_centro_buscador;
    JTextArea texto_buscador;
    JButton boton_buscador;
    javax.swing.JScrollPane scroll= new javax.swing.JScrollPane();
    javax.swing.JScrollPane scroll2= new javax.swing.JScrollPane();
    JTable Tabla_buscador;
     Business_Logic.Business_Logic logica = new  Business_Logic.Business_Logic();
     ImageIcon imagen_buscador = new ImageIcon("images/search.jpg");
     JRadioButton opcion1 = new JRadioButton("Buscar Por Funcion");
      JRadioButton opcion2 = new JRadioButton("Buscar por Presupuesto");
       JRadioButton opcion3 = new JRadioButton("Buscar por Trabajo");
       ButtonGroup grupo= new ButtonGroup();
       JPanel panel_radioButton = new JPanel();
       Buscador referencia=this;
    Buscador() throws Exception{
        super();
       this.addWindowListener(new java.awt.event.WindowAdapter() {
       @Override
            public void windowClosing(WindowEvent e){                   
                try{                 
                referencia.setVisible(false);
            programa_prueba.Delete_Files df = new programa_prueba.Delete_Files();
                }
                catch(Exception ex){
                    System.out.println(ex.getLocalizedMessage()+""+ ex.getMessage()+""+ex.getStackTrace());
                }
            }
           
       });
       
        logica.connect("Other");
        
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(900, 200); // ancho y alto

         panel_radioButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        grupo.add(opcion1);
        grupo.add(opcion2);
        grupo.add(opcion3);
        
        panel_radioButton.add(opcion1);
        panel_radioButton.add(opcion2);
        panel_radioButton.add(opcion3);
        
        String[][] filas_buscador={};
        String columnas_buscador[]={"Id_Empleo","Nombre Comerciante","Empleo","Funcion","Precio"};
        javax.swing.table.DefaultTableModel modelo_buscador= new  javax.swing.table.DefaultTableModel(filas_buscador,columnas_buscador);
        modelo_buscador.setNumRows(6);
        Tabla_buscador= new JTable();
        Tabla_buscador.setModel(modelo_buscador);
       scroll.setViewportView(Tabla_buscador);
       
       panel_centro_buscador= new  JPanel();
       panel_centro_buscador.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      this.boton_buscador= new JButton("Buscar");
      boton_buscador.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                java.util.Enumeration<AbstractButton> grup= grupo.getElements();
                
                int counter_button=0;
               while( grup.hasMoreElements()==true){
                  javax.swing.AbstractButton boton =grup.nextElement();

                   if(boton.isSelected()==true){
                       System.out.println(counter_button);
                       logica.Query_radio_Buttons(counter_button, Tabla_buscador,texto_buscador.getText().trim());
                       
                   }
                   
                    counter_button++;
               }  
            }
            
        });
    this.texto_buscador= new JTextArea("",2,10);
 panel_centro_buscador.add(boton_buscador);
  panel_centro_buscador.add(texto_buscador);
        this.setLayout(new GridLayout(2,3));
        this.add(new JLabel(imagen_buscador));
         this.add(panel_centro_buscador);
          this.add( panel_radioButton);
         this.add(new JLabel(""));
         this.add(scroll);
          this.add(new JLabel(""));
          texto_buscador.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
             JTextArea texti=   (JTextArea)e.getSource();
             logica.Buscador(texti.getText(), Tabla_buscador);
            System.out.println(texti.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
               JTextArea texti=   (JTextArea)e.getSource();
               logica.Buscador(texti.getText(), Tabla_buscador);
            System.out.println(texti.getText());
            }
        });
          
    }
    
     public void getSelectedButtonText(ButtonGroup buttonGroup) {
        for (java.util.Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                System.out.println(button.getText());
            }
        }

        
    }
    
    public static void main(String[]args){
        
        try{
        new Buscador().setVisible(true);
        }
        catch(Exception ex){
            System.out.println(ex.getClass()+""+ex.getMessage()+""+ex.getLocalizedMessage());
        }
         
        
    }
}
