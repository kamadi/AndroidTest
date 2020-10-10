package kz.kamadi.androidtest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kz.kamadi.androidtest.R
import kz.kamadi.androidtest.domain.model.User

class UserDetailFragment : Fragment() {

    private val user: User by lazy {
        requireArguments().getParcelable<User>(USER)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginTextView.text = user.login
        Picasso.get()
            .load(user.avatarUrl)
            .into(avatarImageView)
    }

    companion object {

        private const val USER = "user"

        fun newInstance(user: User) = UserDetailFragment().apply {
            arguments = bundleOf(Pair(USER, user))
        }
    }
}