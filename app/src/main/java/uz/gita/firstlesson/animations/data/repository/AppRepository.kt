package uz.gita.firstlesson.animations.data.repository

interface AppRepository {
    fun getImagesByLevel(level : Int) : List<Int>
}