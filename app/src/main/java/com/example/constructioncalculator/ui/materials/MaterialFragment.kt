package com.example.constructioncalculator.ui.materials

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constructioncalculator.MainApp
import com.example.constructioncalculator.R
import com.example.constructioncalculator.adapter.UiAdapter
import com.example.constructioncalculator.databinding.MaterialFragmentBinding
import com.example.constructioncalculator.ui.base.BaseFragment
import javax.inject.Inject

class MaterialFragment : BaseFragment<MaterialFragmentBinding>(MaterialFragmentBinding::inflate) {

    private lateinit var viewModel: MaterialViewModel
    private var adapter: UiAdapter? = null

    @Inject
    lateinit var factory: MaterialViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        viewModel.material.observe(viewLifecycleOwner) {
            Log.d("material", it.toString())
            adapter?.setMaterial(it)
        }

        binding.materialFab.setOnClickListener {
            addMaterial()
        }
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, factory)[MaterialViewModel::class.java]

        adapter = UiAdapter("material")

        binding.materialRecycler.adapter = adapter

        binding.materialRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addMaterial() {
        findNavController().navigate(R.id.action_materialFragment_to_insertDialogFragment)
    }
}