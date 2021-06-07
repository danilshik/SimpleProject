package com.example.simpleproject.ui.character_list

import com.example.simpleproject.data.repositories.CharacterRepositoryContract
import com.example.simpleproject.ui.base.BaseViewModel
import com.example.simpleproject.ui.base.Notify
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepositoryContract
) : BaseViewModel<CharacterListState>(CharacterListState.Loading), ICharacterListViewModel{



    init {
        loadPageCharacterList(false)
    }


    override fun loadPageCharacterList(isNewPage : Boolean){
        updateState { CharacterListState.Loading }
        repository.getCharacters(isNewPage)
            .subscribe(
                { resource ->
                    updateState {
                        CharacterListState.Result(
                            resource.items, resource.isLastPage
                        )
                    }
                },
                { throwable ->
                    updateState {
                        CharacterListState.Error("При загрузки данных произошла ошибка", throwable)
                    }
                    notify(Notify.TextMessage("При загрузке данных произошла ошибка"))
                }).track()
    }
}

interface ICharacterListViewModel{
    fun loadPageCharacterList(isNewPage: Boolean = false)
}