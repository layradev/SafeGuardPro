package com.happyhour.safeguardpro.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentListaEntregasBinding
import com.happyhour.safeguardpro.databinding.FragmentListaFuncionariosBinding
import com.happyhour.safeguardpro.view.adpater.EntregaAdapter
import com.happyhour.safeguardpro.view.adpater.FuncionarioAdapter
import com.happyhour.safeguardpro.viewmodel.EntregaViewModel
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel

class ListaEntregasFragment : Fragment() {

    private val viewModel: EntregaViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: EntregaAdapter

    // Criar o binding

    private var _binding: FragmentListaEntregasBinding? = null
    private val binding: FragmentListaEntregasBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaEntregasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Quando criar em algum item da lista
        adapter = EntregaAdapter(viewModel.entregaList.value) { funcionario ->
            val funcionarioBundle = Bundle()
            funcionarioBundle.putInt("funcionarioId", funcionario.id)
            arguments = funcionarioBundle
            findNavController().navigate(R.id.funcionarioFragment, arguments)
        }

        //Configura Recycler
        val recycler = binding.rvEntregas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item a lista quando for adicionado
        viewModel.entregaList.observe(viewLifecycleOwner) {
            adapter.updateEntregas(it)
        }

        // Navegar para a tela de cadastro de pessoa
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroFuncionarioFragment)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro funcionarios", it)
        }

        // Carregar as pessoas cadastradas
        viewModel.load()
    }
}