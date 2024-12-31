package com.example.create_account

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class FragmentSwitchActivity : AppCompatActivity() {

    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_switch)

        // Initially display FragmentA
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, FragmentA())
        }

        // Switch fragment on button click
        findViewById<Button>(R.id.switchButton).setOnClickListener {
            val fragment: Fragment = if (showingFragmentA) {
                FragmentB()
            } else {
                FragmentA()
            }
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, fragment)
            }
            showingFragmentA = !showingFragmentA
        }
    }
}
