package com.whty.xzfpos.utils.RxBus.RxBusEvent

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/11/24
 * Time: 16:33
 */
open class BaseEvent<T> {
    var from = ""
    var entity: T

    constructor(from: String, entity: T) {
        this.from = from
        this.entity = entity
    }
}