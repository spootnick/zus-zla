package com.martini.zla.client;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Response {

    public final String raw;

    Response(String raw){
        this.raw = raw;
    }

    public String getData(){
        String tag = "<zawartosc>";
        int open = raw.indexOf(tag);
        if(open == -1){
            throw new RuntimeException(tag+" is missing");
        }
        int close = raw.indexOf("</zawartosc>");

        String data = raw.substring(open+tag.length(), close);

        return data;
    }

    public byte[] getDecodedData(){
        return Base64.getDecoder().decode(getData().replace("\n","").replace("\r", ""));


    }
}
