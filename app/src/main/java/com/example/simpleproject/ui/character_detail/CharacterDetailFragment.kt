package com.example.simpleproject.ui.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.databinding.FrCharacterDetailBinding
import com.example.simpleproject.databinding.FrLocationDetailBinding
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.location_detail.LocationDetailFragmentArgs
import com.example.simpleproject.ui.location_detail.LocationDetailViewModel

class CharacterDetailFragment : BaseFragment<CharacterDetailViewModel>() {
    private val args : CharacterDetailFragmentArgs by navArgs()
    private var _bind : FrCharacterDetailBinding? = null
    private val bind get() = _bind!!

    val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(this)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FrCharacterDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind.tvName){
            text = args.name
        }

        with(bind.tvGender){
            text = args.gender
        }

        with(bind.tvSpecies){
            text = args.species
        }

        with(bind.tvStatus){
            text = args.status
        }

        with(bind.ivAvatar){
            Glide.with(this)
                .load(args.image)
                .into(this)
        }

        with(bind.tvLocatin){
            text = args.location
        }
    }


}