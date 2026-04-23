package com.wyq0918dev.kuiklyhybrid.kuikly.base

import com.tencent.kuikly.core.base.PagerScope
import com.tencent.kuikly.core.timer.setTimeout

/**
 * 老的方式:，需要显式传递 pagerId
 * ```kotlin
 * Utils.bridgeModule(pagerId).reportPageCostTimeForError()
 * ```
 *
 * 新方式：无需显式传递 pagerId
 * ```kotlin
 * bridgeModule.reportPageCostTimeForError()
 * ```
 */
val PagerScope.bridgeModule: BridgeModule
    get() {
        return Utils.bridgeModule(pager = pagerId)
    }

fun PagerScope.setTimeout(delay: Int, callback: () -> Unit): String {
    return setTimeout(pagerId, delay, callback)
}