package com.example.climbstation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.climbstation.fragments.ClimbFragment
import com.example.climbstation.fragments.CreateFragment
import com.example.climbstation.fragments.SettingsFragment
import com.example.climbstation.fragments.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_climb.*

class MainActivity : AppCompatActivity() {

    private val climbFragment =ClimbFragment()
    private val createFragment = CreateFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       var user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            startApp()
        }else {

            setContentView(R.layout.login)
            sign_in.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            sign_up.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun useFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    private fun startApp(){
        setContentView(R.layout.activity_main)

        useFragment(climbFragment)
        /*button.setOnClickListener {
            val intent = Intent(this@MainActivity, QRScanActivity::class.java)
            // intent.putExtra("User", user);
            startActivity(intent)
        }*/

        bottom_navigation_menu.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_climb -> useFragment(climbFragment)
                R.id.ic_create -> useFragment(createFragment)
                R.id.ic_statistics -> useFragment(statisticsFragment)
                R.id.ic_settings -> useFragment(settingsFragment)

            }
            true
        }
    }


}


