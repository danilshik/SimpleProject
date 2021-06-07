package com.example.simpleproject.ui.location_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.data.BaseListResource
import com.example.simpleproject.data.local.LocationItem
import com.example.simpleproject.databinding.FrLocationListBinding
import com.example.simpleproject.factories.LocationListViewModelFactory
import com.example.simpleproject.ui.BaseListState
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.base.NavigationCommand
import com.example.simpleproject.ui.custom.pagination.PaginationScrollListener
import com.example.simpleproject.ui.root.RootActivity
import javax.inject.Inject

class LocationListFragment : BaseFragment<LocationListViewModel>(){
    private var _bind : FrLocationListBinding? = null
    private val bind get() = _bind!!
    lateinit var viewModel: LocationListViewModel
    private var isPageLast : Boolean = false
    private var isLoadingState : Boolean = false

    @Inject
    lateinit var viewModelFactory : LocationListViewModelFactory

    private val locationListAdapter by lazy {
        LocationListAdapter(object : LocationListListener{
            override fun onClick(item: LocationItem) {
                val action = LocationListFragmentDirections.actionToLocationDetail(
                    item.name,
                    item.type,
                    item.dimension
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
        _bind = FrLocationListBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        with(bind.rv){
            layoutManager = linearLayoutManager
            adapter = locationListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
                override fun loadMoreItems() {
                    viewModel.loadPageLocationList(true)
                }
                override val isLastPage: Boolean
                    get() = isPageLast
                override val isLoading: Boolean
                    get() = isLoadingState

            })

        }


        with(bind.sl){
            setOnRefreshListener {
                locationListAdapter.clearItems()
                viewModel.loadPageLocationList(false)
            }
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(LocationListViewModel::class.java)
        initViewModel()
    }


    private fun initViewModel(){
//        viewModel.observeNotifications(viewLifecycleOwner, {
//            when(it){
//                is Notify.TextMessage -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
//                is Notify.ActionMessage -> {}
//            }
//        })
//
//        viewModel.observeNavigation(viewLifecycleOwner, {
//            when(it){
//
//            }
//        })

        viewModel.observeState(viewLifecycleOwner, {
            Log.d("State", it.toString())
            isLoadingState = it is LocationListState.Loading
            bind.sl.isRefreshing = it is LocationListState.Loading
            bind.tv.isVisible = it is LocationListState.Error
            bind.rv.isVisible = it is LocationListState.Result || it is LocationListState.Loading
            when(it){
                is LocationListState.Result -> {
                    locationListAdapter.addItems(it.locations)
                    isPageLast = it.isLastPage
                }
                is LocationListState.Error -> { bind.tv.text = it.message }
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