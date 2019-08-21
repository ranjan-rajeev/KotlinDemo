package com.horizonlabs.kotlindemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.model.UserEntity
import java.util.*


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class UserAdapter(internal var context: Context?) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var itemClick: ItemClick? = null
    internal var userEntities: List<UserEntity>? = ArrayList<UserEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UserHolder {
        val itView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_all_user, viewGroup, false)
        return UserHolder(itView)
    }

    override fun onBindViewHolder(holder: UserHolder, i: Int) {
        val userEntity = userEntities?.get(i)
        userEntity?.let {
            holder.tvName.setText(it.name)
            holder.tvUserName.setText(it.name)
            holder.tvShortName.setText("")
            holder.tvMobile.setText(it.phone)
            holder.tvEmail.setText(it.email)
            holder.tvAddress.setText(it.address.street + " , " + it.address.city)
            holder.tvShortName.setBackgroundResource(R.drawable.circle_green)
        }

    }

    override fun getItemCount(): Int {
        return userEntities?.size ?: 0
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvName: TextView
        internal var tvShortName: TextView
        internal var tvUserName: TextView
        internal var tvAddress: TextView
        internal var tvMobile: TextView
        internal var tvEmail: TextView
        internal var ivFavourite: ImageView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvShortName = itemView.findViewById(R.id.tvShortName)
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvAddress = itemView.findViewById(R.id.tvAddress)
            tvMobile = itemView.findViewById(R.id.tvMobile)
            tvEmail = itemView.findViewById(R.id.tvEmail)
            ivFavourite = itemView.findViewById(R.id.ivFavourite)

            /*itemView.setOnClickListener {
                if (itemView != null && adapterPosition != RecyclerView.NO_POSITION)
                    itemClick!!.onItemClick(userEntities[adapterPosition])
            }
            ivFavourite.setOnClickListener {
                if (itemView != null && adapterPosition != RecyclerView.NO_POSITION)
                    itemClick!!.onFavouriteClick(userEntities[adapterPosition])
            }*/
        }
    }

    fun setUserEntities(userEntities: List<UserEntity>?) {
        this.userEntities = userEntities
        notifyDataSetChanged()
    }

    interface ItemClick {
        fun onItemClick(userEntity: UserEntity)

        fun onFavouriteClick(userEntity: UserEntity)
    }

    fun setOnItemClickListener(listener: ItemClick) {
        this.itemClick = listener
    }
}
