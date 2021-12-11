package com.developer.hyperlink.activities.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.hyperlink.utils.showToast
import com.developer.hyperlink.databinding.ActivitySecondTaskBinding
import com.google.gson.Gson

class SecondTaskActivity : AppCompatActivity() {

    companion object {
        const val TAG = "mydata"
    }

    private val mBinding by lazy { ActivitySecondTaskBinding.inflate(layoutInflater) }
    private val leftAdapter by lazy {
        InputAdapter { position ->
            if (!leftSideSelectedItemPositionList.contains(position))
                leftSideSelectedItemPositionList.add(position)
            else
                leftSideSelectedItemPositionList.remove(position)

            Log.d(TAG, Gson().toJson(leftSideSelectedItemPositionList))
        }
    }
    private val rightAdapter by lazy {
        InputAdapter { position ->
            if (!rightSideSelectedItemPositionList.contains(position))
                rightSideSelectedItemPositionList.add(position)
            else
                rightSideSelectedItemPositionList.remove(position)

            Log.d(TAG, Gson().toJson(rightSideSelectedItemPositionList))
        }
    }

    private val leftList = arrayListOf<String>()
    private val rightList = arrayListOf<String>()

    private val rightSideSelectedItemPositionList = arrayListOf<Int>()
    private val leftSideSelectedItemPositionList = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        init()

//        dummyData()

        mBinding.btnAdd.setOnClickListener {
            val input = mBinding.etInput.text.toString()

            if (input.isEmpty()) {
                showToast("Please enter something.")
                return@setOnClickListener
            }

            rightList.add(input)
            setData()
            clearOperation()

        }

        mBinding.btnDelete.setOnClickListener {
            val input = mBinding.etInput.text.toString()

            if (input.isEmpty()) {
                showToast("Please enter something.")
                return@setOnClickListener
            }

            rightList.remove(element = input)
            leftList.remove(element = input)
            setData()
            clearOperation()

        }

        mBinding.btnCopyLeftToRight.setOnClickListener {
            if (leftSideSelectedItemPositionList.isEmpty()) {
                showToast("Please select at least one item.")
                return@setOnClickListener
            }

            for (i in leftSideSelectedItemPositionList) {

                val element = leftList[i]

                if (!rightList.contains(element))
                    rightList.add(element)

            }

            leftSideSelectedItemPositionList.clear()
            setData()
        }

        mBinding.btnCopyRightToLeft.setOnClickListener {
            if (rightSideSelectedItemPositionList.isEmpty()) {
                showToast("Please select at least one item.")
                return@setOnClickListener
            }

            for (i in rightSideSelectedItemPositionList) {

                val element = rightList[i]

                if (!leftList.contains(element))
                    leftList.add(element)

            }

            rightSideSelectedItemPositionList.clear()
            setData()
        }

        mBinding.btnMoveLeftToRight.setOnClickListener {
            if (leftSideSelectedItemPositionList.isEmpty()) {
                showToast("Please select at least one item.")
                return@setOnClickListener
            }

            for (i in leftSideSelectedItemPositionList) {
                val element = leftList[i]
                if (!rightList.contains(element))
                    rightList.add(element)

            }

            for (i in leftSideSelectedItemPositionList.size - 1 downTo 0) {
                leftList.remove(leftList[leftSideSelectedItemPositionList[i]])
            }

            leftSideSelectedItemPositionList.clear()
            setData()
        }

        mBinding.btnMoveRightToLeft.setOnClickListener {
            if (rightSideSelectedItemPositionList.isEmpty()) {
                showToast("Please select at least one item.")
                return@setOnClickListener
            }

            for (i in rightSideSelectedItemPositionList) {
                val element = rightList[i]
                if (!leftList.contains(element))
                    leftList.add(element)

            }

            for (i in rightSideSelectedItemPositionList.size - 1 downTo 0) {
                rightList.remove(rightList[rightSideSelectedItemPositionList[i]])
            }


            rightSideSelectedItemPositionList.clear()
            setData()
        }

        mBinding.btnSwap.setOnClickListener {
            val leftSideTempList = arrayListOf<String>()
            val rightSideTempList = arrayListOf<String>()

            if (rightSideSelectedItemPositionList.isEmpty() || leftSideSelectedItemPositionList.isEmpty()) {
                showToast("Selected at least one item.")
                return@setOnClickListener
            }

            if (leftSideSelectedItemPositionList.size != rightSideSelectedItemPositionList.size) {
                showToast("Selected item count not matching.")
                return@setOnClickListener
            }

            for (i in leftSideSelectedItemPositionList.size - 1 downTo 0) {
                leftSideTempList.add(leftList[leftSideSelectedItemPositionList[i]])
                leftList.remove(leftList[leftSideSelectedItemPositionList[i]])
            }

            for (i in rightSideSelectedItemPositionList.size - 1 downTo 0) {
                rightSideTempList.add(rightList[rightSideSelectedItemPositionList[i]])
                rightList.remove(rightList[rightSideSelectedItemPositionList[i]])
            }

            for (index in leftSideSelectedItemPositionList.indices) {
                val leftElement = leftSideTempList[leftSideTempList.size - 1 - index]
                val position = leftSideSelectedItemPositionList[index]
                rightList.add(position, leftElement)
            }
            for (index in rightSideSelectedItemPositionList.indices) {
                val rightElement = rightSideTempList[leftSideTempList.size - 1 - index]
                val position = rightSideSelectedItemPositionList[index]
                leftList.add(position, rightElement)
            }

            leftSideSelectedItemPositionList.clear()
            rightSideSelectedItemPositionList.clear()
            setData()
        }

    }

    private fun setData() {
        leftAdapter.setData(leftList)
        rightAdapter.setData(rightList)
    }

    private fun init() {
        mBinding.apply {
            rvLeft.layoutManager = LinearLayoutManager(applicationContext)
            rvRight.layoutManager = LinearLayoutManager(applicationContext)
            rvLeft.setHasFixedSize(true)
            rvRight.setHasFixedSize(true)
            rvLeft.adapter = leftAdapter
            rvRight.adapter = rightAdapter
        }
        leftAdapter.setData(leftList)
        rightAdapter.setData(rightList)
    }

    private fun clearOperation() {
        mBinding.etInput.setText("")
    }

    private fun dummyData() {
        rightList.add("A")
        rightList.add("B")
        rightList.add("C")
        rightList.add("D")
        rightList.add("E")
        rightList.add("F")
        leftList.add("A")
        leftList.add("B")
        leftList.add("I")
        leftList.add("J")
        leftList.add("K")
        leftList.add("L")
        rightAdapter.setData(rightList)
        leftAdapter.setData(leftList)
    }
}