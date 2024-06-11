package com.happyhour.safeguardpro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.happyhour.safeguardpro.databinding.FragmentFuncionarioBinding
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel
import java.util.zip.Inflater

class FuncionarioFragment : Fragment() {

    private val viewModel : FuncionarioViewModel by viewModels()

    private var _binding : FragmentFuncionarioBinding? = null

    private val binding: FragmentFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, Container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFuncionarioBinding.inflate(inflater, Container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        //viewModel.getFuncionarios()

        viewModel.funcionario.observe(viewLifecycleOwner){

        }

        val nome = binding.edtNome.editableText.toString()
    }

}