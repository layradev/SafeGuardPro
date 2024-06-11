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
import com.happyhour.safeguardpro.databinding.FragmentCadastroFuncionarioBinding
import com.happyhour.safeguardpro.databinding.FragmentFuncionarioBinding
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel

class CadastroFuncionarioFragment : Fragment() {

    // Chamar a viewmodel

    private val viewModel: FuncionarioViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: FuncionarioAdapter

    // Criar o binding

    private var _binding: FragmentFuncionarioBinding? = null
    private val binding: FragmentFuncionarioBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // configurar o binding
        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Configura Recycler
        val recycler = binding.rvFuncionario
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item a lista quando for adicionado
        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionarios(it)
        }

        // Navegar para a tela de cadastro de pessoa
        binding.btnCadastrar.setOnClickListener {
            findNavController().navigate(R.id.funcionarioFragment)
        }

        // Carregar as pessoas cadastradas
        viewModel.getFuncionarios()
    }

}