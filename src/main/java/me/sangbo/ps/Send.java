package me.sangbo.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/13.
 */
public class Send {

  public static final String EXCHANGE_NAME= "exchange_ps_sangbo_fanout";

  public static void main(String[] args) throws IOException, TimeoutException {


    Connection connection = ConnectionUtils.getConnection();
    Channel channel = connection.createChannel();
    channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
    String msg = "分发";
    channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

    System.out.println("send："+msg);
    channel.close();
    connection.close();

  }

}
