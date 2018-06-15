package me.sangbo.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/12.
 */
public class Send {


  public static final String QUEUE_NAME= "queue_sangbo";

  public static void main(String[] args) throws IOException, TimeoutException {


    Connection connection = ConnectionUtils.getConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    String msg = "桑博，今天晚上去吃饭吗！饿了。。。";

    channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
    channel.close();
    connection.close();

  }

}
