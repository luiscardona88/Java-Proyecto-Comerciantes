
package programa_prueba;
import java.io.*;

public class Delete_Files {
    public Delete_Files(){
        
  
             java.io.File file_to_delete = new java.io.File("C:\\lista");
            java.io.File[] files_names=file_to_delete.listFiles();
            String names_files=null;
            for(int x=0; x<=files_names.length; x++){
                files_names[x].delete();
               
            }    
        
        
        
    }
}
