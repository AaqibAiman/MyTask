package com.app.mytask

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.mytask.ui.dashboard.DashboardFragment
import com.app.mytask.ui.home.HomeFragment
import com.app.mytask.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)


        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        startFragment(HomeFragment(), null, "HomeFragment")
        navView.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment = HomeFragment()
            var tag = "HomeFragment"
            when(item.itemId) {
                R.id.navigation_home -> {
                    fragment = HomeFragment()
                    tag =  "HomeFragment"
                }
                R.id.navigation_dashboard -> {
                    fragment = DashboardFragment()
                    tag =  "DashboardFragment"
                }
                R.id.navigation_notifications -> {
                    fragment = NotificationsFragment()
                    tag =  "NotificationsFragment"
                }
            }

            startFragment(fragment, null, tag)
        }
    }

    private fun startFragment(fragment: Fragment, bundle: Bundle?, tag: String): Boolean {
        if (isSameFragmentAttached(tag)) {
            val transaction = supportFragmentManager.beginTransaction()
            fragment.arguments = bundle

            transaction.replace(R.id.nav_host_fragment, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        }
        return true
    }

    private fun isSameFragmentAttached(tag: String): Boolean {
        val fragmentList = supportFragmentManager.fragments
        if (fragmentList.size > 0) {
            return fragmentList[fragmentList.size - 1].tag != tag
        }
        return true
    }
}