package me.sangbo.roulting

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import me.sangbo.ConnectionUtils

/**
 * Created by 桑博 on 2018/6/14.
 */
private const val QUEUE_NAME  = "queue_roulting_2_sangbo"
private const val EXCHANGE_NAME = "exchange_sangbo_direct"
private const val ROULTING_KEY = "info";
fun main(args: Array<String>) {

    val connection = ConnectionUtils.getConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME,false,false,false,null);
    channel.exchangeDeclare(EXCHANGE_NAME,"direct")
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROULTING_KEY);
    channel.basicQos(1)

    val consumer = object : DefaultConsumer(channel){
        override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {

            println("rev2："+ String(body!!));
            Thread.sleep(1000);
            channel.basicAck(envelope!!.deliveryTag,false)
        }
    }

    val autoAck = false;
    channel.basicConsume(QUEUE_NAME,autoAck,consumer);



}
