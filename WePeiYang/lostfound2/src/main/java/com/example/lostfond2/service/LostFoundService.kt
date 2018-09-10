package com.example.lostfond2.service

import com.twt.wepeiyang.commons.experimental.network.CommonBody
import com.twt.wepeiyang.commons.experimental.network.ServiceFactory
import kotlinx.coroutines.experimental.Deferred
import okhttp3.MultipartBody
import retrofit2.http.*
import rx.Observable

interface LostFoundService {
    @GET("v1/lostfound/lost")
    fun getLost(@Query("page") page: Int, @Query("detail_type") detail_type: Int): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @GET("v1/lostfound/found")
    fun getFound(@Query("page") page: Int, @Query("detail_type") detail_type: Int): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @GET("v1/lostfound/{id}")
    fun getDetailed(@Path("id") id: Int): Deferred<CommonBody<DetailData>>

    @GET("v1/lostfound/search")
    fun getSearch(@Query("keyword") keyword: String, @Query("page") page: Int): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @GET("v1/lostfound/user/{lostOrFound}")
    fun getMyList(@Path("lostOrFound") lostOrFound: String, @Query("page") page: Int): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @GET("v1/lostfound/inverse/{id}")
    fun turnStatus(@Path("id") id: String): Deferred<CommonBody<String>>


    @FormUrlEncoded
    @POST("v1/lostfound/{lostOrFound}")
    fun updateRelease(@FieldMap map: Map<String, Any>, @Path("lostOrFound") lostOrFound: String): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @Multipart
    @POST("v1/lostfound/{lostOrFound}")
    fun updateReleaseWithPic(@Path("lostOrFound") lostOrFound: String,
                             @Part partList: List<MultipartBody.Part>): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @POST("v1/lostfound/edit/{lostOrFound}/{id}")
    @FormUrlEncoded
    fun updateEdit(@FieldMap map: Map<String, Any>,
                   @Path("lostOrFound") lostOrFound: String,
                   @Path("id") id: String): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @POST("v1/lostfound/edit/{lostOrFound}/{id}")
    @Multipart
    fun updateEditWithPic(@Path("lostOrFound") lostOrFound: String,
                          @Path("id") id: String,
                          @Part partList: List<MultipartBody.Part>): Deferred<CommonBody<List<MyListDataOrSearchBean>>>

    @DELETE("v1/lostfound/{id}")
    fun delete(@Path("id") id: String): Deferred<CommonBody<String>>

    companion object : LostFoundService by ServiceFactory()


}


data class Data(
        val id: Int,
        val name: String,
        val title: String,
        val place: String,
        val phone: String,
        val isback: Int,
        val picture: String
)

data class DetailData(
        val id: Int,
        val type: Int,
        val title: String,
        val name: String,
        val time: String,
        val place: String,
        val phone: String,
        val item_description: String,
        val detail_type: Int,
        val picture: String,
        val card_name: String,
        val card_number: String,
        val publish_start: String,
        val publish_end: String,
        val other_tag: String,
        val recapture_place : String,
        val recapture_enterance : Int,
        val QQ : String,
        val Wechat : String,
        val campus : String
)

data class InverseID(
        val error_code: Int,
        val message: String,
        val data: String
)


data class MyListDataOrSearchBean(


        val id: Int,
        val name: String,
        val title: String,
        val place: String,
        val time: String,
        val phone: String,
        val detail_type: Int,
        val isback: Int,
        val picture: String,
        val recapture_place : String,
        val recapture_enterance : Int,
        val QQ : String,
        val Wechat : String,
        val campus : String
)
