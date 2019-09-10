package com.horizonlabs.kotlindemo.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayout
import com.google.gson.Gson
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.QuestionEntity
import com.horizonlabs.kotlindemo.utility.Logger
import kotlinx.android.synthetic.main.item_textview_flex.view.*


/**
 * Created by Rajeev Ranjan -  ABPB on 01-09-2019.
 */
class ChatViewAdapter(private val context: Context) : RecyclerView.Adapter<ChatViewAdapter.BaseViewHolder<*>>() {

    companion object {
        const val TYPE_SENT = 1
        const val TYPE_RECEIVED = 2
        const val TYPE_RECEIVED_IMAGE = 3
        const val TYPE_RECEIVED_FLEX = 4
    }

    private var itemClick: ChatViewAdapter.ItemClick? = null
    internal var chatEntities: List<ChatEntity> = ArrayList<ChatEntity>()
    // private var adapterDataList: List<Any> = emptyList()

    //--------onCreateViewHolder: inflate layout with view holder-------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_SENT -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_sent, parent, false)
                ChatSentViewHolder(view)
            }
            TYPE_RECEIVED -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_received, parent, false)
                ChatReceivedViewHolder(view)
            }
            TYPE_RECEIVED_IMAGE -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_received_image, parent, false)
                ChatReceivedImageViewHolder(view)
            }
            TYPE_RECEIVED_FLEX -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_chat_flex, parent, false)
                ChatReceivedFlexViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    //-----------onCreateViewHolder: bind view with data model---------
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = chatEntities[position]
        if ((position + 1) == chatEntities!!.size) {
            element?.let { itemClick!!.onLastItemReached(it) }
        }
        when (holder) {
            is ChatSentViewHolder -> holder.bind(element)  //(element as chatentity)
            is ChatReceivedViewHolder -> holder.bind(element)
            is ChatReceivedImageViewHolder -> holder.bind(element)
            is ChatReceivedFlexViewHolder -> holder.bind(element)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = chatEntities[position]

        return when (comparable.chatType) {
            1 -> TYPE_SENT
            2 -> TYPE_RECEIVED
            3 -> TYPE_RECEIVED_IMAGE
            4 -> TYPE_RECEIVED_FLEX
            //is Trailer -> TYPE_FRIEND
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    override fun getItemCount(): Int {
        return chatEntities?.size ?: 0
    }

    inner class ChatSentViewHolder(itemView: View) : BaseViewHolder<ChatEntity?>(itemView) {

        internal var msgSent: TextView

        init {
            msgSent = itemView.findViewById(R.id.msgSent)
        }

        override fun bind(item: ChatEntity?) {
            //Do your view assignment here from the data model
            item?.let {
                msgSent.setText(item.chatDetails)
            }
        }
    }

    inner class ChatReceivedViewHolder(itemView: View) : BaseViewHolder<ChatEntity?>(itemView) {

        internal var msgReceived: TextView

        init {
            msgReceived = itemView.findViewById(R.id.msgReceived)
        }

        override fun bind(item: ChatEntity?) {
            //Do your view assignment here from the data model
            item?.let {
                msgReceived.setText(item.chatDetails)
            }
        }
    }

    inner class ChatReceivedImageViewHolder(itemView: View) : BaseViewHolder<ChatEntity?>(itemView) {

        internal var ivImage: ImageView

        init {
            ivImage = itemView.findViewById(R.id.ivQuesUrl)
        }

        override fun bind(item: ChatEntity?) {
            //Do your view assignment here from the data model
            item?.let {
                Glide
                    .with(context)
                    .load(it.chatDetails)
                    //.transform(RoundedCorners(2))
                    //.override(ivImage.maxWidth, ivImage.maxHeight)
                    //.placeholder(android.R.drawable.progress_horizontal)
                    .into(ivImage);
            }
        }
    }

    inner class ChatReceivedFlexViewHolder(itemView: View) : BaseViewHolder<ChatEntity?>(itemView) {

        internal var msgReceived: TextView
        internal var flexboxlayout: FlexboxLayout

        init {
            msgReceived = itemView.findViewById(R.id.msgReceived)
            flexboxlayout = itemView.findViewById(R.id.flexboxlayout)
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun bind(item: ChatEntity?) {

            //Do your view assignment here from the data model
            item?.let {
                Logger.d(item.chatDetails)
                val question = Gson().fromJson(item.chatDetails, QuestionEntity::class.java)
                msgReceived.text = question.quesString
                setTextViewInFlexbox(item, question, flexboxlayout)
            }
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    fun setChatEntities(userEntities: List<ChatEntity>) {
        // val diffResult = DiffUtil.calculateDiff(ChatDiffCallBack(this.chatEntities, userEntities))
        //diffResult.dispatchUpdatesTo(this);
        this.chatEntities = userEntities
        notifyDataSetChanged()
    }

    interface ItemClick {

        fun onLastItemReached(chatEntity: ChatEntity)

        fun onItemClick(chatEntity: ChatEntity)

        fun onLongClick(chatEntity: ChatEntity)

        fun onOptionSelected(chatEntity: ChatEntity, questionEntity: QuestionEntity)
    }

    fun setOnItemClickListener(listener: ItemClick) {
        this.itemClick = listener
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun setTextViewInFlexbox(
        chatEntity: ChatEntity,
        questionEntity: QuestionEntity,
        flexboxlayout: FlexboxLayout
    ) {
        flexboxlayout.removeAllViews()
        for (x in 0 until questionEntity.option.size step 1) {

            val view = LayoutInflater.from(context).inflate(R.layout.item_textview_flex, null)
            view.tvTitle.text = questionEntity.option[x]
            if (questionEntity.selectedAns == x) {
                view.tvTitle.setBackgroundResource(R.drawable.flex_selected)
            } else {
                view.tvTitle.setBackgroundResource(R.drawable.flex_unselected)
            }
            flexboxlayout.addView(view)


            if (questionEntity.selectedAns == -1) {
                view.setOnClickListener { v ->
                    questionEntity.selectedAns = x
                    view.tvTitle.setBackgroundResource(R.drawable.flex_selected)
                    itemClick!!.onOptionSelected(chatEntity, questionEntity)
                }
            } else {
                view.setOnClickListener { null }
            }

        }
    }

}