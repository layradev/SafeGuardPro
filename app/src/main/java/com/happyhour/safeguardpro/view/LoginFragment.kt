package com.happyhour.safeguardpro.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.R
import com.happyhour.safeguardpro.databinding.FragmentLoginBinding
import com.happyhour.safeguardpro.service.model.Login
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel


class LoginFragment : Fragment() {
    private val viewModelFuncionario: FuncionarioViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var senha = ""
        var cpf = ""

        binding.btnLogar.setOnClickListener {
            cpf = binding.edtCpf.editableText.toString()
            senha = binding.edtSenha.editableText.toString()

            if ((cpf.isBlank() || cpf.isEmpty()) || (senha.isBlank() || senha.isEmpty())) {
                Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
            } else {
                viewModelFuncionario.getFuncionarioByCpf(cpf)
            }
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            if (it.senha == senha && it.cpf == cpf) {
                Login.userConected(it.id, it.cpf, it.admin)

                if (it.admin) {
                    findNavController().navigate(R.id.inicalSupervisorFragment)
                } else {
                    findNavController().navigate(R.id.inicialFuncionarioFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Usuario ou senha inválidos", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}