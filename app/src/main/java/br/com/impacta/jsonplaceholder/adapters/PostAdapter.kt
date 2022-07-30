package br.com.impacta.jsonplaceholder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.impacta.jsonplaceholder.data.models.Post
import br.com.impacta.jsonplaceholder.databinding.PostItemBinding

class PostAdapter(val listaPosts: List<Post>, val funcao: (Int) -> Unit): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val _binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.post = listaPosts[position]
        holder.binding.root.setOnClickListener {
            funcao(listaPosts[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listaPosts.size
    }
}