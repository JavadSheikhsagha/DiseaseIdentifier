package com.a2mp.diseaseidentifier.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a2mp.diseaseidentifier.databinding.RvDiseasesItemBinding
import com.a2mp.diseaseidentifier.models.DiseaseModel
import com.squareup.picasso.Picasso

class RvDiseasesAdapter(
    val list : List<DiseaseModel>
) : RecyclerView.Adapter<RvDiseasesAdapter.RvDiseasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvDiseasesViewHolder {
        val binding = RvDiseasesItemBinding.inflate(LayoutInflater.from(parent.context))
        return RvDiseasesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvDiseasesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class RvDiseasesViewHolder(val binding: RvDiseasesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diseaseModel: DiseaseModel) {

            Picasso.get().load(diseaseModel.similar_images?.get(0)?.url).into(binding.profileImage)

            binding.txtTitle.text = diseaseModel.name

        }
    }

}