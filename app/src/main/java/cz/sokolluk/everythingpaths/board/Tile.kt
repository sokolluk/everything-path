package cz.sokolluk.everythingpaths.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Tile (var state: TileState = TileState.Empty) {

    @Composable
    fun TileComposable(newState: TileState, tileSize: Int) {
        this.state = newState
        Box(modifier = Modifier.size(tileSize.dp).background(state.color))
    }
}