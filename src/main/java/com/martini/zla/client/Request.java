package com.martini.zla.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Request {

    private static final String A = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:zus=\"http://zus/zus.channel.platnikRaportyZla:wsdlPlatnikRaportyZla\">    <soapenv:Header/>    <soapenv:Body>       <zus:PobierzRaporty>          <login>";
    private static final String B = "</login>          <haslo>";
    private static final String C = "</haslo>          <nip>";
    private static final String D = "</nip>          <dataOd>";
    private static final String E = "</dataOd>       </zus:PobierzRaporty>    </soapenv:Body> </soapenv:Envelope>";

    public final String content;

    public Request(String login, String pass, String nip, LocalDate date){
        content = A+login+B+pass+C+nip+D+date.format(DateTimeFormatter.ISO_LOCAL_DATE)+E;
    }


}
