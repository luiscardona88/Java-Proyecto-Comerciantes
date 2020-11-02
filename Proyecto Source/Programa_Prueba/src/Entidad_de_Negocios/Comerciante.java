
package Entidad_de_Negocios;

public class Comerciante {
    private int id_comerciante=0;
    public String nombre_comerciante="";
    public String nombre_empleo="";
    private String nombre_funcion="";
    private int Precio=0;
    public void set_id_comerciante(int id){
        this.id_comerciante=id;             
    }
    public  int get_id_comerciante(){      
        return this.id_comerciante;
    }
      
    public void set_nombre_comerciante(String nombre)
    {
        nombre_comerciante=nombre;
    }
     public  String get_nombre_comerciante(){      
        return this.nombre_comerciante;    
}
     
 public void set_nombre_empleo(String empleo)
    {
        nombre_empleo=empleo;
    }
     public  String get_nombre_empleo(){      
        return this.nombre_empleo;    
}    
     
   public  String get_nombre_funcion(){      
        return this.nombre_funcion;    
}      
 public  void set_nombre_funcion(String nom_funcion){      
     this.nombre_funcion=nom_funcion;
}     
 
    public  int get_precio(){      
        return this.Precio;  
}      
 public  void set_precio(int Precio_parameter){      
     this.Precio=Precio_parameter;
}     
 

}