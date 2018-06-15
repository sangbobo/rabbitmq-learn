package me.sangbo.roulting

import me.sangbo.ConnectionUtils
import java.nio.charset.Charset

/**
 * Created by 桑博 on 2018/6/14.
 */
private const val EXCHANGE_NAME = "exchange_sangbo_direct"
private const val ROULTING_KEY = "info";

fun main(args: Array<String>) {

    val connection = ConnectionUtils.getConnection();
    val channel = connection.createChannel();
    channel.exchangeDeclare(EXCHANGE_NAME,"direct");
    val msg = "测试";
    channel.basicPublish(EXCHANGE_NAME,ROULTING_KEY,null,msg.toByteArray());
    channel.close();
    connection.close();

}