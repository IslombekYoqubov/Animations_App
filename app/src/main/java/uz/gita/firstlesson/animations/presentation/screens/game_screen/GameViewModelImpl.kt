package uz.gita.firstlesson.animations.presentation.screens.game_screen

import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.animations.data.repository.AppRepository
import uz.gita.firstlesson.animations.data.repository.impl.AppRepositoryImpl

class GameViewModelImpl : ViewModel(), GameViewModel {
    override var images: MutableList<Int> = mutableListOf()
    override var opened: MutableList<Int> = mutableListOf()
    override var cnt: Int = 0
    private val repository: AppRepository by lazy { AppRepositoryImpl.getInstance() }

    override fun loadImagesByLevel(level: Int): List<Int> {
        if (images.isEmpty()) {
            images.addAll(repository.getImagesByLevel(level))
        }
        return images
    }

    override fun restart(level: Int): List<Int> {
        images.clear()
        opened.clear()
        cnt = 0
        images.addAll(repository.getImagesByLevel(level))
        return images
    }
}
