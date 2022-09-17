package com.example.constructioncalculator.model.mapper

import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import com.example.constructioncalculator.model.Project
import com.example.constructioncalculator.model.entity.JobEntity
import com.example.constructioncalculator.model.entity.MaterialEntity
import com.example.constructioncalculator.model.entity.ProjectEntity

fun Jobs.toJobEntity() = JobEntity(
    id = id,
    name = name,
    measurement = measurement,
    price = price,
    materialList = materialList,
    count = count
)

fun JobEntity.toJob() = Jobs(
    id = id,
    name = name,
    measurement = measurement,
    price = price,
    materialList = materialList,
    count = count
)

fun MaterialEntity.toMaterial() = Material(
    id = id,
    name = name,
    measurement = measurement,
    price = price,
    percent = percent
)

fun Material.toMaterialEntity() = MaterialEntity(
    id = id,
    name = name,
    measurement = measurement,
    price = price,
    percent = percent
)

fun Project.toProjectEntity() = ProjectEntity(
    id = id,
    name = name,
    price = price,
    date = date,
    jobList = jobList
)

fun ProjectEntity.toProject() = Project(
    id = id,
    name = name,
    price = price,
    date = date,
    jobList = jobList
)