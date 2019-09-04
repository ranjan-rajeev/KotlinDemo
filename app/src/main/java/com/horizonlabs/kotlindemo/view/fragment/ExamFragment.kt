package com.horizonlabs.kotlindemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.horizonlabs.kotlindemo.utility.Logger
import com.horizonlabs.kotlindemo.view.base.BaseFragment
import com.horizonlabs.kotlindemo.viewmodel.ChatViewModel
import com.horizonlabs.kotlindemo.viewmodel.ExamViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.regex.Pattern
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 21-08-2019.
 */
class ExamFragment : BaseFragment()/*, View.OnClickListener, ChatViewAdapter.ItemClick*/ {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var examViewModel: ExamViewModel
    lateinit var rvChat: RecyclerView
    lateinit var chatAdapter: ChatViewAdapter
    lateinit var etInput: EditText
    lateinit var ivSend: ImageView
    lateinit var chatEntity: ChatEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        examViewModel = ViewModelProviders.of(this, viewModelFactory).get(ExamViewModel::class.java)

        examViewModel.getChatList().observe(this, Observer {
            if (it != null) {
                Logger.d("" + it.size)
                /* chatAdapter.setChatEntities(it)
                 if (it.size > 4)
                     rvChat.smoothScrollToPosition(it.size - 1)*/
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_chat, container, false)
       /* chatAdapter = ChatViewAdapter(this.context!!);
        rvChat = view.findViewById(R.id.rvChat)
        rvChat.setHasFixedSize(true)
        var layout = LinearLayoutManager(this.activity, RecyclerView.VERTICAL, false)
        layout.stackFromEnd = false
        rvChat.layoutManager = layout
        rvChat.adapter = chatAdapter
        chatAdapter.setOnItemClickListener(this)

        etInput = view.findViewById(R.id.etInput)
        ivSend = view.findViewById(R.id.ivSend)
        ivSend.setOnClickListener(this)*/
        return view
    }


   /* override fun onClick(v: View?) {
        if (v?.id == R.id.etInput) {

        } else if (v?.id == R.id.ivSend) {
            if (validate(chatEntity, etInput)) {
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
            Toast.makeText(this.context, "Enter valid data", Toast.LENGTH_LONG).show()
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

    }*/
}