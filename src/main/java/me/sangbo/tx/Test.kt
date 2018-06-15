package me.sangbo.tx

import java.util.*

/**
 * Created by 桑博 on 2018/6/15.
 */
fun main(args: Array<String>) {


    val sortedSet = Collections.synchronizedSortedSet(TreeSet<Long>())
    for (i in 0..1000){
        sortedSet.add(i.toLong())
    }
    sortedSet.headSet(200).clear()
    for (l in sortedSet) {
        print("${l} ")
    }


}