package jaxwsExample.ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class HelloWs {

  public String sayHello(String name) {
    if (name == null) {
      return "Hello";
    }

    return "Hello, " + name + "!";
  }
}