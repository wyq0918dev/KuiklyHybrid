package com.wyq0918dev.kuiklyhybrid

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.tencent.kuikly.core.render.android.IKuiklyRenderExport
import com.tencent.kuikly.core.render.android.adapter.KuiklyRenderAdapterManager
import com.tencent.kuikly.core.render.android.expand.KuiklyRenderViewBaseDelegator
import com.tencent.kuikly.core.render.android.expand.KuiklyRenderViewBaseDelegatorDelegate
import com.wyq0918dev.kuiklyhybrid.adapter.KRColorParserAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRFontAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRImageAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRLogAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRRouterAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRThreadAdapter
import com.wyq0918dev.kuiklyhybrid.adapter.KRUncaughtExceptionHandlerAdapter
import com.wyq0918dev.kuiklyhybrid.module.KRBridgeModule
import com.wyq0918dev.kuiklyhybrid.module.KRShareModule
import com.wyq0918dev.kuiklyhybrid.ui.theme.KuiklyHybridTheme
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode

class KuiklyRenderActivity : AppCompatActivity(), KuiklyRenderViewBaseDelegatorDelegate {

    private val kuiklyRenderViewDelegator = KuiklyRenderViewBaseDelegator(
        delegate = this@KuiklyRenderActivity,
    )

    private var mFlutterFragment: FlutterFragment? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mFlutterFragment = FlutterFragment.withNewEngine().renderMode(RenderMode.texture).build()


        val hrContainerView = FrameLayout(this@KuiklyRenderActivity)
        try {
            kuiklyRenderViewDelegator.onAttach(
                container = hrContainerView,
                contextCode = "",
                pageName = "router",
                pageData = createPageData(),
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            KuiklyHybridTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Jetpack Compose") },
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            Text("BottomAppBar")
                        }
                    }
                ) { innerPadding ->
                    AndroidView(
                        factory = { hrContainerView },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        mFlutterFragment?.onPostResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        mFlutterFragment?.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mFlutterFragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mFlutterFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        mFlutterFragment?.onUserLeaveHint()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        mFlutterFragment?.onTrimMemory(level)
    }

    override fun onDestroy() {
        super.onDestroy()
        mFlutterFragment = null
        try {
            kuiklyRenderViewDelegator.onDetach()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            kuiklyRenderViewDelegator.onPause()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            kuiklyRenderViewDelegator.onResume()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun registerExternalModule(kuiklyRenderExport: IKuiklyRenderExport) {
        super.registerExternalModule(kuiklyRenderExport = kuiklyRenderExport)
        with(receiver = kuiklyRenderExport) {
            moduleExport(name = KRBridgeModule.MODULE_NAME) {
                KRBridgeModule()
            }
            moduleExport(name = KRShareModule.MODULE_NAME) {
                KRShareModule()
            }
        }
    }

    override fun registerExternalRenderView(kuiklyRenderExport: IKuiklyRenderExport) {
        super.registerExternalRenderView(kuiklyRenderExport)
        with(receiver = kuiklyRenderExport) {
            renderViewExport(
                viewName = "flutterView",
                renderViewExportCreator = { context ->
                    HybridWrapperView(
                        context = this@KuiklyRenderActivity,
                        flutter = mFlutterFragment,
                    )
                },
            )
        }
    }

    private fun createPageData(): Map<String, Any> {
        val param = mutableMapOf<String, Any>()
        param["appId"] = 1
        return param
    }

    companion object {

        init {
            initKuiklyAdapter()
        }

        private fun initKuiklyAdapter() {
            with(KuiklyRenderAdapterManager) {
                krImageAdapter = KRImageAdapter(KRApplication.application)
                krLogAdapter = KRLogAdapter
                krUncaughtExceptionHandlerAdapter = KRUncaughtExceptionHandlerAdapter
                krFontAdapter = KRFontAdapter
                krColorParseAdapter = KRColorParserAdapter(KRApplication.application)
                krRouterAdapter = KRRouterAdapter
                krThreadAdapter = KRThreadAdapter()
            }
        }
    }
}