package ro.vcv.intheaters.movies.helper

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager

class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: GridLayoutManager) : NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(view: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (view!!.getChildAt(view.childCount - 1) != null) {
            if ((scrollY >= (view.getChildAt(view.childCount - 1).measuredHeight - view.measuredHeight)) && scrollY > oldScrollY) {
                func()
            }
        }
    }
}