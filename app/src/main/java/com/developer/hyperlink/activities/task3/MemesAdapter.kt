package com.developer.hyperlink.activities.task3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer.hyperlink.databinding.ItemMemeBinding
import com.developer.hyperlink.models.Meme

class MemesAdapter : RecyclerView.Adapter<MemesAdapter.MemeViewHolder>() {

    private var memeList = arrayListOf<Meme>()

    inner class MemeViewHolder(val mBinding: ItemMemeBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(
            ItemMemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memeList[position]
        holder.mBinding.apply {
            Glide.with(root.context).load(meme.url).into(ivMeme)
            tvName.text = meme.name
        }
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    fun setData(memeList: List<Meme>) {
        this.memeList = memeList as ArrayList<Meme>
        notifyDataSetChanged()
    }
}