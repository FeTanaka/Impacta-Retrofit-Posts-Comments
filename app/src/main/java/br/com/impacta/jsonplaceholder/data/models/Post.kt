package br.com.impacta.jsonplaceholder.data.models

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) {
    fun getUserIdString() = this.userId.toString()
    fun getIdString() = this.id.toString()
}