package me.sangbo.topic

import me.sangbo.ConnectionUtils

/**
 * Created by 桑博 on 2018/6/14.
 */
private const val EXCHANGE_NAME = "exchange_sangbo_topic"
private const val ROULTING_KEY = "goods.delete";
fun main(args: Array<String>) {

    val connection = ConnectionUtils.getConnection()
    val channel = connection.createChannel()
    channel.exchangeDeclare(EXCHANGE_NAME,"topic");
    val msg = "kotlin";
    channel.basicPublish(EXCHANGE_NAME, ROULTING_KEY,null,msg.toByteArray())

    channel.close();
    connection.close();

}