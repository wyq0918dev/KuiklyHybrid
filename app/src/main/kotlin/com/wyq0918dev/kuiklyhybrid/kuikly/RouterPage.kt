package com.wyq0918dev.kuiklyhybrid.kuikly

import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.Attr
import com.tencent.kuikly.core.base.Color
import com.tencent.kuikly.core.base.DeclarativeBaseView
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.base.ViewContainer
import com.tencent.kuikly.core.base.event.Event
import com.tencent.kuikly.core.views.Image
import com.tencent.kuikly.core.views.View
import com.wyq0918dev.kuiklyhybrid.kuikly.base.BasePager

@Page(name = "router")
class RouterPage : BasePager() {

    override fun body(): ViewBuilder {
        return {
            attr {
                backgroundColor(Color.GRAY)
            }
            View {
                attr {
                    allCenter()
                    margin(16f)
                }
                View {
                    attr {
                        backgroundColor(Color.WHITE)
                        borderRadius(10f)
                        padding(10f)
                    }
                    Image {
                        attr {
                            src("https://vfiles.gtimg.cn/wuji_dashboard/wupload/xy/starter/62394e19.png")
                            size(
                                pagerData.pageViewWidth * 0.6f,
                                (pagerData.pageViewWidth * 0.6f) * (1987f / 2894f)
                            )
                        }
                    }
                }

            }
            flutterView {
                attr {
                    flex(1f)
                    margin(16f)
                    borderRadius(allBorderRadius = 12f)
                }
            }
        }
    }
}


class FlutterView : DeclarativeBaseView<FlutterAttr, FlutterEvent>() {
    override fun createAttr(): FlutterAttr = FlutterAttr()
    override fun createEvent(): FlutterEvent = FlutterEvent()
    override fun viewName(): String = "flutterView"
}


class FlutterAttr : Attr()

class FlutterEvent : Event()

fun ViewContainer<*, *>.flutterView(init: FlutterView.() -> Unit) {
    addChild(FlutterView(), init)
}
