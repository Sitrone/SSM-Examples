package com.sununiq.webservice.v1.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * ref: https://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld {

    @WebMethod
    String getHelloWorldAsString(String name);
}
