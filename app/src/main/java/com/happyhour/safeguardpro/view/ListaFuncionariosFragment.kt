package com.happyhour.safeguardpro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentFuncionarioBinding
import com.happyhour.safeguardpro.databinding.FragmentListaFuncionariosBinding
import com.happyhour.safeguardpro.databinding.FragmentLoginBinding
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel

class ListaFuncionariosFragment : Fragment() {

    // Chamar a viewmodel

    private val viewModel: FuncionarioViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: FuncionarioAdapter

    // Criar o binding

    private var _binding: FragmentListaFuncionariosBinding? = null
    private val binding: FragmentListaFuncionariosBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // configurar o binding
        _binding = FragmentListaFuncionariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando criar em algum item da lista
        adapter = FuncionarioAdapter(viewModel.funcionarioList.value) {funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            //findNavController().navigate(R.id.funcionarioFragment, arguments)
            findNavController().navigate(R.id.funcionarioDetailFragment, arguments)
        }

        //Configura Recycler
        val recycler = binding.rvFuncionario
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item a lista quando for adicionado
        viewModel.funcionarioList.observe(viewLifecycleOwner) {
            adapter.updateFuncionarios(it)
        }

        // Navegar para a tela de cadastro de pessoa
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroFuncionarioFragment)
        }

        // Carregar as pessoas cadastradas
        viewModel.getFuncionarios()
    }

}