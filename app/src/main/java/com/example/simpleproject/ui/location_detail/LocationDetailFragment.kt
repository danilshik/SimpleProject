package com.example.simpleproject.ui.location_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.databinding.FrEpisodeListBinding
import com.example.simpleproject.databinding.FrLocationDetailBinding
import com.example.simpleproject.databinding.FrLocationListBinding
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.location_list.LocationListViewModel

class LocationDetailFragment : BaseFragment<LocationDetailViewModel>() {
    private val args : LocationDetailFragmentArgs by navArgs()
    private var _bind : FrLocationDetailBinding? = null
    private val bind get() = _bind!!

    val viewModel: LocationDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(this)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FrLocationDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind.tvName){
            text = args.name
        }

        with(bind.tvType){
            text = args.type
        }

        with(bind.tvDimension){
            text = args.dimension
        }
    }


}