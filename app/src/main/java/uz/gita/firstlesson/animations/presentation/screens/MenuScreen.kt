package uz.gita.firstlesson.animations.presentation.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.gita.firstlesson.animations.R
import uz.gita.firstlesson.animations.presentation.screens.game_screen.GameScreenOrg

class MenuScreen : Fragment(R.layout.menu_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.easy).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("KEY", 3)
            val fr = GameScreenOrg()
            fr.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, fr)
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<TextView>(R.id.medium).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("KEY", 4)
            val fr = GameScreenOrg()
            fr.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, fr)
                .addToBackStack(null)
                .commit()
        }
        view.findViewById<TextView>(R.id.hard).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("KEY", 5)
            val fr = GameScreenOrg()
            fr.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.myContainer, fr)
                .addToBackStack(null)
                .commit()
        }
    }
}