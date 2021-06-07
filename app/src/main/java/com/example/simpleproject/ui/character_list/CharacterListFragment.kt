package com.example.simpleproject.ui.character_list

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
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.databinding.FrCharacterListBinding
import com.example.simpleproject.databinding.FrLocationListBinding
import com.example.simpleproject.factories.CharacterListViewModelFactory
import com.example.simpleproject.factories.LocationListViewModelFactory
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.base.NavigationCommand
import com.example.simpleproject.ui.custom.pagination.PaginationScrollListener
import com.example.simpleproject.ui.location_list.*
import com.example.simpleproject.ui.root.RootActivity
import javax.inject.Inject

class CharacterListFragment : BaseFragment<CharacterListViewModel>() {
    private var _bind : FrCharacterListBinding? = null
    private val bind get() = _bind!!
    lateinit var viewModel: CharacterListViewModel
    private var isPageLast : Boolean = false
    private var isLoadingState : Boolean = false

    @Inject
    lateinit var viewModelFactory : CharacterListViewModelFactory

    private val characterListAdapter by lazy {
        CharacterListAdapter(object : CharacterListListener {
            override fun onClick(item: CharacterListItem) {
                val action = CharacterListFragmentDirections.actionToCharacterDetail(
                    item.name,
                    item.gender,
                    item.species,
                    item.status,
                    item.image,
                    item.location.name
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
        _bind = FrCharacterListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        with(bind.rv){
            layoutManager = linearLayoutManager
            adapter = characterListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
                override fun loadMoreItems() {
                    viewModel.loadPageCharacterList(true)
                }
                override val isLastPage: Boolean
                    get() = isPageLast
                override val isLoading: Boolean
                    get() = isLoadingState

            })

        }


        with(bind.sl){
            setOnRefreshListener {
                characterListAdapter.clearItems()
                viewModel.loadPageCharacterList(false)
            }
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterListViewModel::class.java)
        initViewModel()
    }


    private fun initViewModel(){

        viewModel.observeState(viewLifecycleOwner, {
            Log.d("State", it.toString())
            isLoadingState = it is CharacterListState.Loading
            bind.sl.isRefreshing = it is CharacterListState.Loading
            bind.tv.isVisible = it is CharacterListState.Error
            bind.rv.isVisible = it is CharacterListState.Result || it is CharacterListState.Loading
            when(it){
                is CharacterListState.Result -> {
                    characterListAdapter.addItems(it.characters)
                    isPageLast = it.isLastPage
                }
                is CharacterListState.Error -> { bind.tv.text = it.message }
            }
        })

        viewModel.observeNavigation(viewLifecycleOwner, {
            (activity as RootActivity?)?.viewModel?.navigate(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }
}