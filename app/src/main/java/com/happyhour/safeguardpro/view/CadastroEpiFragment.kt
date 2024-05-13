package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.happyhour.safeguardpro.databinding.FragmentCadastroFuncionarioBinding
import com.happyhour.safeguardpro.databinding.FragmentEntregaBinding


class CadastroEpiFragment : Fragment() {

    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding =FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

}