package com.happyhour.safeguardpro.view.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.happyhour.safeguardpro.databinding.ListItemEpiBinding
import com.happyhour.safeguardpro.service.model.Epi

class EpiAdapter(epis: List<Epi>?, private val clickListListener: (Epi) -> Unit) :
    RecyclerView.Adapter<EpiAdapter.EpiViewHolder>() {

    //Criar uma lista de pessoas
    private var epiList: List<Epi> = arrayListOf()

    //Carrega as informações da pessoa na lista
    class EpiViewHolder(private val binding: ListItemEpiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(epi: Epi, clickListListener: (Epi) -> Unit) {
            binding.tvNome.text = epi.nome
            binding.tvDataFabricacao.text = epi.data_fabricacao
            binding.tvModoUso.text = epi.modo_uso
            binding.tvTempoUso.text = epi.tempo_uso
            binding.tvCa.text = epi.ca.toString()
            binding.tvValidade.text = epi.validade

            binding.root.setOnClickListener {
                clickListListener(epi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpiViewHolder {
        val listItemEpiBinding =
            ListItemEpiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpiViewHolder(listItemEpiBinding)
    }

    override fun getItemCount(): Int {
        return epiList.count()
    }

    override fun onBindViewHolder(holder: EpiViewHolder, position: Int) {
        holder.bind(epiList[position], clickListListener)
    }

    fun updateEpis(list: List<Epi>) {
        epiList = list
        notifyDataSetChanged()
    }
}