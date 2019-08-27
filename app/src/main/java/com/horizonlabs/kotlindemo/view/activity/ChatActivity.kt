package com.horizonlabs.kotlindemo.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.adapters.ChatAdapter
import com.horizonlabs.kotlindemo.view.base.BaseActivity
import com.horizonlabs.kotlindemo.viewmodel.ChatViewModel
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

class ChatActivity : BaseActivity() {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var chatViewModel: ChatViewModel
    lateinit var rvChat: RecyclerView
    lateinit var chatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        chatAdapter = ChatAdapter(this);
        rvChat = findViewById(R.id.rvChat)
        rvChat.setHasFixedSize(true)
        rvChat.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvChat.adapter = chatAdapter


        chatViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)

        chatViewModel.getChatList().observe(this, Observer {
            if (it != null)
                chatAdapter.setChatEntities(it)
        })


    }

}
