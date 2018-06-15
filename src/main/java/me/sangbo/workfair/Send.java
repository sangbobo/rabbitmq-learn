package me.sangbo.workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/13.
 */
public class Send {

  public static final String QUEUE_NAME= "queue_work_test_durable_2_sangbo";

  public static void main(String[] args) throws IOException, TimeoutException {

    Connection connection = ConnectionUtils.getConnection();
    Channel channel = connection.createChannel();
    boolean durable = false;
    channel.queueDeclare(QUEUE_NAME,durable,false,false,null);
    channel.basicQos(1);
    for (int i = 0; i < 50; i++) {
      String msg = "send："+i;
      channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
    }

    channel.close();
    connection.close();

  }

}
