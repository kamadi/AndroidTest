package kz.kamadi.androidtest.presentation.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.kamadi.androidtest.R
import kz.kamadi.androidtest.domain.model.User

class UsersAdapter(
    private val listener: UserItemClickListener
) : ListAdapter<User, UserViewHolder>(UsersDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserViewHolder(
    itemView: View,
    private val listener: UserItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val avatarImageView: ImageView = itemView.findViewById(R.id.avatar_image_view)
    private val loginTextView: TextView = itemView.findViewById(R.id.login_text_view)
    private val idTextView: TextView = itemView.findViewById(R.id.id_text_view)

    private var user: User? = null

    init {
        itemView.setOnClickListener {
            if (user != null) {
                listener.onItemClick(user!!,avatarImageView)
            }
        }
    }

    fun bind(item: User) {
        this.user = item
        Picasso.get()
            .load(item.avatarUrl)
            .into(avatarImageView)
        loginTextView.text = item.login
        idTextView.text = item.id.toString()
    }
}

object UsersDiff : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.login == newItem.login
    }
}

interface UserItemClickListener {
    fun onItemClick(user: User, avatarImageView: ImageView)
}