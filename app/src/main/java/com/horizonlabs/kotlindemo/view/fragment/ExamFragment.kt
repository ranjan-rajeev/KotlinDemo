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
import com.horizonlabs.kotlindemo.adapters.ExamAdapter
import com.horizonlabs.kotlindemo.model.QuestionEntity
import com.horizonlabs.kotlindemo.utility.Logger
import com.horizonlabs.kotlindemo.view.base.BaseFragment
import com.horizonlabs.kotlindemo.viewmodel.ExamViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.horizonlabs.kotlindemo.utility.SnapHelper


/**
 * Created by Rajeev Ranjan -  ABPB on 21-08-2019.
 */
class ExamFragment : BaseFragment(), ExamAdapter.ItemClick {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var examViewModel: ExamViewModel
    lateinit var rvQuestions: RecyclerView
    lateinit var examAdapter: ExamAdapter


    lateinit var questionEntity: QuestionEntity

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
                examAdapter.setQuestionEntities(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_exam, container, false)
        examAdapter = ExamAdapter(this.context!!);
        rvQuestions = view.findViewById(R.id.rvQuestions)
        rvQuestions.setHasFixedSize(true)
        var layout = LinearLayoutManager(this.activity, RecyclerView.HORIZONTAL, false)
        layout.stackFromEnd = false
        rvQuestions.layoutManager = layout
        rvQuestions.adapter = examAdapter
        val snapHelper = SnapHelper()
        snapHelper.attachToRecyclerView(rvQuestions)
        examAdapter.setOnItemClickListener(this)

        return view
    }

    override fun onItemClick(questionEntity: QuestionEntity) {

    }

    override fun onFavouriteClick(questionEntity: QuestionEntity) {

    }

}