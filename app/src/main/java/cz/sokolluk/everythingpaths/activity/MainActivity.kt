package cz.sokolluk.everythingpaths.activity

import android.app.Activity
import android.os.Bundle
import cz.sokolluk.everythingpaths.board.Game
import cz.sokolluk.everythingpaths.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: Activity() {

    val game by lazy {
        Game(Game.WindowProps.getFromActivity(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        game.initialize(boardDesk)

        exitButton.setOnClickListener {
            finish()
        }

    }
}