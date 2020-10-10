package kz.kamadi.androidtest.presentation.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    private val bottomReachListener: (() -> Unit)
) : RecyclerView.OnScrollListener() {

    var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
        if (dy > 0 && layoutManager != null) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount && !isLoading) {
                bottomReachListener.invoke()
            }
        }
    }
}