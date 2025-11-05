package uz.gita.firstlesson.animations.data.repository.impl

import uz.gita.firstlesson.animations.R
import uz.gita.firstlesson.animations.data.repository.AppRepository

class AppRepositoryImpl private constructor() : AppRepository {
    companion object{
        private var INSTANCE : AppRepositoryImpl? = null
        fun getInstance() : AppRepositoryImpl{
            if(INSTANCE == null) INSTANCE = AppRepositoryImpl()
            return INSTANCE as AppRepositoryImpl
        }
    }

    private val easy = listOf(
        R.drawable.ball, R.drawable.ball,
        R.drawable.img1, R.drawable.img1,
        R.drawable.img2, R.drawable.img2,
        R.drawable.img3, R.drawable.img3,
        R.drawable.img4, R.drawable.img4,
        R.drawable.img5, R.drawable.img5,
        R.drawable.img6, R.drawable.img6,
        R.drawable.img7, R.drawable.img7,
    )

    private val hard = listOf(
        R.drawable.c, R.drawable.css, R.drawable.c_sharp,
        R.drawable.c_plus_plus, R.drawable.php, R.drawable.python,
        R.drawable.flutter, R.drawable.html, R.drawable.go,
        R.drawable.java, R.drawable.js, R.drawable.rust,
        R.drawable.r, R.drawable.kotlin
    )
    private val medium = listOf(
        R.drawable.e1,
        R.drawable.e2,
        R.drawable.e3,
        R.drawable.e4,
        R.drawable.e5,
        R.drawable.e6,
        R.drawable.e7,
        R.drawable.e8,
        R.drawable.e9,
        R.drawable.e10,
        R.drawable.e11,
        R.drawable.e12,
    )
    override fun getImagesByLevel(level: Int): List<Int> {
        val res = mutableListOf<Int>()
        when(level){
            3 -> res.addAll(easy)
            4 -> {
                res.addAll(medium)
                res.addAll(medium)
            }
            5 -> {
                res.addAll(hard)
                res.addAll(hard)
            }
        }
        return res.shuffled()
    }
}