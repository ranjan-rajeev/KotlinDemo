package com.horizonlabs.kotlindemo.adapters

import androidx.recyclerview.widget.DiffUtil
import com.horizonlabs.kotlindemo.model.ChatEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 30-08-2019.
 */
class ChatDiffCallBack(internal var newChat: List<ChatEntity>, internal var oldChat: List<ChatEntity>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldChat.size
    }

    override fun getNewListSize(): Int {
        return newChat.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChat.get(oldItemPosition).chatId === newChat.get(newItemPosition).chatId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldChat.get(oldItemPosition) == newChat.get(newItemPosition)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}