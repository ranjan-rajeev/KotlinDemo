package com.horizonlabs.kotlindemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.adapters.UserAdapter
import com.horizonlabs.kotlindemo.data.remote.ApiStatus
import com.horizonlabs.kotlindemo.view.base.BaseFragment
import com.horizonlabs.kotlindemo.viewmodel.UserViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 21-08-2019.
 */
class UserFragment : BaseFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var userViewModel: UserViewModel
    lateinit var rvAllUsers: RecyclerView
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        userViewModel.getUser().observe(this, Observer {

            if (it != null)
                userAdapter.setUserEntities(it)

            /*if (it.status == ApiStatus.LOADING) {
                showDialog(this.activity, it.message)
                showDialog(this.activity, it.message)
            } else if (it.status == ApiStatus.SUCCESS) {

                userAdapter.setUserEntities(it.data)
                cancelDialog()

            } else if (it.status == ApiStatus.FAILURE) {

            } else if (it.status == ApiStatus.ERROR) {

            } else {

            }*/
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_user, container, false)
        userAdapter = UserAdapter(this.activity);
        rvAllUsers = view.findViewById(R.id.rvAllUsers)
        rvAllUsers.setHasFixedSize(true)
        rvAllUsers.layoutManager = LinearLayoutManager(this.activity, RecyclerView.VERTICAL, false)
        rvAllUsers.adapter = userAdapter
        return view
    }
}