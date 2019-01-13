package com.martini.zla;

import com.martini.zla.business.Parser;
import com.martini.zla.client.Client;
import com.martini.zla.client.Request;
import com.martini.zla.client.Response;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;


public class Console {


    private static Logger log = LoggerFactory.getLogger(Console.class);

    private static InputStream unzip(Response response, String password) throws Exception{
        FileOutputStream fos = new FileOutputStream("test.zip");
        fos.write(response.getDecodedData());
        fos.flush();
        fos.close();

        ZipFile zip = new ZipFile("test.zip");
        zip.setPassword(password);
        ZipInputStream is = zip.getInputStream((FileHeader) zip.getFileHeaders().get(0));
        return is;
    }

    public static void main(String[] args) throws Exception {

        Configuration conf;
        if(args.length > 0){
            conf = Configuration.load(args[0]);
        }else{
            conf = Configuration.load();
        }


        Request req = new Request(conf.login, conf.password, conf.nip, LocalDate.of(2018, 12, 20));
        Response response = new Client().send(req);

        log.info(response.raw);

        log.info(response.getData());


        InputStream is = unzip(response, conf.password);


        String report = Client.read(is);
        log.info(report);

        new Parser().parse(report);


    }
}
