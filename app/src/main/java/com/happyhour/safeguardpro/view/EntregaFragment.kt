package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentCadastroFuncionarioBinding
import com.happyhour.safeguardpro.databinding.FragmentEntregaBinding
import com.happyhour.safeguardpro.viewmodel.EntregaViewModel
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel

class EntregaFragment : Fragment() {

    private val viewModel : EntregaViewModel by viewModels()

    private var _binding: FragmentEntregaBinding? = null
    private val binding: FragmentEntregaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntregaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConcluir.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
        //viewModel.getFuncionarios()

        viewModel.entrega.observe(viewLifecycleOwner){

        }

        binding.btnConcluir.setOnClickListener{
//            val data_entrega = binding.e .editableText.toString()
            val id = ""
            val funcionario_id = ""

        }
    }
}