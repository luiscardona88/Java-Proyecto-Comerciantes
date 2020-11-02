
package programa_prueba;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cotizacion_Display extends JFrame {
    JPanel panel_areas;
    int counter_lines=0;
    JTextArea  resultado;
    JButton boton_result;
    JLabel etiqueta;
    int suma_total=0;
    int contenedor_num=0;
    Business_Logic.Business_Logic logica= new Business_Logic.Business_Logic();
    String linea_nueva=null;
    javax.swing.JCheckBox check_cot= new javax.swing.JCheckBox();
    public Cotizacion_Display()throws Exception{
        super();
        
        this.setSize(500, 250);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        etiqueta= new JLabel("Resultado de la Cotizacion:");
        this.boton_result= new JButton("Aceptar");
      
        this.resultado= new JTextArea("0");
        this.panel_areas= new JPanel();
        this.panel_areas.setLayout(new FlowLayout(FlowLayout.LEFT));
       panel_areas.add(etiqueta);
       panel_areas.add(resultado);
       panel_areas.add(check_cot);
      
         boton_result.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

              try{
                  logica.connect("cotizacion");
              String stream_line=null;
            java.io.BufferedReader buffer = new java.io.BufferedReader(new java.io.FileReader("C:\\lista\\resultados.txt"));
            
           
             
            do{
               try{
                   linea_nueva=buffer.readLine();
                   logica.Cotizacion(false, Integer.parseInt(linea_nueva));
 
               }
          catch(Exception ex){
                        stream_line="0";
                        linea_nueva="0";
                        int value_cotiz=Integer.parseInt(linea_nueva);
                        logica.Cotizacion(false,value_cotiz);
              }    

            }

            while(buffer.read()!=-1);
System.out.println("el resultado es"+contenedor_num);

    }
    catch(Exception ex){
System.out.println(ex.getCause()+""+ex.getLocalizedMessage()+""+ex.getClass()+""+ ex.getStackTrace());

    }    
                
  if(check_cot.isSelected()==true){
      try{
         resultado.setText(String.valueOf(logica.Cotizacion(true, 0)));  
        
                
      }
      catch(Exception ex){
          
      }   
            }
                
            }
        });
       
       panel_areas.add(boton_result);
        
      this.add(panel_areas);
    }
 
    public static void main(String[]args){
        try{
        new Cotizacion_Display().setVisible(true);
        }
        catch(Exception ex){
            System.out.println(ex.getCause()+""+ex.getLocalizedMessage()+""+ex.getClass()+""+ ex.getStackTrace());
        }
    }
}