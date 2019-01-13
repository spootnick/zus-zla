package com.martini.zla;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

    public static final String FILE = "zla.properties";

    public final String login;
    public final String password;
    public final String nip;

    private Configuration(){
        Properties props = new Properties();
        File file = new File(FILE);
        if(!file.exists()){
            throw new RuntimeException(FILE+" not found, must contain: login, password, nip");
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

    public static Configuration load(){
        return new Configuration();
    }
}
