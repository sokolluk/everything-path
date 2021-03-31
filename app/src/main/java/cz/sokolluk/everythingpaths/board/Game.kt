package cz.sokolluk.everythingpaths.board

import android.app.Activity
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.sokolluk.everythingpaths.board.generator.Generator
import cz.sokolluk.everythingpaths.extension.disposeIfAbleTo
import cz.sokolluk.everythingpaths.extension.pxToDp
import cz.sokolluk.everythingpaths.structure.Matrix
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable

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

    enum class Size(val w: Int, val h: Int) {
        Small(16, 16),
        Medium(26, 26),
        Large(40, 40);
    }

    var tiles = Matrix(Size.Medium.w, Size.Medium.h, Tile())

    var generator: Generator? = null
    var generatorSubscription: Disposable? = null

    fun setUpGenerator(generator: Generator) {
        this.generator = generator

        generatorSubscription?.disposeIfAbleTo()

        generatorSubscription = generator
            .updatePublisher
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tileCell ->
                val (i, j, tileState) = tileCell

                tiles.set(i, j, Tile(tileState))
            }, {

            })
    }

    @Composable
    fun InitGame(generator: Generator) {
        val size = Size.Medium

        val windowWidth = windowProps.width
        val tileSize = windowWidth / size.w

        setUpGenerator(generator)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(windowWidth.pxToDp.dp)) {
                Board(tiles, tileSize)
            }
        }
    }


    @Composable
    fun Board(tiles: Matrix<Tile>, tileSize: Int) {
        Row {
            (0 until tiles.height).forEach { i ->
                Column {
                    tiles.get(i).forEach { tile ->
                        tile.data.TileComposable(tile.data.state, tileSize)
                    }
                }
            }
        }
    }

//    fun generate(generator: Generator, rootView: FrameLayout) {
//        generator.initialize(board)
//
//        generator.updatePublisher
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe ({ updatedTile ->
//                    board.tiles?.get(updatedTile.i, updatedTile.j)?.data?.changeState(updatedTile.tileState)
//                    rootView.invalidate()
//                }, { error ->
//                    Log.e("TILEERROR", "is: ", error)
//                })
//
//        generator.compute()
//    }
}