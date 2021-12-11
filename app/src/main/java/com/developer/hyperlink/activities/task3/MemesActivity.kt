package com.developer.hyperlink.activities.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.developer.hyperlink.activities.task1.LoginActivity
import com.developer.hyperlink.activities.task2.SecondTaskActivity
import com.developer.hyperlink.databinding.ActivityMemesBinding
import com.developer.hyperlink.viewmodels.MemesViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemesActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityMemesBinding.inflate(layoutInflater) }
    private val memesViewModel: MemesViewModel by viewModels()
    private val memesAdapter by lazy { MemesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        memesViewModel.callApiFromViewModel()

        mBinding.rvMemes.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = memesAdapter
        }

        memesViewModel.response.observe(this) {
            Log.d(SecondTaskActivity.TAG, Gson().toJson(it))
            memesAdapter.setData(it.memesData.memes)
        }

    }
}