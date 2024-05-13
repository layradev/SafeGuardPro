package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.happyhour.safeguardpro.databinding.FragmentDetalhesEpiBinding

class DetalhesEpiFragment : Fragment() {

    private var _binding: FragmentDetalhesEpiBinding? = null
    private val binding: FragmentDetalhesEpiBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

}