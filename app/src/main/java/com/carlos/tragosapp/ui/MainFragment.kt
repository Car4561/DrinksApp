package com.carlos.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.tragosapp.R
import com.carlos.tragosapp.data.DataSource
import com.carlos.tragosapp.data.model.Drink
import com.carlos.tragosapp.databinding.FragmentMainBinding
import com.carlos.tragosapp.domain.RepoImpl
import com.carlos.tragosapp.ui.viewmodel.MainViewModel
import com.carlos.tragosapp.ui.viewmodel.ViewModelFactory
import com.carlos.tragosapp.vo.Resource


class MainFragment : Fragment(),MainAdapter.OnDrinkClickListener {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel : MainViewModel

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
        viewModel = ViewModelProvider(this, ViewModelFactory(RepoImpl(DataSource())) ).get()
        binding.apply {
            setupRecyclerView()
            viewModel.fetchDrinksList.observe(viewLifecycleOwner){ result ->
                when (result){
                    is Resource.Sucess -> {
                        progressBar.visibility = View.GONE
                        val adapter = MainAdapter(requireContext(),result.data,this@MainFragment)
                        rvDrinks.adapter = adapter
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

    private fun setupRecyclerView(){
        binding.apply {
            rvDrinks.layoutManager = LinearLayoutManager(requireContext())
            rvDrinks.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.detailsDrinkFragment,bundle)
    }

}