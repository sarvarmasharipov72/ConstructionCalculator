package com.example.constructioncalculator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.constructioncalculator.data.database.dao.JobDao
import com.example.constructioncalculator.data.database.dao.MaterialDao
import com.example.constructioncalculator.data.database.dao.ProjectDao
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.model.entity.JobEntity
import com.example.constructioncalculator.model.entity.MaterialEntity
import com.example.constructioncalculator.model.entity.ProjectEntity

@Database(
    version = 1,
    entities = [ProjectEntity::class, JobEntity::class, MaterialEntity::class],
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class ConstructionDatabase : RoomDatabase() {
    abstract fun materialDao(): MaterialDao
    abstract fun jobDao(): JobDao
    abstract fun projectDao(): ProjectDao

    companion object {

        private var INSTANCE: ConstructionDatabase? = null

        fun newInstance(context: Context): ConstructionDatabase {
            return INSTANCE ?: synchronized(this) {

                var tempInstance = Room.databaseBuilder(
                    context,
                    ConstructionDatabase::class.java,
                    "calculatorDB"
                )
                    .build()
                INSTANCE = tempInstance
                tempInstance
            }
        }
    }
}