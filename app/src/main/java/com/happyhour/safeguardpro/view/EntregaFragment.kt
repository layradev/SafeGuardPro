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
import com.happyhour.safeguardpro.databinding.FragmentEntregaBinding
import com.happyhour.safeguardpro.service.model.Entrega
import com.happyhour.safeguardpro.service.model.Epi
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.viewmodel.EntregaViewModel
import com.happyhour.safeguardpro.viewmodel.EpiViewModel
import com.happyhour.safeguardpro.viewmodel.FuncionarioViewModel
import java.time.LocalDateTime

class EntregaFragment : Fragment() {

    private val viewModel : EntregaViewModel by viewModels()
    private val viewModelFuncionario: FuncionarioViewModel by viewModels()
    private val viewModelEpi: EpiViewModel by viewModels()

    private lateinit var epiByCa: Epi
    private lateinit var funcionarioByCpf: Funcionario

    private var _binding: FragmentEntregaBinding? = null
    private val binding: FragmentEntregaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntregaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.getEntrega(it.getInt("entregaId"))
        }

        binding.btnConcluir.setOnClickListener {
            val cpf = binding.edtCpf.editableText.toString()
            val numero_ca = binding.edtCa.editableText.toString().toInt()
            val data_entrega = LocalDateTime.now().toLocalDate().toString()

            if (numero_ca != 0 && cpf != "") {

                viewModelEpi.getEpiByCa(numero_ca)
                viewModelFuncionario.getFuncionarioByCpf(cpf)

                val entrega = Entrega(
                    data_entrega = data_entrega,
                    epi_id = epiByCa.id,
                    funcionario_id = funcionarioByCpf.id,
                )

                viewModel.entrega.value?.let {
                    entrega.id = it.id
                    viewModel.update(entrega)
                } ?: run {
                    viewModel.insert(entrega)
                }

                binding.edtCpf.editableText.clear()
                binding.edtCa.editableText.clear()

                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }
        }

       // nao vai ter exclusao

        viewModelEpi.epi.observe(viewLifecycleOwner) {
            epiByCa = it
            binding.edtCa.setText(it.ca)
        }

        viewModelFuncionario.funcionario.observe(viewLifecycleOwner) {
            funcionarioByCpf = it
            binding.edtCpf.setText(it.cpf)
        }

        viewModel.erro.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Erro $it", Toast.LENGTH_LONG).show()
            Log.e("erro Emprestimo", it)
        }
    }
}