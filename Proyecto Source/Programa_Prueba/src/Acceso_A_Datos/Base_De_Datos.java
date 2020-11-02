
 
package Acceso_A_Datos;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JTable.*;
import java.lang.*;
public class Base_De_Datos {
    java.sql.Connection conexion;
    java.sql.Statement sentencia;
    java.sql.CallableStatement sentencia_store;
    java.sql.ResultSet conjunto;  
    List<Entidad_de_Negocios.Comerciante> lista;
    Entidad_de_Negocios.Comerciante entidad;
     java.util.List<String>values_comerciantes=new ArrayList<String>();
     java.util.List<Integer>Lista_ids=new ArrayList<Integer>();
     int entero=0;
     java.util.List<Object> lista_boolean= new  ArrayList<Object>();
     java.io.FileWriter escritor;
    java.util.List<Entidad_de_Negocios.Comerciante> lista_base_de_datos= new ArrayList<Entidad_de_Negocios.Comerciante>() ;
    java.io.FileWriter write_record= new java.io.FileWriter("c:\\lista\\record_found.txt");
     programa_prueba.Cotizacion_Display cotdisp;
     public  int propiedad_salta_espacios=0;
    public Base_De_Datos(String parameter)throws Exception{
     
      
      
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      this.conexion=   DriverManager.getConnection("jdbc:odbc:Base_Comerciantes","sa","netbus");
        this.sentencia=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
        if(parameter=="Normal"){
        this.conjunto= sentencia.executeQuery("select * from comerciantes");
        }
        else if(parameter=="without accion"){
            
        }
        else{     
        }
     }
    public java.util.List<Integer>  Listar_id(){
        try{
            this.conjunto= sentencia.executeQuery("select * from comerciantes"); 
         while(conjunto.next()){
             entero=Integer.parseInt(conjunto.getString("id_comerciante"));
          Lista_ids.add( Integer.parseInt(conjunto.getString("id_comerciante")));
             
         }
        }
        catch(Exception xx){
            
        }
        return Lista_ids;
    }
    
    public java.util.List<Object> Boolean_value(){
        Object column=null;
        String query="select v.valor  from Comerciantes cm,Valores v where cm.id_comerciante= v.id_comerciante";
        
        try{
          this.conjunto= sentencia.executeQuery(query); 
         while(conjunto.next()){
            
          lista_boolean.add(conjunto.getByte(1));
         }
         }
         catch(Exception EXX){
             
             System.out.println(EXX.getCause()+EXX.getMessage()+EXX.getLocalizedMessage());
         }
        return lista_boolean;
    }
    
    public void Query_radio_Buttons (int indice,javax.swing.JTable tablain,String parametro_radio)throws Exception{
        javax.swing.JTable tabla_especial=tablain;
        String query_radios=null;
       int counter_record_radios=0;
        //creamos modelo de la tabla
        String[][]filas_radios={};
        String []columnas_radios=new String[6];
        javax.swing.table.DefaultTableModel modelo_radios;
        
        switch(indice){
            case 0:
   query_radios="select f.nombre,cm.nombre,e.Nom_Empleo,presup.valor,e.id_empleo from  Comerciantes cm inner join dbo.comerciantes_Empleos comer_empleos on cm.id_comerciante=comer_empleos.id_comerciante inner join Empleos e on e.id_empleo=comer_empleos.id_empleo inner join Empleos_Funciones emf on e.id_empleo = emf.id_empleo inner join Funciones f on emf.funcion = f.id_funcion inner join dbo.Funciones_Presupuesto funcpres on funcpres.id_funcion=f.id_funcion inner join Presupuesto presup on presup.id_presupuesto=funcpres.id_presupuesto where e.id_empleo between 1 and 1000 and presup.valor > 0 AND f.nombre like'%"+parametro_radio+"%'";

   
   columnas_radios[0]="Nombre Funcion";
   columnas_radios[1]="Comerciante";
   columnas_radios[2]="Empleo";
   columnas_radios[3]="Presupuesto";
   columnas_radios[4]="Id_Empleo";
    columnas_radios[5]="Seleccionar";
     modelo_radios= new javax.swing.table.DefaultTableModel(filas_radios, columnas_radios);
   
   
   conjunto=null;
    this.conjunto= sentencia.executeQuery(query_radios);
   while(conjunto.next()){
       try{
    
      
       counter_record_radios++;
       }
       catch(Exception ex){}}    
   
   
   modelo_radios.setNumRows(counter_record_radios);
  
   
   tablain.setModel(modelo_radios);
javax.swing.table.TableColumn columna_boolean=tablain.getColumnModel().getColumn(5);
 columna_boolean.setCellRenderer(tablain.getDefaultRenderer(Boolean.class));
columna_boolean.setCellEditor(tablain.getDefaultEditor(Boolean.class));
 
   counter_record_radios=0;
   conjunto=null; //limpiamos
    this.conjunto= sentencia.executeQuery(query_radios);
   while(conjunto.next()){
             try{
           tablain.setValueAt(conjunto.getString(1), counter_record_radios,0);
           tablain.setValueAt(conjunto.getString(2), counter_record_radios,1);
           tablain.setValueAt(conjunto.getString(3), counter_record_radios,2);
           tablain.setValueAt(conjunto.getInt(4), counter_record_radios,3);
           tablain.setValueAt(conjunto.getInt(5), counter_record_radios,4);
           // tablain.setValueAt(new Boolean(false), counter_record_radios,5);
           counter_record_radios++;
             }
             catch(Exception ex){
   
             }
          
         }
     break;
      case 1:  
query_radios="select presup.valor, f.nombre,cm.nombre,e.Nom_Empleo,e.id_empleo from  Comerciantes cm inner join dbo.comerciantes_Empleos comer_empleos on cm.id_comerciante=comer_empleos.id_comerciante inner join Empleos e  on e.id_empleo=comer_empleos.id_empleo inner join Empleos_Funciones emf on e.id_empleo = emf.id_empleo inner join Funciones f on emf.funcion = f.id_funcion inner join dbo.Funciones_Presupuesto funcpres on funcpres.id_funcion=f.id_funcion inner join Presupuesto presup  on presup.id_presupuesto=funcpres.id_presupuesto where e.id_empleo between 1 and 1000 and presup.valor > 0 AND presup.valor like'%"+parametro_radio+"%'order by presup.valor asc,f.nombre asc";
                
     columnas_radios[0]="Presupuesto";
   columnas_radios[1]="Funcion";
   columnas_radios[2]="Comerciante";
   columnas_radios[3]="Empleo";
   columnas_radios[4]="Id_Empleo";
    columnas_radios[5]="Seleccionar";
   
    this.conjunto=null;// limpiramos el conjunto_de_registros;
    this.conjunto= sentencia.executeQuery(query_radios);
  while(conjunto.next()){try{counter_record_radios++;}catch(Exception ex){}}  
   modelo_radios= new javax.swing.table.DefaultTableModel(filas_radios, columnas_radios);
   modelo_radios.setNumRows(counter_record_radios);
   counter_record_radios=0;      
      tablain.setModel(modelo_radios);
      javax.swing.table.TableColumn columna_boolean3=tablain.getColumnModel().getColumn(5);
 columna_boolean3.setCellRenderer(tablain.getDefaultRenderer(Boolean.class));
columna_boolean3.setCellEditor(tablain.getDefaultEditor(Boolean.class));
      
      
      conjunto=null;
      this.conjunto= sentencia.executeQuery(query_radios);
    while(conjunto.next()){
             try{
           tablain.setValueAt(conjunto.getString(1),counter_record_radios,0);
           tablain.setValueAt(conjunto.getString(2), counter_record_radios,1);
           tablain.setValueAt(conjunto.getString(3), counter_record_radios,2);
           tablain.setValueAt(conjunto.getString(4), counter_record_radios,3);
           tablain.setValueAt(conjunto.getInt(5), counter_record_radios,4);
           counter_record_radios++;
             }
             catch(Exception EXX){
   System.out.println(EXX.getCause()+EXX.getMessage()+EXX.getLocalizedMessage());
             }

         }
  
    
                break;
            case 2:
                query_radios="select e.Nom_Empleo,f.nombre,presup.valor, cm.nombre,e.id_empleo from  Comerciantes cm inner join dbo.comerciantes_Empleos comer_empleos on cm.id_comerciante=comer_empleos.id_comerciante inner join Empleos e  on e.id_empleo=comer_empleos.id_empleo inner join Empleos_Funciones emf on e.id_empleo = emf.id_empleo inner join Funciones f on emf.funcion = f.id_funcion inner join dbo.Funciones_Presupuesto funcpres on funcpres.id_funcion=f.id_funcion inner join Presupuesto presup  on presup.id_presupuesto=funcpres.id_presupuesto where e.id_empleo between 1 and 1000 and presup.valor > 0 and e.Nom_Empleo like'%"+parametro_radio+"%'order by e.Nom_Empleo asc,f.nombre asc ";
      columnas_radios[0]="Empleo";
   columnas_radios[1]="Funcion";
   columnas_radios[2]="Presupuesto";
   columnas_radios[3]="Comerciante";
   columnas_radios[4]="Id_Empleo";
    columnas_radios[5]="Seleccionar";
   
   this.conjunto= sentencia.executeQuery(query_radios);
  while(conjunto.next()){try{counter_record_radios++;}catch(Exception ex){}}
  conjunto=null;
  
    modelo_radios= new javax.swing.table.DefaultTableModel(filas_radios, columnas_radios);      
     modelo_radios.setNumRows(counter_record_radios);
      counter_record_radios=0; //limpiamos egistros
      tablain.setModel(modelo_radios);
        tablain.setModel(modelo_radios);
javax.swing.table.TableColumn columna_boolean2=tablain.getColumnModel().getColumn(5);
 columna_boolean2.setCellRenderer(tablain.getDefaultRenderer(Boolean.class));
columna_boolean2.setCellEditor(tablain.getDefaultEditor(Boolean.class));
      
      
      counter_record_radios=0; //limpiamos y empezamos a contar desde 0
       this.conjunto= sentencia.executeQuery(query_radios);
   while(conjunto.next()){
             try{
           tablain.setValueAt(conjunto.getString(1), counter_record_radios,0);
           tablain.setValueAt(conjunto.getString(2), counter_record_radios,1);
           tablain.setValueAt(conjunto.getInt(3), counter_record_radios,2);
           tablain.setValueAt(conjunto.getString(4), counter_record_radios,3);
           tablain.setValueAt(conjunto.getInt(5), counter_record_radios,4);
           counter_record_radios++;
             }
             catch(Exception EXX){
    System.out.println(EXX.getCause()+EXX.getMessage()+EXX.getLocalizedMessage());
             }
          
         }
   
  
           
                break;
            
            default:
                
            
        }
        
      
    tablain.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try{
             JTable tabla_refer=(JTable)e.getSource();
      int columna_selected=   tabla_refer.getSelectedColumn();
      
      if(columna_selected==5){
          int record_currently=tabla_refer.getSelectedRow();
          
        Object resultado_recuperado=tabla_refer.getValueAt(record_currently,4);
         System.out.println(resultado_recuperado);
         java.io.BufferedWriter salida= new java.io.BufferedWriter(new java.io.FileWriter("C:\\lista\\resultados.txt",true));
          salida.write("N" + resultado_recuperado.toString());
          salida.newLine();
         salida.flush();
         salida.close();
         propiedad_salta_espacios++;
      int cot=   javax.swing.JOptionPane.showConfirmDialog(null, "Deseas Ver Cotizacion Actual?");
      
      switch(cot){
          
          case 0:
              cotdisp = new programa_prueba.Cotizacion_Display();
              cotdisp.setVisible(true);
              break;
             
              
              
          case 1:
              break;
          case 2:
          break;
          
      }
          
      }
      
      
            }
             catch(Exception ex){
                 
                 
             }   
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
              
            }
        });    
        
        
        
    }
    
    public int Cotizacion(boolean return_result,int value_stream)throws Exception{
        
       int total_sum=0;
    
if(return_result==true){
   
  conjunto= sentencia.executeQuery("select sum(tot) from total where tot <> 0");
   while(conjunto.next()){   
 total_sum=conjunto.getInt(1);
        
   }
        
    }
else{
    this.conjunto=null; //limpiamos conjunto_de_registros
       sentencia.executeUpdate("insert into total values("+value_stream+")");
    
}
         return  total_sum;
    }
    public int Records_count(){
        int record_result=0;
        try{
          this.conjunto= sentencia.executeQuery("select count(nombre) from comerciantes"); 
         while(conjunto.next()){
             record_result=conjunto.getInt(1);
          
         }
        }
         catch(Exception exx){
         
             
         }
         return record_result;
        
    }    
    
    
    
    
    
     public int Records_count_Full(){
        int record_result=0;
        try{
             String Query_full="select count(e.id_empleo)" +
"from  Comerciantes cm"+
"inner join dbo.comerciantes_Empleos comer_empleos" +
"on cm.id_comerciante=comer_empleos.id_comerciante" +
"inner join Empleos e" +
"on e.id_empleo=comer_empleos.id_empleo" +
"inner join  Empleos_Funciones emf" +
"on e.id_empleo = emf.id_empleo" +
"inner join Funciones f" +
"on emf.funcion = f.id_funcion" +
"inner join dbo.Funciones_Presupuesto funcpres with (nolock)"+
"on funcpres.id_funcion=f.id_funcion" +
"inner join Presupuesto presup"+
"on presup.id_presupuesto=funcpres.id_presupuesto"+
"where e.id_empleo  between 1 and 1000"+
"and presup.valor > 0";
          this.conjunto= sentencia.executeQuery(Query_full); 
         while(conjunto.next()){
             record_result=conjunto.getInt(0);
          
         }
        }
         catch(Exception exx){
         
           System.out.println(exx.getLocalizedMessage()+""+exx.getMessage()+""+exx.getStackTrace());
         }
         return record_result;
        
    }   
    public void actualizar(int id,String nombre){
        try{
        sentencia.executeUpdate("update comerciantes set nombre='"+nombre+"' where id_comerciante="+id); 
        }
        catch(Exception ex){
            
            
        }   
    }
     public void consulta(Entidad_de_Negocios.Comerciante entidad) throws Exception{
          this.conjunto= sentencia.executeQuery("select * from comerciantes"); 
         while(conjunto.next()){
             entidad=new Entidad_de_Negocios.Comerciante();
            entidad.set_id_comerciante(Integer.parseInt(conjunto.getString("id_comerciante")));
             entidad.set_nombre_comerciante(conjunto.getString("nombre"));
             
              this.values_comerciantes.add(entidad.get_nombre_comerciante());
              this.values_comerciantes.add(String.valueOf(entidad.get_id_comerciante()));
                    
         }
                           
     }
     public java.util.List<String> Listar_entidades(){
         return values_comerciantes;
         
     }
     public int Insertar_User_Validar_User(String nom,String pass){
         int my_result=0;
         try{
         String getDBUSERByUserIdSql = "{call Validar_usuario(?,?,?,?)}";
         this.sentencia_store=this.conexion.prepareCall(getDBUSERByUserIdSql);
          this.sentencia_store.setString(1, nom);
          this.sentencia_store.setString(2, pass);
          this.sentencia_store.setString(3, "Validar_existe_usuario");
           this.sentencia_store.setInt(4, 0);
          this.sentencia_store.registerOutParameter(4, java.sql.Types.INTEGER);
          
          this.sentencia_store.executeUpdate();
         my_result=sentencia_store.getInt(4);
         System.out.println(my_result);
         
         }
         catch(Exception x){
             System.out.println(x.getClass()+""+x.getMessage()+""+x.getLocalizedMessage()+""+x.getStackTrace());
                          
         }
          
         return my_result;
     }
     public void add_record(int id,String nombre) throws Exception{
         entidad=new Entidad_de_Negocios.Comerciante();
         entidad.set_id_comerciante(id);
         entidad.set_nombre_comerciante(nombre); 
        sentencia.executeUpdate("insert into Comerciantes values("+id+",'"+nombre+"')");
     System.out.println("insertado con exito");
}
     
     
     public void Buscador(String parametro,javax.swing.JTable tabla) throws Exception{
         int counter_record=0;
         
  String Query_full="select e.id_empleo,cm.nombre,e.Nom_Empleo,f.nombre,presup.valor from  Comerciantes cm inner join dbo.comerciantes_Empleos comer_empleos  on cm.id_comerciante=comer_empleos.id_comerciante inner join Empleos e  on e.id_empleo=comer_empleos.id_empleo inner join  Empleos_Funciones emf on e.id_empleo = emf.id_empleo inner join Funciones f on emf.funcion = f.id_funcion inner join dbo.Funciones_Presupuesto funcpres on funcpres.id_funcion=f.id_funcion inner join Presupuesto presup  on presup.id_presupuesto=funcpres.id_presupuesto where e.id_empleo  between 1 and 1000 and presup.valor > 0 and cm.nombre like'%"+parametro+"%' order by cm.nombre,f.nombre asc ";        
      String datos[][]={};      
        String columns[]={"Identificador","Nombre","Empleo","Funcion","Precio"};
  javax.swing.table.DefaultTableModel modelo=  new javax.swing.table.DefaultTableModel(datos,columns);
  
         this.conjunto= sentencia.executeQuery(Query_full); 
         
         while(conjunto.next()){
         try{
           counter_record++;       
         }
         catch(Exception ex){
             
         }
     }
         //Estbalcemos el numero de registros
         modelo.setNumRows(counter_record);
         
        
         counter_record=0;
conjunto=null;
  this.conjunto= sentencia.executeQuery(Query_full); 
            while(conjunto.next()){
                try{
           modelo.setValueAt((String.valueOf(conjunto.getInt(1))), counter_record,0);
            modelo.setValueAt(String.valueOf(conjunto.getString(2)), counter_record,1);
             modelo.setValueAt(String.valueOf(conjunto.getString(3)), counter_record,2);
              modelo.setValueAt(String.valueOf(conjunto.getString(4)), counter_record,3);
               modelo.setValueAt(String.valueOf(conjunto.getString(5)), counter_record,4);
                  counter_record++;
                }
                catch(Exception ex){
                   System.out.println(ex.getClass()+""+ex.getMessage()+""+ex.getStackTrace()+""+ ex.getLocalizedMessage());  
                    
                }
         
     }
        tabla.setModel(modelo);  
     }
     public void Eliminar(String nom_eliminar){
             try{
         sentencia.executeUpdate("delete comerciantes where nombre='"+nom_eliminar+"'"); 
             }
             catch(Exception ex){           
             }       
     }
     
     public void Query_Comerciante_Job_Function(Entidad_de_Negocios.Comerciante entidad){
         int record_total=0;
         try{
  String Query_full="select e.id_empleo,cm.nombre,e.Nom_Empleo,f.nombre,presup.valor from  Comerciantes cm inner join dbo.comerciantes_Empleos comer_empleos  on cm.id_comerciante=comer_empleos.id_comerciante inner join Empleos e  on e.id_empleo=comer_empleos.id_empleo inner join  Empleos_Funciones emf on e.id_empleo = emf.id_empleo inner join Funciones f on emf.funcion = f.id_funcion inner join dbo.Funciones_Presupuesto funcpres on funcpres.id_funcion=f.id_funcion inner join Presupuesto presup  on presup.id_presupuesto=funcpres.id_presupuesto where e.id_empleo  between 1 and 1000 and presup.valor > 0 order by cm.nombre,f.nombre asc ";
 this.conjunto= sentencia.executeQuery(Query_full); 
         while(conjunto.next()){
             entidad=new Entidad_de_Negocios.Comerciante();
           entidad.set_id_comerciante(conjunto.getInt(1));
             entidad.set_nombre_comerciante(conjunto.getString(2).toString());
             entidad.set_nombre_empleo(conjunto.getString(3).toString());
             entidad.set_nombre_funcion(conjunto.getString(4));
             entidad.set_precio(conjunto.getInt(5));
             record_total++;
  
           lista_base_de_datos.add(entidad);      
          
         }
 
   Entidad_de_Negocios.Comerciante  nueva_entidad;
 for(int x=0;x<=lista_base_de_datos.toArray().length;x++)
      {
     nueva_entidad=(Entidad_de_Negocios.Comerciante)lista_base_de_datos.toArray()[x];
          
      Write_results(0,String.valueOf(nueva_entidad.get_id_comerciante()));
      Write_results(1,nueva_entidad.get_nombre_comerciante());
      Write_results(2,nueva_entidad.get_nombre_empleo());
      Write_results(3,nueva_entidad.get_nombre_funcion());
      Write_results(4,String.valueOf(nueva_entidad.get_precio()));
      }
         }
         catch(Exception ex ){
             System.out.println(ex.getCause()+""+ex.getLocalizedMessage());
         }
 
         try{
           write_record.write(String.valueOf(record_total));
          write_record.flush();
            
         }
         
         catch(Exception ex){  
         }
     }
     
     public void Write_results(int col_number,String valor){
         try{
             
          this.escritor= new java.io.FileWriter("c:\\lista\\columna" +String.valueOf(col_number)+".txt",true);               
          escritor.write(valor.toString());
         escritor.write(System.getProperty("line.separator"));
                    
                  escritor.flush();
                  escritor.close();
         }
         catch(Exception ex){
             System.out.println(ex.getLocalizedMessage()+""+ex.getMessage());
         }
     }
     
     public void vaciar_tabla() throws Exception{
          this.conjunto=null; //limpiamos conjunto_de_registros
         sentencia.executeUpdate("delete from total where tot>0 or tot=0");

         
         
     }
     public void close_connection(){
         try{
         this.conexion.close();
         }
         catch(Exception ex){
             
         }
         
     }
}