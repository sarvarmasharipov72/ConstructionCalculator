package com.example.constructioncalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.constructioncalculator.utils.Constants
import com.example.constructioncalculator.R
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.ui.jobs.JobsFragmentDirections
import com.example.constructioncalculator.ui.materials.MaterialFragmentDirections
import com.example.constructioncalculator.ui.projects.ProjectFragmentDirections

class UiAdapter(private val context: String): RecyclerView.Adapter<UiAdapter.UIHolder>() {
    private var listJobs = emptyList<Jobs>()
    private var listMaterial = emptyList<Material>()
    private var listProject = emptyList<Project>()
    class UIHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val container = itemView.findViewById<ConstraintLayout>(R.id.projectContainer)
        private val name = itemView.findViewById<TextView>(R.id.projectName)
        private val price = itemView.findViewById<TextView>(R.id.projectPrice)
        private val date = itemView.findViewById<TextView>(R.id.projectDate)
        fun bind(materialModel: Material) {
            name.text = materialModel.name
            price.text = materialModel.price.toString() + " so'm"
            date.visibility = View.GONE
        }

        fun bind(jobs: Jobs) {
            name.text = jobs.name
            price.text = jobs.price.toString() + " so'm"
            date.visibility = View.GONE
        }

        fun bind(project: Project) {
            name.text = project.name
            price.text = project.price.toString() + " so'm"
            date.text = project.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UIHolder {
        return UIHolder(LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent, false))
    }

    override fun onBindViewHolder(holder: UIHolder, position: Int) {
        when (context) {
            Constants.MATERIAL -> holder.bind(listMaterial[position])
            Constants.JOBS -> holder.bind(listJobs[position])
            else -> holder.bind(listProject[position])
        }
        holder.container.setOnClickListener {
            when (context) {
            Constants.MATERIAL -> {
                val action = MaterialFragmentDirections.actionMaterialFragmentToMaterialReadFragment(listMaterial[position])
                holder.itemView.findNavController().navigate(action)
            }
            Constants.JOBS -> {
                val action = JobsFragmentDirections.actionJobsFragmentToJobReadFragment(listJobs[position])
                holder.itemView.findNavController().navigate(action)
            }
            else -> {
                val action = ProjectFragmentDirections.actionProjectFragmentToProjectReadFragment(listProject[position])
                holder.itemView.findNavController().navigate(action)
            }
        }
        }
    }

    override fun getItemCount() = when {
        listJobs.isNotEmpty() -> listJobs.size
        listMaterial.isNotEmpty() -> listMaterial.size
        else -> listProject.size
    }

    fun setJobs(list: List<Jobs>) {
        this.listJobs = list
        notifyDataSetChanged()
    }

    fun setProject(list: List<Project>) {
        this.listProject = list
        notifyDataSetChanged()
    }

    fun setMaterial(list: List<Material>) {
        this.listMaterial = list
        notifyDataSetChanged()
    }
}