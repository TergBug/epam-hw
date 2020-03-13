package org.mycode.annotationbased;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"org.mycode.annotationbased", "org.mycode.model"})
public class SpringAopConfig {
}
