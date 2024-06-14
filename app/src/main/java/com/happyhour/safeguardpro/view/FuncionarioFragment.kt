package com.happyhour.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.databinding.FragmentFuncionarioBinding
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.service.model.Login
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel
import java.util.zip.Inflater

class FuncionarioFragment : Fragment() {

    private val viewModel : FuncionarioViewModel by viewModels()

    private var _binding : FragmentFuncionarioBinding? = null

    private val binding: FragmentFuncionarioBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getFuncionario(it.getInt("funcionarioId"))
        }

        binding.btnCadastrar.setOnClickListener {
            val nome = binding.edtNome.editableText.toString()
            val senha = binding.edtSenha.editableText.toString()
            val cargo = binding.edtCargo.editableText.toString()
            val cpf = binding.edtCpf.editableText.toString()
            val admin = binding.chkAdmin.isChecked

            if (nome != "" && senha != "" && cargo != "" && cpf != "") {
                val funcionario = Funcionario(
                    nome = nome,
                    cpf = cpf,
                    cargo = cargo,
                    senha = senha,
                    admin = admin
                )

                viewModel.funcionario.value?.let {
                    funcionario.id = it.id
                    viewModel.update(funcionario)
                } ?: run {
                    viewModel.insert(funcionario)
                }

                binding.edtNome.editableText.clear()
                binding.edtCpf.editableText.clear()
                binding.edtCargo.editableText.clear()
                binding.edtSenha.editableText.clear()
                binding.chkAdmin.isChecked = false

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.funcionario.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.funcionario.observe(viewLifecycleOwner) { funcionario ->
            binding.edtNome.setText(funcionario.nome)
            binding.edtCargo.setText(funcionario.cargo)
            binding.edtCpf.setText(funcionario.cpf)
            binding.edtSenha.setText(funcionario.senha)

            if (Login.userAdmin) {
                binding.btnDeletar.visibility = View.VISIBLE
            } else {
                binding.edtNome.isClickable = false
                binding.edtCargo.isClickable = false
                binding.edtCpf.isClickable = false
                binding.edtSenha.visibility = View.GONE
                binding.chkAdmin.visibility = View.GONE

                binding.btnCadastrar.visibility = View.GONE
            }
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}