package me.sangbo.tx

import me.sangbo.ConnectionUtils

/**
 * Created by 桑博 on 2018/6/15.
 */

private const val QUEUE_NAME = "queue_sangbo_confirm1"
fun main(args: Array<String>) {

    val connection = ConnectionUtils.getConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME,false,false,false,null)
    val msg = "confrim消息";
    channel.confirmSelect()
    channel.basicPublish("", QUEUE_NAME,null,msg.toByteArray())
    if(channel.waitForConfirms()){
        println("rev成功收到了消息")
    }else{
        println("消息接受失败")
    }

    channel.close();
    connection.close();


}