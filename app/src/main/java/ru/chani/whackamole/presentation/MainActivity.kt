package ru.chani.whackamole.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import ru.chani.whackamole.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        hideSystemBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchStartMenuFragment()

    }

    private fun launchStartMenuFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, StartMenuFragment.newInstance())
            .commit()
    }


    private fun hideSystemBar() {
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) actionBar.hide()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}