package uz.gita.firstlesson.animations.presentation.screens.game_screen

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.gita.firstlesson.animations.R
import uz.gita.firstlesson.animations.databinding.GameFragmentBinding

class GameScreenOrg : Fragment() {
    private var _binding : GameFragmentBinding? = null
    private var cnt = 0
    private var isOpen = false
    private val binding get() = _binding!!
    private var firstSelected: ImageView? = null
    private val viewModel : GameViewModel by viewModels<GameViewModelImpl>()
    private val level by lazy { arguments?.getInt("KEY") }
    private var secondSelected: ImageView? = null
    private var len : Int = 0
    private var images : MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        images.addAll(viewModel.loadImagesByLevel(level?:3))
        len = 0
        when(level){
            3 ->{
                binding.textLevel.text = "Easy"
                len = 16
                binding.grid.rowCount = 4
                binding.grid.columnCount = 4
            }
            4 -> {
                len = 24
                binding.grid.rowCount = 6
                binding.grid.columnCount = 4
                binding.textLevel.text = "Medium"
            }
            5 -> {
                binding.textLevel.text = "Hard"
                len = 28
                binding.grid.rowCount = 7
                binding.grid.columnCount = 4
            }
        }
        draw()
        loadActions()
    }

    private fun loadActions() {
        binding.backBtn.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.restartBtn.setOnClickListener {
            cnt = 0
            draw()
        }
    }

    private fun draw() {
        binding.grid.removeAllViews()
        images.shuffle()
        for (i in 0 until len) {
            val img = ImageView(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                setPadding(4, 4, 4, 4)
                setImageResource(R.drawable.img)
                setBackgroundResource(R.drawable.bar_bg)
                scaleType = ImageView.ScaleType.FIT_CENTER
                id = View.generateViewId()
            }

            img.setOnClickListener {
                onImageClicked(img, images[i])
            }

            binding.grid.addView(img)
        }
    }

    private fun onImageClicked(img: ImageView, imageRes: Int) {
        if (firstSelected == null) {
            firstSelected = img
            flipToFront(img, imageRes)
        } else if (secondSelected == null && img != firstSelected) {
            secondSelected = img
            flipToFront(img, imageRes)
            Handler(Looper.getMainLooper()).postDelayed({
                if (firstSelected?.tag == secondSelected?.tag) {
                    firstSelected!!.isClickable = false
                    secondSelected!!.isClickable = false
                    cnt += 2
                    Log.d("TTT", "$cnt")
                    if(cnt == len) showDialog()
                } else {
                    flipToBack(firstSelected!!)
                    flipToBack(secondSelected!!)
                }
                firstSelected = null
                secondSelected = null
            }, 1000)
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.win_dialog, null)
        view.findViewById<TextView>(R.id.dialog_text).text = "You won the ${binding.textLevel.text} level !!"
        val dialog = AlertDialog.Builder(requireContext()).setView(view).setCancelable(false).create()
        view.findViewById<TextView>(R.id.back).setOnClickListener {
            parentFragmentManager.popBackStack()
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.restart).setOnClickListener {
            cnt = 0
            draw()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun flipToFront(img: ImageView, resId: Int) {
        val hideAnim = ObjectAnimator.ofFloat(img, "rotationY", 0f, 90f).apply { duration = 200 }
        val showAnim = ObjectAnimator.ofFloat(img, "rotationY", 270f, 360f).apply { duration = 200 }
        hideAnim.addListener(onEnd = {
            img.setImageResource(resId)
            img.rotationY = 270f
            showAnim.start()
        })
        hideAnim.start()

        img.tag = resId
    }

    private fun flipToBack(img: ImageView) {
        val hideAnim = ObjectAnimator.ofFloat(img, "rotationY", 0f, 90f).apply { duration = 200 }
        val showAnim = ObjectAnimator.ofFloat(img, "rotationY", 270f, 360f).apply { duration = 200 }

        hideAnim.addListener(onEnd = {
            img.setImageResource(R.drawable.img)
            img.rotationY = 270f
            showAnim.start()
        })
        hideAnim.start()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}