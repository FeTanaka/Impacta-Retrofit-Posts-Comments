package br.com.impacta.jsonplaceholder.data.remote

import br.com.impacta.jsonplaceholder.data.models.Comment
import br.com.impacta.jsonplaceholder.data.models.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JSONPlaceHolderEndpoints {

    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @GET("/posts/{id}/comments")
    fun getCommentsByPostId(@Path("id") id: Int): Call<List<Comment>>

    @GET("/comments")
    fun getCommentsByPostId2(@Query("postId") id: Int): Call<List<Comment>>
}
