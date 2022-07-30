package br.com.impacta.jsonplaceholder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.impacta.jsonplaceholder.adapters.PostAdapter
import br.com.impacta.jsonplaceholder.data.models.Post
import br.com.impacta.jsonplaceholder.data.remote.JSONPlaceHolderEndpoints
import br.com.impacta.jsonplaceholder.data.remote.NetworkUtils
import br.com.impacta.jsonplaceholder.databinding.FragmentPostsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = NetworkUtils.getRetrofitInstance("https://jsonplaceholder.typicode.com/")
        val jsonEndpoints = retrofit.create(JSONPlaceHolderEndpoints::class.java)

        binding.progressBar.visibility = View.GONE

        binding.button.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            val chamada = jsonEndpoints.getAllPosts()
            chamada.enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    response.body()?.let { lista ->

                        val adapter = PostAdapter(lista) { id ->
                            val action =
                                PostsFragmentDirections.actionPostsFragmentToPostCommentsFragment(id)
                            findNavController().navigate(action)
                        }
                        binding.postRecyclerView.adapter = adapter
                        binding.postRecyclerView.layoutManager = LinearLayoutManager(context)
                        binding.progressBar.visibility = View.GONE
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Log.e("IMPACTA", t.stackTraceToString())
                    Toast.makeText(context, "Houve um erro na conexão", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }


}