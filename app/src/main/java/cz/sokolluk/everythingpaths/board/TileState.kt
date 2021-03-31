package cz.sokolluk.everythingpaths.board

import androidx.compose.ui.graphics.Color

enum class TileState(val id: Int, val color: Color) {
    Wall(0, Color.Black),
    Empty(1, Color.White),
    Selected(2, Color(0xFFBB86FC)),
    Progress(3, Color(0xFF03DAC5)),
    Final(4, Color(0xFF018786));
}