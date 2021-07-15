package com.carlos.tragosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.carlos.tragosapp.cache.DrinkDatabase
import com.carlos.tragosapp.cache.RoomDataSource
import com.carlos.tragosapp.domain.models.Drink
import com.carlos.tragosapp.databinding.FragmentDetailsDrinkBinding
import com.carlos.tragosapp.domain.RepositoryImpl
import com.carlos.tragosapp.remote.DrinkDbDataSource
import com.carlos.tragosapp.presentation.MainViewModel
import com.carlos.tragosapp.presentation.ViewModelFactory

// T
class DetailsDrinkFragment : Fragment() {


    private lateinit var binding: FragmentDetailsDrinkBinding

    private lateinit var drink: Drink

    private var isFavorite = false

    private val viewModel by activityViewModels<MainViewModel> {
        ViewModelFactory(
            RepositoryImpl(
                RoomDataSource(DrinkDatabase.getInstance(requireContext())),
                DrinkDbDataSource()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
            isFavorite = it.getBoolean("isFavorite") ?: false
            Log.d("DETALLES FRAG", "$drink")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(requireContext())
                .load(drink.imagen)
                .centerCrop()
                .into(imgDrink)
            drink.apply {
            }
            tvDrinkDesc.text = drink.nombre
            tvDrinkDesc.text = drink.description
            tvHasAlcohol.text = if (drink.hasAlcohol == "Non_Alcoholic") "Bebida sin alcohol" else "Bebida con alcohol"
            btnSaveDrink.setOnClickListener {
                viewModel.insertDrink(drink)
                Toast.makeText(requireContext(), "Se guardó el trago a favoritos", Toast.LENGTH_SHORT).show()
            }
            btnDeleteDrink.setOnClickListener{
                viewModel.deleteDrink(drink)
                Toast.makeText(requireContext(), "Se elimino el trago de favoritos", Toast.LENGTH_SHORT).show()
            }
            isFavorite.takeIf { it }?.apply {
                btnDeleteDrink.visibility = View.VISIBLE
            }
        }
    }


}