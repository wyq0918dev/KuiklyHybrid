package com.wyq0918dev.kuiklyhybrid

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.tencent.kuikly.core.render.android.export.IKuiklyRenderViewExport
import io.flutter.embedding.android.FlutterFragment

/**
 * 承载FlutterFragment的View
 */
internal class HybridWrapperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    flutter: FlutterFragment? = null,
) : ViewGroup(context, attrs, defStyleAttr), IKuiklyRenderViewExport {

    /** FragmentActivity */
    private val mFragmentActivity: FragmentActivity

    /** Fragment */
    private val mFlutterFragment: FlutterFragment

    init {
        if (context is FragmentActivity && flutter != null) {
            mFragmentActivity = context
            mFlutterFragment = flutter
        } else error(
            message = "FlutterWrapperView 的父 Activity 必须是 FragmentActivity 或其子类, 并且flutter不为空",
        )
        addView(
            ViewPager2(context).apply {
                tag = FLUTTER_CONTAINER_TAG
            }
        )
    }

    /**
     * 子View添加时调用
     */
    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        if (child?.tag == FLUTTER_CONTAINER_TAG && child is ViewPager2) child.apply {
            isUserInputEnabled = false // 禁用滑动
            adapter = object : FragmentStateAdapter(mFragmentActivity) {
                override fun getItemCount(): Int = 1
                override fun createFragment(position: Int): Fragment = mFlutterFragment
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child.tag == FLUTTER_CONTAINER_TAG) {
                val childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(widthMeasureSpec),
                    MeasureSpec.EXACTLY,
                )
                val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(heightMeasureSpec),
                    MeasureSpec.EXACTLY,
                )
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec)
            }
        }
    }

    /**
     * 布局子View
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        // 遍历子View
        for (index in 0 until childCount) {
            val child: View = getChildAt(index)
            if (child.tag == FLUTTER_CONTAINER_TAG) {
                child.layout(0, 0, right - left, bottom - top)
            }
        }
    }

    /**
     * 伴生对象
     */
    private companion object {
        const val FLUTTER_CONTAINER_TAG: String = "FLUTTER_CONTAINER"
    }
}