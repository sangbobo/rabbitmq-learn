package me.sangbo.simple;

import com.rabbitmq.client.*;
import me.sangbo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 桑博 on 2018/6/12.
 */
public class Rev {

  public static final String QUEUE_NAME= "queue_sangbo";
  public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

    //获取连接
    Connection connection = ConnectionUtils.getConnection();
    //创建频道
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME,false,false,false,null);

    DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body,"utf-8");
        System.out.println("收到的消息：" + msg);
      }
    };

    channel.basicConsume(QUEUE_NAME,true,defaultConsumer);

  }
  private static void oldMethod() throws IOException, TimeoutException, InterruptedException {
    //获取连接
    Connection connection = ConnectionUtils.getConnection();
    //创建频道
    Channel channel = connection.createChannel();
    //定义消费者
    QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
    //监听队列
    channel.basicConsume(QUEUE_NAME,true,queueingConsumer);

    while (true){

      QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
      String msg = new String(delivery.getBody());
      System.out.println(msg);

    }
  }
}
