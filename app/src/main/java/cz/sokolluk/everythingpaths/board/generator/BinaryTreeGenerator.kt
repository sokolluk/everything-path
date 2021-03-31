package cz.sokolluk.everythingpaths.board.generator

import cz.sokolluk.everythingpaths.board.TileState
import kotlin.random.Random

class BinaryTreeGenerator(): Generator() {

    enum class Choice {
        North,
        West
    }

    override fun compute() {
        matrix?.forEachIndexed { i, j, tileState ->

            if (i % 2 == 0 || j % 2 == 0) return@forEachIndexed

            update(i, j, TileState.Empty)

            when(makeChoice()) {
                Choice.North -> {
                    if (i - 1 > 0) {
                        update(i - 1, j, TileState.Empty)
                    }
                }
                Choice.West -> {
                    if (j - 1 > 0) {
                        update(i, j - 1, TileState.Empty)
                    }
                }
            }
        }
    }

    private fun isBoundaryWall(i: Int, j: Int): Boolean {
        val h = matrix?.height ?: return false
        val w = matrix?.width ?: return false
        return i == 0 || j == 0 || i == h - 1 || j == w - 1
    }

    private fun makeChoice(): Choice {
        return if (Random.nextInt(2) == 0) Choice.North else Choice.West
    }

}