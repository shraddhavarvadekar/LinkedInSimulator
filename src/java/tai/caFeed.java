/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tai;

/**
 *
 * @author viky
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class caFeed {
    
    
    
    public static JSONArray call( String name , List<Object> args ){
        //List<Object> args = new ArrayList<Object>();
        //args.add(1);
        //args.add("abcd");

        String query = caHelper.readFile( "/Model/feed all.txt" , name + "\"");
        
        query = String.format( query ,  escape( args.toArray() ) ) ;
        System.out.println( query );
        return caHelper.RunSQL(query);
    }
    
    public static List<String> getParameterNameList( String name  )
    {
        String query = caHelper.readFile( "feed all.html" , name + "\"");

        List< String > params = new ArrayList();  
        
        String[] lines = query.split("\n");
        
        for( int i = 0 ; i < lines.length ; i ++ )
        {
            if( lines[i].length() < 5 ) continue ;
            if( lines[i].substring( 0 , 2 ).equals( "--" ) == false ) continue;
            if( lines[i].indexOf("$s") == -1 ) continue;
            int marker = lines[i].indexOf( "@" ) + 1;
            params.add( lines[i].substring( marker , lines[i].length() ).trim() );
        }
        
        return params ;
    }
    
    public static ArrayList< JSONObject >  call( String name , String[] args ){
        
        //String query = caHelper.readFile( "/Model/feed all.txt" , name + "\"");
        String query = caHelper.readFile( "feed all.html" , name + "\"");

        
        query = String.format( query ,  escape( args )  ) ;
        
        
        ArrayList< JSONObject > result = new ArrayList< JSONObject >();
        JSONArray recs = caHelper.RunSQL(query) ;
        
        if( recs == null ) return result ;
        
        for( int i = 0 ; i < recs.length() ; i ++ )
        {
            try{
                result.add( recs.getJSONObject( i ) );
            }catch( Exception err ){
                err.printStackTrace();
            }
        }
        
        return result;
        
    }
    
    /*public static JSONArray call( String name  ){
        
        String query = caHelper.readFile( "/Model/feed all.txt" , name + "\"");
        
        query = String.format( query  ) ;
        //System.out.println( query );
        return caHelper.RunSQL(query);
    }*/
    
    
    public static Object[] escape ( Object[] texts )
    {
        for( int i = 0 ; i < texts.length ; i ++ )
        {
            texts[i] = texts[i].toString().replace("'", "'''");
        }
        return texts;
    }
}

