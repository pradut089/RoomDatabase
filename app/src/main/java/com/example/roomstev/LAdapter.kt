package com.example.roomstev

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstev.data.User
import com.example.roomstev.data.UserViewModel

class LAdapter:ListAdapter<User,LAdapter.MyViewHolder>(DiffUtilCallback()) {

    private var mViewModel: UserViewModel? = null

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {}
    class DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentIetm = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.tv_id_rv).text = currentIetm.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_fname_rv).text = currentIetm.firstName
        holder.itemView.findViewById<TextView>(R.id.tv_lname_rv).text = currentIetm.lastName
        holder.itemView.findViewById<TextView>(R.id.tv_age_rv).text = currentIetm.age.toString()

        holder.itemView.findViewById<ImageView>(R.id.edit).setOnClickListener {
            val action = LIstFragment2Directions.actionLIstFragment2ToUpdateFragment(currentIetm)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.findViewById<ImageView>(R.id.delete).setOnClickListener {
            mViewModel = ViewModelProvider(holder.itemView.context as ViewModelStoreOwner).get(UserViewModel::class.java)
            mViewModel!!.deleteUser(currentIetm)

        }
    }
}