package me.sangbo.tx

import com.rabbitmq.client.ConfirmListener
import me.sangbo.ConnectionUtils
import java.util.*

/**
 * Created by 桑博 on 2018/6/15.
 */
private const val QUEUE_NAME = "queue_sangbo_confirm3"
fun main(args: Array<String>) {

    val connection = ConnectionUtils.getConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME,false,false,false,null)
    channel.confirmSelect()

    val confirmSet = Collections.synchronizedSortedSet(TreeSet<Long>())

    channel.addConfirmListener(object: ConfirmListener {
        override fun handleAck(p0: Long, p1: Boolean) {
            println("这是一个批量消息吗？${p1}：p0的消息是：${p0}")
            if(p1){
                confirmSet.headSet(p0+1).clear()
            }else{
                confirmSet.remove(p0)
            }
        }

        override fun handleNack(p0: Long, p1: Boolean) {
            println("这是一个批量消息吗？${p1}：p0的消息是：${p0}")
            if(p1){
                confirmSet.headSet(p0+1).clear()
            }else{
                confirmSet.remove(p0)
            }
        }
    })


    val msg = "测试消息——桑"



//    channel.close();
//    connection.close()

}

