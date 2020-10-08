package com.codinginflow.imagesearchapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.data.ResultsItem
import com.codinginflow.imagesearchapp.databinding.ItemUnsplashPhotoBinding

class GalleryAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<ResultsItem, GalleryAdapter.PhotoViewHolder>(
        PHOTO_COMPARATOR
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, position)
        }
    }


    inner class PhotoViewHolder(private val binding: ItemUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }

            }
        }

        fun bind(photo: ResultsItem, place: Int) {
            binding.apply {
                /*when(place){
                    0-> countTextView.isVisible = true
                        else-> countTextView.isVisible = false
                }*/
                countTextView.isVisible = false
                Glide.with(itemView)
                    .load(photo.urls?.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView2)

                textViewUserName.text = photo.user?.username
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: ResultsItem)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem) =
                oldItem == newItem
        }
    }


}