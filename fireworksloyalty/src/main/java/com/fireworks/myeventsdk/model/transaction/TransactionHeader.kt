package com.fireworks.myeventsdk.model.transaction

import java.util.ArrayList

/**
 * @project:      Asterspring
 * @package:      my.fireworks.pohkong.data.remote.model.transaction
 * @version:      1.0
 * @author:       Dilip <dilipkumar4813@gmail.com>
 * @description:  description
 * @since:        21/08/2019 13:05
 */
class TransactionHeader {
     private var header: String = ""
     private var child: ArrayList<DataItem>? =null

    fun getHeader(): String {
        return header
    }

    fun setHeader(header: String) {
        this.header = header
    }

    fun getDataItem(): ArrayList<DataItem> {
        return child!!
    }

    fun setDataItem(child: ArrayList<DataItem>) {
        this.child = child
    }
}