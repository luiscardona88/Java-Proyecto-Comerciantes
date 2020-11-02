
package programa_prueba;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Presentacion extends JFrame implements java.io.Serializable {
    
    ImageIcon icono;
    JProgressBar bar;
    javax.swing.Timer temporaizador;
    JPanel panel;
   int counter=0;
   Object referencia;
   Presentacion ps;
   JPanel panel_grid = new JPanel();
   GridLayout grid = new GridLayout(1,3);
 JLabel eti_imagen;
 JLabel progreso= new JLabel("Esperando.........");
    Presentacion()throws Exception{           
        super();       
        panel= new JPanel();  
       ps=this;       
       this.setSize(400,180);
       this.icono= new ImageIcon("images/Wait_icon.gif");
       this.eti_imagen= new JLabel(icono);
       this.panel_grid.setLayout(grid); 
       this.bar= new JProgressBar();
       bar.setSize(250, 100);
       panel.setSize(500,100 );
        panel.setLayout(new FlowLayout());
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
       BorderLayout layout = new BorderLayout();
       this.setLayout(new GridLayout(0,2));       
        this.temporaizador= new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter=counter+20;
               bar.setValue(counter);              
               if(counter==100){                
                  temporaizador.stop();
                   System.out.println("finalizado con exito");
                    ps.setVisible(false);
                 ps.getContentPane().setVisible(false);
                   Programa_Prueba p = new Programa_Prueba();
                 
               }
            }
        });
        this.temporaizador.setInitialDelay(1000);      
        panel.add(bar);      
        this.temporaizador.start();
        this.add(this.eti_imagen);
        this.add(panel);
        this.add(new JLabel());
        this.add(this.progreso);       
    }
    public static void main(String[]args){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        new Presentacion().setVisible(true);
        }
        catch(Exception ex){        
        }
    }
}
