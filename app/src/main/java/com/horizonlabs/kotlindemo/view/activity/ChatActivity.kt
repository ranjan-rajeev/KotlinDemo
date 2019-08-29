package com.horizonlabs.kotlindemo.view.activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.adapters.ChatAdapter
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.view.base.BaseActivity
import com.horizonlabs.kotlindemo.viewmodel.ChatViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

class ChatActivity : BaseActivity(), View.OnClickListener, ChatAdapter.ItemClick {


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var chatViewModel: ChatViewModel
    lateinit var rvChat: RecyclerView
    lateinit var chatAdapter: ChatAdapter
    lateinit var etInput: EditText
    lateinit var ivSend: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        AndroidInjection.inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        chatAdapter = ChatAdapter(this);
        rvChat = findViewById(R.id.rvChat)
        rvChat.setHasFixedSize(true)
        rvChat.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvChat.adapter = chatAdapter
        chatAdapter.setOnItemClickListener(this)

        chatViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)

        chatViewModel.getChatList().observe(this, Observer {
            if (it != null)
                chatAdapter.setChatEntities(it)
        })

        etInput = findViewById(R.id.etInput)
        ivSend = findViewById(R.id.ivSend)
        ivSend.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.etInput) {

        } else if (v?.id == R.id.ivSend) {
            if (etInput.text.toString() != "") {
                chatViewModel.addUserInput(etInput.text.toString())
                etInput.setText("")
            }
        }
    }

    override fun onLastItemReached(chatEntity: ChatEntity) {
        if(!chatEntity.isUserInputRequired){
            chatViewModel.fetchNextChat(chatEntity.seqId)
        }
        Toast.makeText(this@ChatActivity, "Last Item Reached", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(chatEntity: ChatEntity) {

    }

    override fun onLongClick(chatEntity: ChatEntity) {

    }
}
