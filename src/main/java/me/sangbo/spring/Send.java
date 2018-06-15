package me.sangbo.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Created by 桑博 on 2018/6/15.
 */
public class Send {


  public static void main(String[] args) throws InterruptedException {

    AbstractApplicationContext abstractApplicationContext = new ClassPathXmlApplicationContext("classpath:context.xml");
    RabbitTemplate rabbitTemplate = abstractApplicationContext.getBean(RabbitTemplate.class);
    rabbitTemplate.convertSendAndReceive("sangbo");
    Thread.sleep(1000);
    abstractApplicationContext.destroy();

  }

}
