package br.com.impacta.jsonplaceholder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.impacta.jsonplaceholder.data.models.Comment
import br.com.impacta.jsonplaceholder.databinding.CommentItemBinding

class CommentAdapter(val listaComentarios: List<Comment>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(val binding: CommentItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var _binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.comment = listaComentarios[position]
    }

    override fun getItemCount(): Int {
        return listaComentarios.size
    }
}