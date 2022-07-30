package br.com.impacta.jsonplaceholder.data.models

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
) {
    fun getIdString() = this.id.toString()
    fun getPostIdString() = this.postId.toString()
}