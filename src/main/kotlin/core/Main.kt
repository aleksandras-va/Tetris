package core

fun main() {
    Window().startGame()
}

// TODO: [bottom collisions]
// TODO: more pieces


/*
src
│
└───main
    │
    └───kotlin
        │
        └───com
            │
            └───yourdomain
                │
                └───tetris
                    │
                    ├───core
                    │   ├───Game.kt            // Contains the main game logic and state
                    │   ├───Main.kt            // Entry point of the application
                    │   └───Window.kt          // Handles the game window, JFrame setup
                    │
                    ├───entities
                    │   ├───Entity.kt          // Defines the Entity class (e.g., Tetrimino or play area)
                    │   ├───PieceFactory.kt    // Generates random Tetrimino pieces
                    │   └───Coordinates.kt     // Optional: A data class to represent row and column coordinates
                    │
                    ├───input
                    │   └───KeyListenerImpl.kt // Custom KeyListener implementation with key bindings
                    │
                    ├───ui
                    │   ├───Panel.kt           // Custom JPanel for drawing the game state
                    │   └───Colors.kt          // Utility functions for color mapping or custom colors
                    │
                    └───utils
                        ├───CollisionDetector.kt  // Handles collision detection between pieces and play area
                        └───RotationHelper.kt     // Handles Tetrimino piece rotation logic

* */