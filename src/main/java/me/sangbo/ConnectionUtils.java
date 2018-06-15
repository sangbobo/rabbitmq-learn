package me.sangbo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/12.
 */
public class ConnectionUtils {



  public static Connection getConnection() throws IOException, TimeoutException {


    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setPort(5672);
    factory.setVirtualHost("vhost_sangbo");
    factory.setUsername("sangbo");
    factory.setPassword("sangbo");
    return factory.newConnection();

  }

}
