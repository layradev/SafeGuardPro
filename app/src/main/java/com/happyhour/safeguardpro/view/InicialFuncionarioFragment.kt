package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentHomeBinding
import com.happyhour.safeguardpro.databinding.FragmentInicialFuncionarioBinding


class InicialFuncionarioFragment : Fragment() {

    private var _binding: FragmentInicialFuncionarioBinding? = null
    private  val binding: FragmentInicialFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicialFuncionarioBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_inicial_funcionario, container, false)
    }

}
