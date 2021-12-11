package com.developer.hyperlink.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.hyperlink.activities.task1.LoginActivity
import com.developer.hyperlink.activities.task2.SecondTaskActivity
import com.developer.hyperlink.activities.task3.MemesActivity
import com.developer.hyperlink.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.btnTask1.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        mBinding.btnTask2.setOnClickListener {
            startActivity(Intent(this, SecondTaskActivity::class.java))
        }

        mBinding.btnTask3.setOnClickListener {
            startActivity(Intent(this, MemesActivity::class.java))
        }

    }
}