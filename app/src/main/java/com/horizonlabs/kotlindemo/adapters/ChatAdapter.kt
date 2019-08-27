package com.horizonlabs.kotlindemo.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.utility.Constants
import java.util.*


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class ChatAdapter(internal var context: Context?) : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    private var itemClick: ItemClick? = null
    internal var chatEntities: List<ChatEntity>? = ArrayList<ChatEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ChatHolder {
        val itView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_chat, viewGroup, false)
        return ChatHolder(itView)
    }

    override fun onBindViewHolder(holder: ChatHolder, i: Int) {
        val userEntity = chatEntities?.get(i)
        userEntity?.let {
            if (it.chatType == Constants.CHAT_RECEIVED) {
                holder.msgReceived.setText(it.chatDetails)
                holder.msgSent.visibility = View.GONE
                holder.msgReceived.visibility = View.VISIBLE

            } else if (it.chatType == Constants.CHAT_SENT) {
                holder.msgReceived.visibility = View.GONE
                holder.msgSent.visibility = View.VISIBLE
                holder.msgSent.setText(it.chatDetails)

            }

        }

    }

    override fun getItemCount(): Int {
        return chatEntities?.size ?: 0
    }

    inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var msgReceived: TextView
        internal var msgSent: TextView

        init {
            msgReceived = itemView.findViewById(R.id.msgReceived)
            msgSent = itemView.findViewById(R.id.msgSent)
        }
    }

    fun setChatEntities(userEntities: List<ChatEntity>?) {
        this.chatEntities = userEntities
        notifyDataSetChanged()
    }

    interface ItemClick {
        fun onItemClick(chatEntity: ChatEntity)

        fun onFavouriteClick(chatEntity: ChatEntity)
    }

    fun setOnItemClickListener(listener: ItemClick) {
        this.itemClick = listener
    }
}
