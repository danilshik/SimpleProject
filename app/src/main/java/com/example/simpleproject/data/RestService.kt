package com.example.simpleproject.data

import com.example.simpleproject.data.remote.response.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {

    //https://rickandmortyapi.com/api/location?page="1"
    @GET("location")
    fun getLocationList(
        @Query("page") page: Int
    ) : Single<BaseResponse<List<LocationResponse>>>

    //https://rickandmortyapi.com/api/character?page="1"
    @GET("character")
    fun getCharacterList(
        @Query("page") page: Int
    ): Single<BaseResponse<List<CharacterListResponse>>>

    //https://rickandmortyapi.com/api/episode?page="1"
    @GET("episode")
    fun getEpisodesList(
        @Query("page") page: Int
    ): Single<BaseResponse<List<EpisodeListResponse>>>
}