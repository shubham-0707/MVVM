package com.shubham.mvvm_github

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.shubham.mvvm_github.fragments.UserFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startUserFragment()
    }

    private fun startUserFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, UserFragment(), UserFragment.TAG)
        transaction.commit()
    }
}