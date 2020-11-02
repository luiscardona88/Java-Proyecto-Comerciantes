
package Business_Logic;

public class Business_Logic {
    Entidad_de_Negocios.Comerciante entidad;
    Acceso_A_Datos.Base_De_Datos b;
    java.util.List<Integer> Lista_ids= new java.util.ArrayList<Integer>();
    public void connect(String paramter)throws Exception{
             
      if(paramter=="Normal")  
      {
           this.b= new Acceso_A_Datos.Base_De_Datos(paramter);
       b.consulta(entidad);   
      }
      else if(paramter=="cotizacion"){
          
          this.b= new Acceso_A_Datos.Base_De_Datos(paramter);
          
      }
      else if(paramter=="without accion"){
          
          
      }
      else{
          this.b= new Acceso_A_Datos.Base_De_Datos(paramter);
          b.Query_Comerciante_Job_Function(entidad);
      }
     
    }
     public java.util.List<String> Listar_entidades_Acceso(){
               
         return b.Listar_entidades();     
     }
     public void  add_new_record(int id,String nombre)throws Exception{
         
         b.add_record(id,nombre);      
     }
     public java.util.List<Integer>  Listar_ids()throws Exception{
         
       Lista_ids=  b.Listar_id();   
       return Lista_ids;
     }
     
     public int Result_record(){
         
         
        return b.Records_count();
     }
     public int Result_record_Full(){
         
         
        return b.Records_count_Full();
     }
      public java.util.List<Object> Boolean_value(){
          
          return b.Boolean_value();
      }
     public void close_conexion(){
         b.close_connection();     
     }
     public void actualiza(int id,String nombre){
         
         b.actualizar(id, nombre);       
     }
     public int Insertar_User_Validar_User(String nombre,String pass){
         
       return  b.Insertar_User_Validar_User(nombre,pass);        
     }
     public void Eliminar(String nom){       
         b.Eliminar(nom);    
     }
     public void Buscador(String parametro,javax.swing.JTable tabla){
         
         try{
         b.Buscador(parametro, tabla);
         }
         catch(Exception ex){
         System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace()+ex.getClass().toString());
 
         }
         
         
     }
     
     public void Query_radio_Buttons(int indice_radio,javax.swing.JTable tabla_radios,String parametro){
         try{
       b.Query_radio_Buttons(indice_radio, tabla_radios, parametro);
         }
         catch(Exception ex){
               System.out.println(ex.getCause()+":"+ex.getMessage()+":"+ex.getStackTrace()+ex.getClass().toString());
         }
         
     }
     
     public int Cotizacion(boolean bool,int value_stream)throws Exception{
      
         return b.Cotizacion(bool, value_stream);
      
       
     }
     public void vaciar_tabla()throws Exception{
         
         b.vaciar_tabla();
     }
     }

