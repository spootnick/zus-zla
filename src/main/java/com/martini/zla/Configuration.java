package com.martini.zla;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

    public static final String FILE = "zla.properties";

    public final String login;
    public final String password;
    public final String nip;

    private Configuration(String path){
        Properties props = new Properties();
        File file = new File(path);
        if(!file.exists()){
            throw new RuntimeException(path+" not found, must contain: login, password, nip");
        }
        try {
            props.load(new FileInputStream(file));
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        login = props.getProperty("login");
        password = props.getProperty("password");
        nip = props.getProperty("nip");
    }

    public static Configuration load(String path){
        return new Configuration(path);
    }

    public static Configuration load(){
        return load(FILE);
    }
}
