/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 *
 * @author luongt7640
 */
public class caHelper {
    
    // cache helper
    
    public static JSONObject cache = new JSONObject();
    
    public static Object getCache( String name )
    {
        try{
            return cache.get(name);
        } catch( Exception e ){
            e.printStackTrace();
            
        }
        return null;
    }
    
    public static void setCache( String name , Object value )
    {
        try{
            cache.put( name , value);
        } catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    
    public static String displayJson( JSONArray recs )
    {
               
        try{
            
            JSONObject obj = new JSONObject();

            obj.put("ErrorCode", 0);
            obj.put("Count", recs.length() );
            obj.put("Data",  recs );
            
            return obj.toString();
        } catch( Exception e )
        {
            e.printStackTrace();
        }

        return null ;
    }
    
    
    public static String displayJson( String ErrorMessage )
    {
               
        try{
            
            JSONObject obj = new JSONObject();

            obj.put("ErrorCode", 1 );
            obj.put("ErrorMessage",  ErrorMessage );
            
            return obj.toString();
        } catch( Exception e )
        {
            e.printStackTrace();
        }

        return null ;
    }
   
    // sql helper

    public static JSONArray RunSQL( String query )
    {
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527" ;
         
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());

            // connect to the database
            conn = DriverManager.getConnection( DATABASE_URL , "jainr9527", "1322607");

            // create a statement
            stat = conn.createStatement();
            
            query = query.trim().toLowerCase();
            
            if( query.indexOf("-- update") == -1 )
            {
                rs = stat.executeQuery(query);
                return ResultSetConverter.convert( rs );
            }
            else // update , insert , delete
            {
                JSONObject jo = new JSONObject();
                jo.put("rowCount", stat.executeUpdate(query) );
                
                JSONArray ja = new JSONArray();
                ja.put(jo);
                return ja;
            }
            
            //if( rs.getRow()== 0 )
            //    return new JSONArray();
            
           
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
            // close the database
            try{
                conn.close();
                stat.close();
                if( rs != null ) rs.close();
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        }
                

        return null;
    }
    
    // file helper
    
    public static String readFileObsolete( String path , String marker )
    {
            
        path = System.getProperty("user.dir") + path ;
            
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                int count= 0 ;
                while (line != null) {
                    if( line.indexOf( marker ) >= 0 )
                    {
                        count++;
                        line =  br.readLine();
                        continue;
                    }
                    if( count == 0 ){
                        line =  br.readLine();
                        continue;
                    }
                    if( count == 1 && line.indexOf("</div>") >= 0 )
                        break;
                    
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();
                return everything;
            } finally {
                br.close();
            }
        }catch(Exception e )
        {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String readFile( String marker )
    {
        return readFile( "/Model/feed all.txt" , marker );
    }
    
    public static String readFile( String path, String marker )
    {
                 
        InputStream is = caHelper.class.getResourceAsStream(path);
            
        try{
                        
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                int count= 0 ;
                while (line != null) {
                    if( line.indexOf( marker ) >= 0 )
                    {
                        count++;
                        line =  br.readLine();
                        continue;
                    }
                    if( count == 0 ){
                        line =  br.readLine();
                        continue;
                    }
                    if( count == 1 && line.indexOf("</div>") >= 0 )
                        break;
                    
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();
                return everything;
            } finally {
                br.close();
            }
        }catch(Exception e )
        {
            e.printStackTrace();
        }
        return "";
        
        //return readFile( "/Model/feed all.txt" , marker );
    }
    
    // begin get methods for jsonobject 
    
    public JSONObject jsonItem;
    
    caHelper( JSONArray items , int i ){
        try{
            jsonItem = items.getJSONObject(i);
        }catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public Object get( String name )
    {
        try{
            return jsonItem.getInt(name);
        }
        catch( Exception e ){}
        
        try{
            return jsonItem.getDouble(name);
        }
        catch( Exception e ){}
        
        try{
            return jsonItem.getString(name);
        }
        catch( Exception e ){}
        
        try{
            return jsonItem.getBoolean(name);
        }
        catch( Exception e ){}
        return null;
    }
    
}
