package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentHomeBinding
import com.happyhour.safeguardpro.databinding.FragmentListaEpiBinding
import com.happyhour.safeguardpro.viewmodel.EpiViewModel


class ListaEpiFragment : Fragment() {

    // Chamar a viewmodel

    private val viewModel: EpiViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: EpiAdapter

    // Criar o binding

    private var _binding: FragmentListaEpiBinding? = null
    private val binding: FragmentListaEpiBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // configurar o binding
        _binding = FragmentListaEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando criar em algum item da lista
        adapter = EpiAdapter(viewModel.epiList.value) {epi ->
            val epiBundle = Bundle()
            epiBundle.putInt("epiId", epi.id)
            arguments = epiBundle
            //findNavController().navigate(R.id.epiFragment, arguments)
            findNavController().navigate(R.id.epiDetailFragment, arguments)

        }

        //Configura Recycler
        val recycler = binding.rvEpis
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item a lista quando for adicionado
        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updatePessoas(it)
        }

        // Navegar para a tela de cadastro de pessoa
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.epiFragment)
        }

        // Carregar as pessoas cadastradas
        viewModel.loadEpi(true)
    }

}