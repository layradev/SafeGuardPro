package com.happyhour.safeguardpro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.happyhour.safeguardpro.databinding.FragmentEntregaBinding
import com.happyhour.safeguardpro.databinding.FragmentFuncionarioBinding
import com.happyhour.safeguardpro.viewmodel.EpiViewModel
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel
import java.time.LocalDateTime

class EpiFragment : Fragment() {
    private val viewModel : EpiViewModel by viewModels()

    private var _binding : FragmentCadastroEpiBinding? = null

    private val binding: FragmentCadastroEpiBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, Container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroEpiBinding.inflate(inflater, Container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnCadastrar.setOnClickListener{
            val ca = binding.editCadastro.editableText.toString()
            val validade = binding.editValidade.editableText.toString()
            val data_de_validade = binding.editDataValidade.editableText.toString()
        }


        //viewModel.getEpi()

        viewModel.epi.observe(viewLifecycleOwner){epi ->
            binding.editCadastro.setText((epi.nome))
            binding.editValidade.setText((LocalDateTime.now().year + epi.validade.toInt()).toString())

        }

        val nome = binding.editDataValidade.editableText.toString()
        val ca = binding.editValidade.editableText.toString()
        val validade = binding.editValidade.editableText.toString()
    }

}

