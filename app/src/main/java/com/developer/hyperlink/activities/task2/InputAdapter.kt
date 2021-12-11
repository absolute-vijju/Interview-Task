package com.developer.hyperlink.activities.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.hyperlink.databinding.ItemInputBinding

class InputAdapter(val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<InputAdapter.InputViewHolder>() {

    private var inputData = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputViewHolder {
        return InputViewHolder(
            ItemInputBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InputViewHolder, position: Int) {
        holder.itemInputBinding.apply {
            tvInput.text = inputData[position]

            cbxInput.isChecked = false

            cbxInput.setOnClickListener {
                onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return inputData.size
    }

    fun setData(inputData: List<String>) {
        this.inputData = inputData
        notifyDataSetChanged()
    }

    inner class InputViewHolder(val itemInputBinding: ItemInputBinding) :
        RecyclerView.ViewHolder(itemInputBinding.root)
}