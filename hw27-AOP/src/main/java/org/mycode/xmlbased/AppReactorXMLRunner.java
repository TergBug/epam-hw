package org.mycode.xmlbased;

import org.mycode.model.ActivatorOfReactor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppReactorXMLRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springaopconfig.xml");
        ActivatorOfReactor activator = applicationContext.getBean(ActivatorOfReactor.class);
        activator.runReactor();
        activator.stopReactor();
    }
}
