package cz.sokolluk.everythingpaths.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.sokolluk.everythingpaths.board.Game
import cz.sokolluk.everythingpaths.board.generator.BinaryTreeGenerator


class MainActivity: ComponentActivity() {

    val game by lazy {
        Game(Game.WindowProps.getFromActivity(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            game.InitGame(BinaryTreeGenerator())
        }
    }
}