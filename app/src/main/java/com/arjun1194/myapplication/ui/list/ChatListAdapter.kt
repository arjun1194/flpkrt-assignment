package com.arjun1194.myapplication.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arjun1194.myapplication.R
import com.arjun1194.myapplication.data.model.ChatListResponse
import com.arjun1194.myapplication.data.model.ChatListResponseItem
import com.arjun1194.myapplication.databinding.ChatListItemBinding
import com.bumptech.glide.Glide

class ChatListAdapter : RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    private val items: MutableList<ChatListResponseItem> = ArrayList()

    inner class ViewHolder(private val binding: ChatListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatListResponseItem) {
            binding.title.text = item.title
            binding.description.text = "Order ${item.orderId}"
            binding.time.text = item.latestMessageTimestamp.toString()
            Glide.with(binding.root.context).load(item.imageURL).into(binding.imageView)
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(
                        R.id.navigation_chat_detail,
                        bundleOf("id" to item.id)
                    )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)
        val binding = ChatListItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(it: ChatListResponse) {
        items.addAll(it)
        notifyDataSetChanged()
    }
}