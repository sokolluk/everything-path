package cz.sokolluk.everythingpaths.board

import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import cz.sokolluk.everythingpaths.view.TileView

class Board {

    enum class Size(val w: Int, val h: Int) {
        Small(16, 16),
        Medium(26, 26),
        Large(40, 40);
    }

    var size = Size.Small

    var board: Array<Array<Tile>>? = null

    fun resize(size: Size) {
        if (this.size != size) {
            this.size = size
        }
        createBoard()
    }

    private fun createBoard() {
        board = (0 until size.h).map { i -> (0 until size.w).map {
            Tile(
                    if (i == 0 || it == 0 || i == size.h - 1 || it == size.w - 1)
                        TileState.Wall
                    else
                        TileState.Empty
            )
        }.toTypedArray() }.toTypedArray()
    }

    fun generateTiles(windowProps: Game.WindowProps, rootView: FrameLayout) {
        val rootViewSize = windowProps.width - 40
        val tileSize = rootViewSize / size.w

        rootView.updateLayoutParams<ConstraintLayout.LayoutParams> {
            width = rootViewSize
            height = rootViewSize
        }

        board?.forEachIndexed { i, row ->
            row.forEachIndexed { j, tile ->
                addTileView(rootView, i, j, tile, tileSize)
            }
        }

        rootView.invalidate()
    }

    private fun addTileView(rootView: FrameLayout, i: Int, j: Int, tile: Tile, tileSize: Int) {
        val newTileView = TileView(rootView.context).apply {
            setBackgroundResource(tile.state.color)
        }
        rootView.addView(newTileView)
        newTileView.updateLayoutParams<FrameLayout.LayoutParams> {
            setMargins(i * tileSize, j * tileSize, 0, 0)
        }
    }

}