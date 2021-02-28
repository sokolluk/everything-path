package cz.sokolluk.everythingpaths.board

import cz.sokolluk.everythingpaths.R

enum class TileState(val id: Int, val color: Int) {
    Wall(0, R.color.black),
    Empty(1, R.color.white),
    Selected(2, R.color.purple_200),
    Progress(3, R.color.teal_200),
    Final(4, R.color.purple_500);
}