package me.sangbo.work;

import com.rabbitmq.client.*;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/13.
 */
public class Rev2 {


  public static final String QUEUE_NAME= "queue_work_sangbo";
  public static void main(String[] args) throws IOException, TimeoutException {

    Connection connection = ConnectionUtils.getConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME,false,false,false,null);

    DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

        System.out.println("rev2收到的消息："+new String(body,"utf-8"));
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    channel.basicConsume(QUEUE_NAME,true,defaultConsumer);



  }

}
