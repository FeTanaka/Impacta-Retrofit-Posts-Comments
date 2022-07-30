package br.com.impacta.jsonplaceholder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.impacta.jsonplaceholder.adapters.CommentAdapter
import br.com.impacta.jsonplaceholder.data.models.Comment
import br.com.impacta.jsonplaceholder.data.models.Post
import br.com.impacta.jsonplaceholder.data.remote.JSONPlaceHolderEndpoints
import br.com.impacta.jsonplaceholder.data.remote.NetworkUtils
import br.com.impacta.jsonplaceholder.databinding.FragmentPostCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostCommentsFragment : Fragment() {

    private var _binding: FragmentPostCommentsBinding? = null
    private val binding get() = _binding!!
    private val args: PostCommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = NetworkUtils.getRetrofitInstance("https://jsonplaceholder.typicode.com/")
        val jsonEndpoints = retrofit.create(JSONPlaceHolderEndpoints::class.java)

        val chamada = jsonEndpoints.getPostById(args.id)
        chamada.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                response.body()?.let { post ->
                    binding.include.post = post

                    val chamada2 = jsonEndpoints.getCommentsByPostId2(args.id)
                    chamada2.enqueue(object : Callback<List<Comment>> {
                        override fun onResponse(
                            call: Call<List<Comment>>,
                            response: Response<List<Comment>>
                        ) {
                            response.body()?.let { lista ->
                                val adapter = CommentAdapter(lista)
                                binding.commentRecyclerView.adapter = adapter
                                binding.commentRecyclerView.layoutManager = LinearLayoutManager(context)
                            }
                        }

                        override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                            Log.e("IMPACTA", t.stackTraceToString())
                            Toast.makeText(context, "Não consegui pegar os comentários", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.e("IMPACTA", t.stackTraceToString())
                Toast.makeText(context, "Não consegui pegar o post", Toast.LENGTH_SHORT).show()
            }
        })
    }


}