package com.example.simpleproject.ui.episode_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.data.local.CharacterListItem
import com.example.simpleproject.data.local.EpisodeItem
import com.example.simpleproject.databinding.FrCharacterListBinding
import com.example.simpleproject.databinding.FrEpisodeListBinding
import com.example.simpleproject.factories.CharacterListViewModelFactory
import com.example.simpleproject.factories.EpisodeListViewModelFactory
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.base.NavigationCommand
import com.example.simpleproject.ui.character_list.CharacterListAdapter
import com.example.simpleproject.ui.character_list.CharacterListListener
import com.example.simpleproject.ui.character_list.CharacterListState
import com.example.simpleproject.ui.character_list.CharacterListViewModel
import com.example.simpleproject.ui.custom.pagination.PaginationScrollListener
import com.example.simpleproject.ui.location_list.LocationListFragmentDirections
import com.example.simpleproject.ui.root.RootActivity
import javax.inject.Inject

class EpisodeListFragment : BaseFragment<EpisodeListViewModel>() {
    private var _bind : FrEpisodeListBinding? = null
    private val bind get() = _bind!!
    lateinit var viewModel: EpisodeListViewModel
    private var isPageLast : Boolean = false
    private var isLoadingState : Boolean = false

    @Inject
    lateinit var viewModelFactory : EpisodeListViewModelFactory

    private val episodeListAdapter by lazy {
        EpisodeListAdapter(object : EpisodeListListener {
            override fun onClick(item: EpisodeItem) {
                val action = EpisodeListFragmentDirections.actionToEpisodDetail(
                    item.name,
                    item.airDate,
                    item.episode
                )
                viewModel.navigate(NavigationCommand.To(action.actionId, action.arguments))
            }

        })
    }

    override val prepareToolbar: (RootActivity.ToolbarBuilder.() -> Unit) = {
//        setSubtitle("Локации")
        setVisibility(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FrEpisodeListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        with(bind.rv){
            layoutManager = linearLayoutManager
            adapter = episodeListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
                override fun loadMoreItems() {
                    viewModel.loadPageEpisodeList(true)
                }
                override val isLastPage: Boolean
                    get() = isPageLast
                override val isLoading: Boolean
                    get() = isLoadingState

            })

        }


        with(bind.sl){
            setOnRefreshListener {
                viewModel.loadPageEpisodeList(false)
            }
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(EpisodeListViewModel::class.java)
        initViewModel()
    }


    private fun initViewModel(){

        viewModel.observeState(viewLifecycleOwner, {
            Log.d("State", it.toString())
            isLoadingState = it is EpisodeListState.Loading
            bind.sl.isRefreshing = it is EpisodeListState.Loading
            bind.tv.isVisible = it is EpisodeListState.Error
            bind.rv.isVisible = it is EpisodeListState.Result || it is EpisodeListState.Loading
            when(it){
                is EpisodeListState.Result -> {
                    episodeListAdapter.addItems(it.episodes)
                    isPageLast = it.isLastPage
                }
                is EpisodeListState.Error -> { bind.tv.text = it.message }
                is EpisodeListState.ClearData -> {episodeListAdapter.clearItems()}
            }
        })

        viewModel.observeNavigation(viewLifecycleOwner, {
            (activity as RootActivity?)?.viewModel?.navigate(it)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}