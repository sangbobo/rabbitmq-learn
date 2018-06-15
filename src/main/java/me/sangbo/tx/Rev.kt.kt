package me.sangbo.tx

import com.rabbitmq.client.*
import me.sangbo.ConnectionUtils

import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * Created by 桑博 on 2018/6/12.
 */

private const val QUEUE_NAME = "queue_sangbo_confirm1"
fun main(args: Array<String>) {

    //获取连接
    val connection = ConnectionUtils.getConnection()
    //创建频道
    val channel = connection.createChannel()

    channel.queueDeclare(QUEUE_NAME, false, false, false, null)

    val defaultConsumer = object : DefaultConsumer(channel) {
        override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {
            val msg = String(body!!)
            channel.basicCancel("取消")
            println("收到的消息：$msg")
        }
    }

    channel.basicConsume(QUEUE_NAME, true, defaultConsumer)

}

