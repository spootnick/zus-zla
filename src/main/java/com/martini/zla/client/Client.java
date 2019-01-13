package com.martini.zla.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class Client {

    private Logger log = LoggerFactory.getLogger(Client.class);

    public static final String SERVICE = "https://pue.zus.pl:8500/ws/zus.channel.platnikRaportyZla:wsdlPlatnikRaportyZla/zus_channel_platnikRaportyZla_wsdlPlatnikRaportyZla_Port";
    public static final String WSDL = SERVICE + "?wsdl";


    private static final String AUTH = "Basic " + Base64.getEncoder().encodeToString("b2b_platnik_raporty_zla:b2b_platnik_raporty_zla".getBytes());

    private URL url;

    public Client() {
        try {
            url = new URL(SERVICE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String read(InputStream is) throws IOException {
        byte[] buffer = new byte[1000];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int c;
        while( (c = is.read(buffer)) != -1){
            baos.write(buffer, 0, c);
        }
        is.close();
        return new String(baos.toByteArray());
    }

    public Response send(Request request) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", AUTH);
            connection.setDoOutput(true);


            log.info(request.content);

            OutputStream os = connection.getOutputStream();
            os.write(request.content.getBytes());
            os.flush();
            os.close();


            InputStream is = connection.getInputStream();
            if(is == null){
                is = connection.getErrorStream();
            }

            String resp = read(is);
            connection.disconnect();

            return new Response(resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
