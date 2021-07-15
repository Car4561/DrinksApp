package com.carlos.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.tragosapp.R
import com.carlos.tragosapp.cache.DrinkDatabase
import com.carlos.tragosapp.cache.RoomDataSource
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.databinding.FragmentMainBinding
import com.carlos.tragosapp.domain.RepositoryImpl
import com.carlos.tragosapp.remote.DrinkDbDataSource
import com.carlos.tragosapp.presentation.MainViewModel
import com.carlos.tragosapp.presentation.ViewModelFactory
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by activityViewModels<MainViewModel>{  ViewModelFactory(
        RepositoryImpl(
            RoomDataSource(DrinkDatabase.getInstance(requireContext())),
            DrinkDbDataSource()
        )
    ) }

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        // Inflate the layout for this fragment
        binding =  FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel = ViewModelProvider(this, ViewModelFactory(RepoImpl(DataSource())) ).get()
        setupRecyclerView()
        setupSearchView()
        setupBtnFavorite()
        setupFlows()
    }

    private fun setupFlows(){
        binding.apply {
            lifecycleScope.launch {
                viewModel.fetchDrinksList.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED).collect{ result ->
                    when (result){
                        is Resource.Sucess -> {
                            progressBar.visibility = View.GONE
                            mainAdapter.submitList(result.data)
                        }
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Failure -> {
                            progressBar.visibility = View.GONE
                            Log.d("TAG1",result.exception.toString())
                            Toast.makeText(requireContext(),"Ocurrio un error al traer los datos ${result.exception}",Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

    private fun setupSearchView() {
        var currentDrink = "margarita"
        binding.searchView.apply {
            setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("DETALLES","query : $query , current : $currentDrink , bool : ${query.equals(currentDrink)}")
                    query.equals(currentDrink).takeIf { !it }?.apply {
                        viewModel.getDrinks(query ?: "")
                        currentDrink = query ?: ""
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun setupRecyclerView(){
        binding.rvDrinks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
            mainAdapter = MainAdapter(requireContext(),this@MainFragment)
            adapter = mainAdapter
        }
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        bundle.putBoolean("isFavorite",false)
        findNavController().navigate(R.id.action_mainFragment_to_detailsDrinkFragment,bundle)
    }

    private fun setupBtnFavorite() {
        binding.btnFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
    }

}