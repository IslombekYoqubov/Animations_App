package uz.gita.firstlesson.animations.presentation.screens.game_screen


interface GameViewModel {
    var images: MutableList<Int>
    var opened: MutableList<Int>
    var cnt: Int
    fun loadImagesByLevel(level: Int) : List<Int>
    fun restart(level: Int) : List<Int>
}