package com.happyhour.safeguardpro.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.happyhour.safeguardpro.databinding.FragmentCadastroEpiBinding
import com.happyhour.safeguardpro.service.model.Epi
import com.happyhour.safeguardpro.service.model.Login
import com.happyhour.safeguardpro.viewmodel.EpiViewModel


class CadastroEpiFragment : Fragment() {
    private val viewModel: EpiViewModel by viewModels()

    private var _binding: FragmentCadastroEpiBinding? = null
    private val binding: FragmentCadastroEpiBinding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding =FragmentCadastroEpiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getEpi(it.getInt("epiId"))
        }

        binding.btnCadastrar.setOnClickListener {
            val nome_equipamento = binding.editNome.editableText.toString()
            val validade_epi = binding.editDataValidade.editableText.toString()
            val fabricacao = binding.editFabricacao.editableText.toString()
            val numero_ca = binding.editCa.editableText.toString().toInt()
            val tempo_uso = binding.editTempoUso.editableText.toString()
            val modo_uso = binding.editModoUso.editableText.toString()

            if (nome_equipamento != "" && validade_epi != "" && fabricacao != "" && numero_ca != 0 && tempo_uso != "" && tempo_uso != "") {
                val epi = Epi(
                    nome = nome_equipamento,
                    data_fabricacao = validade_epi,
                    tempo_uso = tempo_uso,
                    validade = fabricacao,
                    ca = numero_ca,
                    modo_uso = modo_uso,
                )

                viewModel.epi.value?.let {
                    epi.id = it.id
                    viewModel.update(epi)
                } ?: run {
                    viewModel.insert(epi)
                }

                binding.editNome.editableText.clear()
                binding.editDataValidade.editableText.clear()
                binding.editFabricacao.editableText.clear()
                binding.editCa.editableText.clear()
                binding.editTempoUso.editableText.clear()
                binding.editModoUso.editableText.clear()

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnExcluir.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de EPI")
                .setMessage("você realmente deseja excluir ?")
                .setPositiveButton("sim") { _, _ ->
                    viewModel.delete(viewModel.epi.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("não") { _, _ -> }
                .show()
        }

        viewModel.epi.observe(viewLifecycleOwner) { epi ->
            if (Login.userAdmin) {
                binding.editNome.setText(epi.nome)
                binding.editDataValidade.setText(epi.validade)
                binding.editFabricacao.setText(epi.data_fabricacao)
                binding.editCa.setText(epi.ca)
                binding.editTempoUso.setText(epi.tempo_uso)
                binding.editModoUso.setText(epi.modo_uso)

                binding.btnExcluir.visibility = View.VISIBLE
            } else {
                binding.editNome.isClickable = false
                binding.editDataValidade.isClickable = false
                binding.editFabricacao.isClickable = false
                binding.editCa.isClickable = false
                binding.editTempoUso.isClickable = false
                binding.editModoUso.isClickable = false

                binding.btnCadastrar.visibility = View.GONE
            }
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}