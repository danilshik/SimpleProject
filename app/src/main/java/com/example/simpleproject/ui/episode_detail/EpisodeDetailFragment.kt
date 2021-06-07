package com.example.simpleproject.ui.episode_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.simpleproject.AppDelegate
import com.example.simpleproject.databinding.FrCharacterDetailBinding
import com.example.simpleproject.databinding.FrEpisodeDetailBinding
import com.example.simpleproject.ui.base.BaseFragment
import com.example.simpleproject.ui.character_detail.CharacterDetailFragmentArgs
import com.example.simpleproject.ui.character_detail.CharacterDetailViewModel

class EpisodeDetailFragment  : BaseFragment<EpisodeDetailViewModel>() {
    private val args : EpisodeDetailFragmentArgs by navArgs()
    private var _bind : FrEpisodeDetailBinding? = null
    private val bind get() = _bind!!

    val viewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.appComponent.inject(this)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FrEpisodeDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind.tvName){
            text = args.name
        }

        with(bind.tvName){
            text = args.name
        }

        with(bind.tvAirDate){
            text = args.airDate
        }

        with(bind.tvEpisode){
            text = args.episod
        }

    }


}