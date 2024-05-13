package com.happyhour.safeguardpro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentListaFuncionariosBinding
import com.happyhour.safeguardpro.databinding.FragmentLoginBinding

class ListaFuncionariosFragment : Fragment() {

    private var _binding: FragmentListaFuncionariosBinding? = null
    private  val binding: FragmentListaFuncionariosBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaFuncionariosBinding.inflate(inflater, container, false)
        return binding.root
    }

}
