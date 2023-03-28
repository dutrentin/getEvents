package br.com.poc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {


    public static String getProp(String property){
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

            
            Properties props = new Properties();
            
            String pathAppProperties = PropertiesUtil.class.getClassLoader().getResource("application.properties").getFile() ;
            
            FileInputStream file = new FileInputStream(pathAppProperties);

            props.load(file);
            return props.getProperty("proximity.limit.distance");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
