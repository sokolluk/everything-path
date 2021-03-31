package cz.sokolluk.everythingpaths.board.generator

import android.util.Log
import cz.sokolluk.everythingpaths.board.Game
import cz.sokolluk.everythingpaths.board.TileState
import cz.sokolluk.everythingpaths.structure.Matrix
import io.reactivex.rxjava3.subjects.PublishSubject

open class Generator {

    val updatePublisher: PublishSubject<TileCell> = PublishSubject.create()

    var matrix: Matrix<TileState>? = null

    data class TileCell(val i: Int, val j: Int, val tileState: TileState)

    open fun initialize(size: Game.Size) {
        val w = size.w
        val h = size.h

        matrix = Matrix(w, h, TileState.Empty)
    }

    open fun compute() {}

    fun update(i: Int, j: Int, tileState: TileState) {
        Log.e("TILEUPDATE", "[$i][$j] = $tileState")
        matrix?.set(i, j, tileState)
        updatePublisher.onNext(TileCell(i, j, tileState))
    }

}