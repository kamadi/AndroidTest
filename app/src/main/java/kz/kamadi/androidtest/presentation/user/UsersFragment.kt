package kz.kamadi.androidtest.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_users.*
import kz.kamadi.androidtest.R
import kz.kamadi.androidtest.domain.model.User
import kz.kamadi.androidtest.presentation.MainViewModel
import kz.kamadi.androidtest.presentation.detail.UserDetailFragment
import kz.kamadi.androidtest.presentation.util.EndlessScrollListener
import javax.inject.Inject

class UsersFragment : DaggerFragment(), UserItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders
            .of(requireActivity(), viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private val adapter = UsersAdapter(this)

    private val endlessScrollListener = EndlessScrollListener {
        viewModel.getNextUsers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        recyclerView.apply {
            adapter = this@UsersFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                ).apply {
                    setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
                })
            addOnScrollListener(endlessScrollListener)
        }
        swipeRefreshLayout.setOnRefreshListener(this)
        viewModel.getUsers()
    }

    private fun initObservers() {
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
            endlessScrollListener.isLoading = it
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                val messageId = when {
                    it.isNetworkError -> R.string.error_internet
                    it.isHttpException -> R.string.error_http
                    else -> R.string.error_unexpected
                }
                Snackbar.make(swipeRefreshLayout, messageId, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onRefresh() {
        viewModel.getUsers(forceUpdate = true)
    }

    override fun onItemClick(user: User, avatarImageView: ImageView) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .replace(R.id.root, UserDetailFragment.newInstance(user))
            .addToBackStack("users")
            .commit()
    }
}