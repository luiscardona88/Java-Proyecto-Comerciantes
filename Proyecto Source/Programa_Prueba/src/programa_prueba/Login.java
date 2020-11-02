
package programa_prueba;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Login  extends Login_Overridable{
    int resul=0;
    JLabel nombre_user= new JLabel();
    JLabel pass=new JLabel();
    JTextArea nomtext= new JTextArea("");
    
    JPasswordField passtext= new JPasswordField();
     JPanel panel_Login_inside= new JPanel();
     JPanel panel_contenedor;
      ImageIcon icono_imagen= new ImageIcon("images/candado.jpg");
      JLabel eti_imagen= new JLabel(icono_imagen);
      JLabel eti_imagen2= new JLabel(icono_imagen);
      JButton LoginB=new JButton("Login");
       Business_Logic.Business_Logic logica= new Business_Logic.Business_Logic();
     Login(){
         super();
         
         try{
            
          programa_prueba.Delete_Files df = new programa_prueba.Delete_Files();
          java.io.BufferedWriter salida= new java.io.BufferedWriter(new java.io.FileWriter("C:\\lista\\resultados.txt",true));
          salida.newLine();
         salida.flush();
         salida.close();
         }
         catch(Exception ex){
             
         }
         
         this.getContentPane().setBackground(Color.WHITE);
      this.setSize(600, 120);  
       this.setLocation(400, 200);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        panel_Login_inside.setBackground(Color.WHITE);
         panel_Login_inside.setLayout(new GridLayout(4,3));
         panel_Login_inside.add(new JLabel(""));
        panel_Login_inside.add(new JLabel("Nombre"));
        panel_Login_inside.add(nomtext);    
         panel_Login_inside.add(new JLabel(""));
        panel_Login_inside.add(new JLabel(""));
        panel_Login_inside.add(new JLabel(""));         
        panel_Login_inside.add(new JLabel(""));
        panel_Login_inside.add(new JLabel("Password"));
        panel_Login_inside.add(passtext);               
          panel_Login_inside.add(new JLabel(""));        
           this.LoginB.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {              
                Login l = new Login();
              l.Validar_Usuario(nomtext.getText(), passtext.getText());
              
            }
        });
                   
    panel_Login_inside.add(LoginB);
        panel_Login_inside.add(new JLabel(""));     
        this.panel_contenedor= new JPanel();
        panel_contenedor.setLayout(new GridLayout(1,3));
        panel_contenedor.add(eti_imagen);
        panel_contenedor.add(panel_Login_inside);
        this.setLayout(new GridLayout(1,3)); 
        this.add(eti_imagen);
        this.add(panel_contenedor);
        this.add(eti_imagen2);                   
        try{
        logica.connect("Normal");
         logica.vaciar_tabla();// limpiamos la tabla total de la base de datos
        }
        catch(Exception ex){
     System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace()+ex.getClass().toString());
            
        }
    }
    @Override   
    public int Validaacion_Previa(String user, String pass) {
       
        if(user!=null && pass!=null){
     resul=1;
        }
        
        return resul; 
    }

    @Override
    public void  Validar_Usuario(String user, String pass) {
       
       if(Validaacion_Previa(user,pass)!=0){
         
       if(logica.Insertar_User_Validar_User(user, pass)!=0)
       {

          new Resultados().setVisible(true);
       }          
       }
                 
    }    
    public static void main(String args[]){
        Login l= new Login();
        l.setVisible(true);
    }
}
