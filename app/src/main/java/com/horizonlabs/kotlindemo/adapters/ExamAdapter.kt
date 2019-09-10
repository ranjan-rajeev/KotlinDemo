package com.horizonlabs.kotlindemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.model.QuestionEntity
import java.util.*


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class ExamAdapter(internal var context: Context) : RecyclerView.Adapter<ExamAdapter.ExamViewHolder>() {

    private var itemClick: ItemClick? = null
    internal var qusetionEntities: List<QuestionEntity>? = ArrayList<QuestionEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ExamViewHolder {
        val itView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_question, viewGroup, false)
        return ExamViewHolder(itView)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, i: Int) {
        val questionEntity = qusetionEntities?.get(i)
        questionEntity?.let {
            holder.tvQno.setText("${i + 1}")
            holder.tvQuestion.setText(it.quesString)
            holder.tvInst.setText(it.ansExplanation)
            holder.bindList(questionEntity.option)
            holder.setSelected(questionEntity.selectedAns)
            if (questionEntity.questionUrl.equals("")) {
                holder.ivQuesUrl.visibility = View.GONE
            } else {
                holder.ivQuesUrl.visibility = View.VISIBLE
                Glide
                    .with(context)
                    .load(it.questionUrl)
                    //.transform(RoundedCorners(2))
                    //.override(ivImage.maxWidth, ivImage.maxHeight)
                    //.placeholder(android.R.drawable.progress_horizontal)
                    .into(holder.ivQuesUrl);
            }

        }

    }

    override fun getItemCount(): Int {
        return qusetionEntities?.size ?: 0
    }

    inner class ExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvQno: TextView
        internal var tvInst: TextView
        internal var tvQuestion: TextView

        //internal var ivMark: ImageView
        internal var ivQuesUrl: ImageView

        internal var tvOptionOne: TextView
        internal var tvOptionTwo: TextView
        internal var tvOptionThree: TextView
        internal var tvOptionFour: TextView
        internal var tvOptionFive: TextView
        internal var tvOptionSix: TextView

        init {
            tvQno = itemView.findViewById(R.id.tvQno)
            tvInst = itemView.findViewById(R.id.tvInst)
            tvQuestion = itemView.findViewById(R.id.tvQuestion)

            //ivMark = itemView.findViewById(R.id.ivMark)
            ivQuesUrl = itemView.findViewById(R.id.ivQuesUrl)

            tvOptionOne = itemView.findViewById(R.id.tvOptionOne)
            tvOptionTwo = itemView.findViewById(R.id.tvOptionTwo)
            tvOptionThree = itemView.findViewById(R.id.tvOptionThree)
            tvOptionFour = itemView.findViewById(R.id.tvOptionFour)
            tvOptionFive = itemView.findViewById(R.id.tvOptionFive)
            tvOptionSix = itemView.findViewById(R.id.tvOptionSix)

            /* itemView.setOnClickListener {
                 if (itemView != null && adapterPosition != RecyclerView.NO_POSITION)
                     itemClick!!.onItemClick(qusetionEntities[adapterPosition])
             }*/

        }

        fun bindList(list: List<String>) {
            tvOptionOne.visibility = View.GONE
            tvOptionTwo.visibility = View.GONE
            tvOptionThree.visibility = View.GONE
            tvOptionFour.visibility = View.GONE
            tvOptionFive.visibility = View.GONE
            tvOptionSix.visibility = View.GONE

            when (list.size) {
                1 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                }

                2 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                    tvOptionTwo.visibility = View.VISIBLE
                    tvOptionTwo.text = list.get(1)
                }

                3 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                    tvOptionTwo.visibility = View.VISIBLE
                    tvOptionTwo.text = list.get(1)
                    tvOptionThree.visibility = View.VISIBLE
                    tvOptionThree.text = list.get(2)
                }

                4 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                    tvOptionTwo.visibility = View.VISIBLE
                    tvOptionTwo.text = list.get(1)
                    tvOptionThree.visibility = View.VISIBLE
                    tvOptionThree.text = list.get(2)
                    tvOptionFour.visibility = View.VISIBLE
                    tvOptionFour.text = list.get(3)
                }

                5 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                    tvOptionTwo.visibility = View.VISIBLE
                    tvOptionTwo.text = list.get(1)
                    tvOptionThree.visibility = View.VISIBLE
                    tvOptionThree.text = list.get(2)
                    tvOptionFour.visibility = View.VISIBLE
                    tvOptionFour.text = list.get(3)
                    tvOptionFive.visibility = View.VISIBLE
                    tvOptionFive.text = list.get(4)
                }

                6 -> {
                    tvOptionOne.visibility = View.VISIBLE
                    tvOptionOne.text = list.get(0)
                    tvOptionTwo.visibility = View.VISIBLE
                    tvOptionTwo.text = list.get(1)
                    tvOptionThree.visibility = View.VISIBLE
                    tvOptionThree.text = list.get(2)
                    tvOptionFour.visibility = View.VISIBLE
                    tvOptionFour.text = list.get(3)
                    tvOptionFive.visibility = View.VISIBLE
                    tvOptionFive.text = list.get(4)
                    tvOptionSix.visibility = View.VISIBLE
                    tvOptionSix.text = list.get(4)
                }
            }


        }

        fun setSelected(option: Int) {
            tvOptionOne.setBackgroundResource(R.drawable.flex_unselected)
            tvOptionTwo.setBackgroundResource(R.drawable.flex_unselected)
            tvOptionThree.setBackgroundResource(R.drawable.flex_unselected)
            tvOptionFour.setBackgroundResource(R.drawable.flex_unselected)
            tvOptionFive.setBackgroundResource(R.drawable.flex_unselected)
            tvOptionSix.setBackgroundResource(R.drawable.flex_unselected)

            when (option) {
                0 -> {
                    tvOptionOne.setBackgroundResource(R.drawable.flex_selected)
                }
                1 -> {
                    tvOptionTwo.setBackgroundResource(R.drawable.flex_selected)
                }
                2 -> {
                    tvOptionThree.setBackgroundResource(R.drawable.flex_selected)
                }
                3 -> {
                    tvOptionFour.setBackgroundResource(R.drawable.flex_selected)
                }
                4 -> {
                    tvOptionFive.setBackgroundResource(R.drawable.flex_selected)
                }
                5 -> {
                    tvOptionSix.setBackgroundResource(R.drawable.flex_selected)
                }
            }
        }
    }

    fun setQuestionEntities(qusetionEntities: List<QuestionEntity>?) {
        this.qusetionEntities = qusetionEntities
        notifyDataSetChanged()
    }

    interface ItemClick {
        fun onItemClick(questionEntity: QuestionEntity)

        fun onFavouriteClick(questionEntity: QuestionEntity)
    }

    fun setOnItemClickListener(listener: ItemClick) {
        this.itemClick = listener
    }
}
