
package programa_prueba;
import com.sun.org.apache.xerces.internal.impl.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Actualizar extends JFrame {
  JTextField nombre_campo;
  JTextField pass_campo;
  JPanel panel_Text;
  JPanel panel_nombre;
  ImageIcon icono_imagen= new ImageIcon("images/refresh.png");
  JLabel eti_imagen= new JLabel(icono_imagen);
  JLabel eti_imagen2= new JLabel(icono_imagen);
  JButton Boton_actualizar= new JButton("Update");
  Business_Logic.Business_Logic logica= new  Business_Logic.Business_Logic();
  public int record;
  public Actualizar Referencia;
    Actualizar(int record) {
        
        super();
        new Resultados().setVisible(true);
        Referencia=this;
        try{
       this.record=record;
        }
        catch(Exception ex){        
        }
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(700, 100);    
        this.nombre_campo=new JTextField("");
        this.pass_campo= new JTextField("");       
        this.panel_nombre=new JPanel();
        panel_nombre.setLayout(new GridLayout(3,3));
        panel_nombre.add(new JLabel("Identificador"));
        panel_nombre.add(nombre_campo);
        panel_nombre.add(new JLabel(""));
        panel_nombre.add(new JLabel("Nombre"));
        panel_nombre.add(pass_campo);       
        panel_nombre.add(new JLabel(""));
        panel_nombre.add(new JLabel(""));
        panel_nombre.add(Boton_actualizar);
        panel_nombre.add(new JLabel(""));     
        this.panel_Text= new JPanel();
        panel_Text.setLayout(new GridLayout(1,3));
        panel_Text.add(eti_imagen);
        panel_Text.add(panel_nombre);
       this.setLayout(new GridLayout(1,3));      
        this.add(eti_imagen);
        this.add(panel_Text);
        this.add(eti_imagen2);
        try{
        java.io.BufferedReader lector = new java.io.BufferedReader(new java.io.FileReader("c:\\lista\\list.txt"));
        
        this.nombre_campo.setText(lector.readLine());
        
        
        
        this.pass_campo.setText(lector.readLine());
        
        }
        catch(Exception exc){
            
            
        }
        this.addWindowListener(new java.awt.event.WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
               
               
              
            }

            @Override
            public void windowClosed(WindowEvent e) {
                
               
                logica.close_conexion();
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
               
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
               
            }
        });
        
        Boton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int resultado=JOptionPane.showConfirmDialog(null, "Deseas Actualizar la Informacion?");               
               switch(resultado){         
                   case 0:
                       try{
                       logica.actualiza(Integer.parseInt(nombre_campo.getText()),pass_campo.getText());
                        JOptionPane.showMessageDialog(null, "Actualizado con exito");                 
                      Resultados rs =new  Resultados();;
       java.io.FileWriter nuevo_valor_record= new java.io.FileWriter("c:\\lista\\nuevo_valor.txt");
              nuevo_valor_record.write(pass_campo.getText());
              nuevo_valor_record.flush();
              nuevo_valor_record.close();
              
                       rs.Actualiza_modelo_tabla(Actualizar_Modelo_Tabla.Updated_Model());

                       }
                       catch(Exception ex){
                           
                           System.out.println(ex.getCause()+""+ex.getMessage()+""+ex.getStackTrace());
                       }
                   break;
                   case 1:
                   break;
                   case 2:                   
                   break;                  
               }
            }
        });
        
        
        try{
             
            logica.connect("Normal");
        }
        catch(Exception ex){
            System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace()+ex.getClass().toString());
        }
    }
    
    public static void main(String args[]){
       new programa_prueba.Actualizar(0).setVisible(true);
    }
}
