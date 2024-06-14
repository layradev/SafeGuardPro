package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.happyhour.safeguardpro.databinding.FragmentInicialSupervisorBinding

class InicialSupervisorFragment : Fragment() {

    private var _binding: FragmentInicialSupervisorBinding? = null
    private val binding: FragmentInicialSupervisorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicialSupervisorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEntrega.setOnClickListener {
            findNavController().navigate(R.id.listaEntregasFragment)
        }

        binding.btnListaEpi.setOnClickListener {
            findNavController().navigate(R.id.listaEpiFragment)
        }

        binding.btnLista.setOnClickListener {
            findNavController().navigate(R.id.listaFuncionariosFragment)

        }
    }
}