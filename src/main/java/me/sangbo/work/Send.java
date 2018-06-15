package me.sangbo.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/13.
 */
public class Send {

  public static final String QUEUE_NAME= "queue_work_sangbo";

  public static void main(String[] args) throws IOException, TimeoutException {

    Connection connection = ConnectionUtils.getConnection();
    Channel channel = connection.createChannel();
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);

    for (int i = 0; i < 50; i++) {
      String msg = "send："+i;
      channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
    }

    channel.close();
    connection.close();

  }

}
