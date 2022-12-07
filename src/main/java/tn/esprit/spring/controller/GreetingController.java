package tn.esprit.spring.controller;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Group;

@Controller
public class GreetingController {


  //@MessageMapping("/hello")
  //@SendTo("/topic/greetings")
  public Group greeting(Chat message) throws Exception {
	return null;
   // Thread.sleep(1000); // simulated delay
  //  return new Group("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
  }
}
