package kz.kamadi.androidtest.presentation

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kz.kamadi.androidtest.R
import kz.kamadi.androidtest.presentation.user.UsersFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, UsersFragment())
                .commit()
        }
    }
}