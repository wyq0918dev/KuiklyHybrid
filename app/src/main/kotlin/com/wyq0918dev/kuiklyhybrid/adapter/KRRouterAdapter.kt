package com.wyq0918dev.kuiklyhybrid.adapter

import android.content.Context
import com.tencent.kuikly.core.render.android.adapter.IKRRouterAdapter
import org.json.JSONObject

object KRRouterAdapter : IKRRouterAdapter {

    //    override fun openPage(
//        context: Context,
//        pageName: String,
//        pageData: JSONObject,
//    ) {
//        KuiklyRenderActivity.start(context, pageName, pageData)
//    }
//
//    override fun closePage(context: Context) {
//        (context as? Activity)?.finish()
//    }
    override fun openPage(
        context: Context,
        pageName: String,
        pageData: JSONObject
    ) = Unit

    override fun closePage(context: Context) = Unit
}