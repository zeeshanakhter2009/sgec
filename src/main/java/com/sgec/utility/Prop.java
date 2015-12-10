package com.sgec.utility;


import java.io.*;
import java.net.URL;
import java.util.Properties;

public class Prop {

    // private static String filePath = "D:/ugc_prop/ugc.properties";
//  private static String filePath = "/home/ubuntu/ugc_prop/ugc.properties";
    private static String filePath = "/segec.properties";

    public static String getProperty(String PropertyName) {

        try {
            Properties p = new Properties();
            URL url = Prop.class.getResource(filePath);
            //System.out.println("url.getPath(); ::::::::::: " + url.getPath());

            p.load(new FileInputStream(url.getPath()));
            return p.getProperty(PropertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static void setProperty(String PropertyName, String PropertyValue) {

        try {
            Properties p = new Properties();
            p.load(new FileInputStream("prop1.properties"));
            p.setProperty(PropertyName, PropertyValue);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setPropertyLog(String PropertyName, String PropertyValue) {

        try {
            Properties p = new Properties();
            p.load(new FileInputStream("proplog.properties"));
            p.setProperty(PropertyName, PropertyValue);
            String abc = (String) p.put(PropertyName, PropertyValue);
            p.store(new FileOutputStream("proplog.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setPropertyLog4j(String PropertyName, String PropertyValue) {

        try {
            Properties p = new Properties();
            p.load(new FileInputStream("log4j.properties"));
            p.setProperty(PropertyName, PropertyValue);
            p.put(PropertyName, PropertyValue);
            p.store(new FileOutputStream("log4j.properties"), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
