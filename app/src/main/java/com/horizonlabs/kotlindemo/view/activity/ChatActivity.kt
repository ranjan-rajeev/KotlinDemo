package com.horizonlabs.kotlindemo.view.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.adapters.ChatViewAdapter
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.view.base.BaseActivity
import com.horizonlabs.kotlindemo.viewmodel.ChatViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject
import java.util.regex.Pattern


class ChatActivity : BaseActivity(), View.OnClickListener, ChatViewAdapter.ItemClick {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var chatViewModel: ChatViewModel
    lateinit var rvChat: RecyclerView
    lateinit var chatAdapter: ChatViewAdapter
    lateinit var etInput: EditText
    lateinit var ivSend: ImageView
    lateinit var chatEntity: ChatEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        AndroidInjection.inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        chatAdapter = ChatViewAdapter(this);
        rvChat = findViewById(R.id.rvChat)
        rvChat.setHasFixedSize(true)
        var layout = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        layout.stackFromEnd = false
        rvChat.layoutManager = layout
        rvChat.adapter = chatAdapter
        chatAdapter.setOnItemClickListener(this)

        chatViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)

        chatViewModel.getChatList().observe(this, Observer {
            if (it != null) {
                chatAdapter.setChatEntities(it)
                if (it.size > 4)
                    rvChat.smoothScrollToPosition(it.size - 1)
            }

        })

        etInput = findViewById(R.id.etInput)
        ivSend = findViewById(R.id.ivSend)
        ivSend.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.etInput) {

        } else if (v?.id == R.id.ivSend) {
            if (validate(chatEntity,etInput)) {
                chatViewModel.addUserInput(etInput.text.toString())
                etInput.setText("")
            }
        }
    }

    fun validate(chatEntity: ChatEntity, et: EditText): Boolean {
        val input = et.text.toString()
        val pattern = Pattern.compile("" + chatEntity.regex)
        val matcher = pattern.matcher(input)
        if (!matcher.matches()) {
            Toast.makeText(this,"Enter valid data",Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onLastItemReached(chatEntity: ChatEntity) {
        this.chatEntity = chatEntity
        if (!chatEntity.isUserInputRequired) {
            chatViewModel.fetchNextChat(chatEntity.seqId)
        }
    }

    override fun onItemClick(chatEntity: ChatEntity) {

    }

    override fun onLongClick(chatEntity: ChatEntity) {

    }
}
