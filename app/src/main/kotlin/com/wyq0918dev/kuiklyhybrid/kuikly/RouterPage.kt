package com.wyq0918dev.kuiklyhybrid.kuikly

import com.tencent.kuikly.core.annotations.Page
import com.tencent.kuikly.core.base.Color
import com.tencent.kuikly.core.base.ViewBuilder
import com.tencent.kuikly.core.views.Image
import com.tencent.kuikly.core.views.Text
import com.tencent.kuikly.core.views.View
import com.wyq0918dev.kuiklyhybrid.kuikly.base.BasePager

@Page("router")
class RouterPage : BasePager() {

    override fun body(): ViewBuilder {
        return {
            attr {
                backgroundColor(Color.GRAY)
            }
            View {
                attr {
                    allCenter()
                    margin(20f)
                }
                View {
                    attr {
                        backgroundColor(Color.WHITE)
                        borderRadius(10f)
                        padding(10f)
                    }
                    Image {
                        attr {
                            src(LOGO)
                            size(
                                pagerData.pageViewWidth * 0.6f,
                                (pagerData.pageViewWidth * 0.6f) * (1987f / 2894f)
                            )
                        }
                    }
                }

            }

            View {
                attr {
                    flexDirectionColumn()
                }

                Text {
                    attr {
                        text("Flutter")
                    }
                }
            }
        }
    }

    companion object {
        const val LOGO = "https://vfiles.gtimg.cn/wuji_dashboard/wupload/xy/starter/62394e19.png"
    }
}