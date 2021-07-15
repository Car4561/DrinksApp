package com.carlos.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.tragosapp.R
import com.carlos.tragosapp.cache.DrinkDatabase
import com.carlos.tragosapp.cache.RoomDataSource
import com.carlos.tragosapp.databinding.FragmentFavoritesBinding
import com.carlos.tragosapp.domain.RepositoryImpl
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.remote.DrinkDbDataSource
import com.carlos.tragosapp.presentation.MainViewModel
import com.carlos.tragosapp.presentation.ViewModelFactory
import com.carlos.tragosapp.vo.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class  FavoritesFragment : Fragment(), MainAdapter.OnDrinkClickListener{

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel by activityViewModels<MainViewModel> {
        ViewModelFactory(
            RepositoryImpl(
                RoomDataSource(DrinkDatabase.getInstance(requireContext())),
                DrinkDbDataSource()
            )
        )
    }

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFlows()
    }
    private fun setupFlows(){
        binding.apply {
            lifecycleScope.launch {
                viewModel.fetchDrinksFavoriteLits.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED).collect { result ->
                    when (result) {
                        is Resource.Loading -> {

                        }
                        is Resource.Sucess -> {
                            Log.d("FAVORITOS", "${result.data}")
                            mainAdapter.submitList(result.data)
                        }
                        is Resource.Failure -> {

                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvDrinksFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
            mainAdapter = MainAdapter(requireContext(),this@FavoritesFragment)
            adapter = mainAdapter
        }
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        bundle.putBoolean("isFavorite",true)
        findNavController().navigate(R.id.action_favoritesFragment_to_detailsDrinkFragment,bundle)
    }

}