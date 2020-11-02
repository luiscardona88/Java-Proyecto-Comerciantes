
package programa_prueba;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;


public class Login_Overridable extends JFrame implements estructura  {
int Resultado=0;
    @Override
    public int Validaacion_Previa(String user, String pass) {     
        return 0;
    }

    @Override
    public void Validar_Usuario(String user, String pass) {             
    }          
}
interface estructura{
    public int  Validaacion_Previa(String user,String pass);      
   public void Validar_Usuario(String user,String pass);
       
}