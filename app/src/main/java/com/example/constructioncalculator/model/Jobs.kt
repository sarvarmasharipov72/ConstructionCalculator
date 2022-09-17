package com.example.constructioncalculator.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jobs(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val measurement: String,
    val price: Int,
    val materialList: List<Material>,
    val count: Int
) : Parcelable {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Jobs>() {
            override fun areItemsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
                return oldItem == newItem
            }
        }
    }
}