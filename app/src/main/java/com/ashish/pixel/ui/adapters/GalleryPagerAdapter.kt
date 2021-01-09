package com.ashish.pixel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.pixel.data.models.PixelImage
import com.ashish.pixel.data.models.PixelImages
import com.ashish.pixel.databinding.ListItemGalleryBinding
import com.ashish.pixel.utils.load

class GalleryPagerAdapter(
    private val onItemClicked: (PixelImage) -> Unit,
    private val images: MutableList<PixelImage> = mutableListOf()
): RecyclerView.Adapter<GalleryPagerAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], onItemClicked)
    }

    fun submitList(list: PixelImages) {
        this.images.clear()
        this.images.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: PixelImage, onItemClicked: (PixelImage) -> Unit) {
            binding.imageView.setOnClickListener {
                onItemClicked(image)
            }

            binding.imageView.load(image.url)
        }
    }
}