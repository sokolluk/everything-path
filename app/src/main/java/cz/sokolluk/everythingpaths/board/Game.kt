package cz.sokolluk.everythingpaths.board

import android.app.Activity
import android.util.DisplayMetrics
import android.widget.FrameLayout

class Game(val windowProps: WindowProps) {

    data class WindowProps(val width: Int, val height: Int) {
        companion object {
            fun getFromActivity(activity: Activity): WindowProps {
                val displayMetrics = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
                val height = displayMetrics.heightPixels
                val width = displayMetrics.widthPixels
                return WindowProps(width, height)
            }
        }
    }

    val board by lazy {
        Board()
    }

    fun initialize(rootView: FrameLayout) {
        board.resize(Board.Size.Small)
        board.generateTiles(windowProps, rootView)
    }

}