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
import com.happyhour.safeguardpro.databinding.FragmentListaEpiBinding
import com.happyhour.safeguardpro.service.model.Epi
import com.happyhour.safeguardpro.service.model.Login
import com.happyhour.safeguardpro.view.adpater.EpiAdapter
import com.happyhour.safeguardpro.viewmodel.EntregaViewModel
import com.happyhour.safeguardpro.viewmodel.EpiViewModel


class ListaEpiFragment : Fragment() {

    // Chamar a viewmodel
    private val viewModel: EpiViewModel by viewModels()
    private val viewModelEmprestimo: EntregaViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: EpiAdapter

    private val episFuncionario = mutableListOf<Epi>()

    // Criar o binding
    private var _binding: FragmentListaEpiBinding? = null
    private val binding: FragmentListaEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

            findNavController().navigate(R.id.cadastroEpiFragment, arguments)
//            TODO Melhorar a tela de detalhes para trocar de tela ou deixar apenas na tela de cadastro
//            if (Login.userAdmin) {
//                findNavController().navigate(R.id.epiFragment, arguments)
//            } else {
//                findNavController().navigate(R.id.detalhesEpiFragment, arguments)
//            }
        }

        //Configura Recycler
        val recycler = binding.rvEpis
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //Observa para adicionar um item a lista quando for adicionado
        viewModel.epiList.observe(viewLifecycleOwner) {
            adapter.updateEpis(it)
        }

        // Navegar para a tela de cadastro de pessoa
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.cadastroEpiFragment)
        }

        viewModel.epi.observe(viewLifecycleOwner) { epi ->
            episFuncionario.add(epi)
//            TODO testar aqui ou na linha 81
            adapter.updateEpis(episFuncionario)
        }

        viewModelEmprestimo.entregaList.observe(viewLifecycleOwner) { listEntregas ->
            val entregasFuncionario = listEntregas.filter { it.funcionario_id == Login.userId }

            entregasFuncionario.forEach {
                viewModel.getEpi(it.epi_id)
            }

//            TODO testar aqui ou na linha 65
            adapter.updateEpis(episFuncionario)
            Toast.makeText(requireContext(), "Epis: $listEntregas", Toast.LENGTH_LONG).show()
        }

        if (Login.userAdmin){
            // Carregar as pessoas cadastradas
            viewModel.getEpis()
        } else {
            viewModelEmprestimo.load()
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro funcionarios", it)
        }
    }
}