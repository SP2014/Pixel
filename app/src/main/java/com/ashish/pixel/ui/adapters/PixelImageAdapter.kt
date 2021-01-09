package com.ashish.pixel.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashish.pixel.data.models.PixelImage
import com.ashish.pixel.databinding.ListItemImageBinding
import com.ashish.pixel.utils.loadWithThumbnail

class PixelImageAdapter(private val onItemClicked: (Int) -> Unit):
    ListAdapter<PixelImage, RecyclerView.ViewHolder>(PixelImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PixelImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = getItem(position)
        (holder as PixelImageViewHolder).bind(image, onItemClicked)
    }

    class PixelImageViewHolder(private val binding: ListItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: PixelImage, onItemClicked: (Int) -> Unit) {
            binding.root.setOnClickListener {
                onItemClicked(adapterPosition)
            }

            binding.imageView.loadWithThumbnail(image.url)
        }
    }
}

private class PixelImageDiffCallback : DiffUtil.ItemCallback<PixelImage>() {

    override fun areItemsTheSame(oldItem: PixelImage, newItem: PixelImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PixelImage, newItem: PixelImage): Boolean {
        return oldItem == newItem
    }
}