package com.example.simpleproject.ui.episode_list

import com.example.simpleproject.data.repositories.CharacterRepositoryContract
import com.example.simpleproject.data.repositories.EpisodeRepositoryContract
import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.ui.base.Notify
import com.example.simpleproject.ui.character_list.CharacterListState
import javax.inject.Inject

class EpisodeListViewModel  @Inject constructor(
    private val repository: EpisodeRepositoryContract
) : BaseViewModel<EpisodeListState>(EpisodeListState.Loading), IEpisodeListViewModel{



    init {
        loadPageEpisodeList(false)
    }


    override fun loadPageEpisodeList(isNewPage : Boolean){
        if(!isNewPage)
            updateState { EpisodeListState.ClearData }
        updateState { EpisodeListState.Loading }
        repository.getEpisodes(isNewPage)
            .subscribe(
                { resource ->
                    updateState {
                        EpisodeListState.Result(
                            resource.items, resource.isLastPage
                        )
                    }
                },
                { throwable ->
                    updateState {
                        EpisodeListState.Error("При загрузки данных произошла ошибка", throwable)
                    }
                    notify(Notify.TextMessage("При загрузке данных произошла ошибка"))
                }).track()
    }
}

interface IEpisodeListViewModel{
    fun loadPageEpisodeList(isNewPage: Boolean = false)
}