package com.example.constructioncalculator.di

import android.content.Context
import com.example.constructioncalculator.MainActivity
import com.example.constructioncalculator.data.database.ConstructionDatabase
import com.example.constructioncalculator.data.database.dao.JobDao
import com.example.constructioncalculator.data.database.dao.MaterialDao
import com.example.constructioncalculator.data.database.dao.ProjectDao
import com.example.constructioncalculator.data.repository.JobRepository
import com.example.constructioncalculator.data.repository.MaterialRepository
import com.example.constructioncalculator.data.repository.ProjectRepository
import com.example.constructioncalculator.data.repository.impl.JobRepositoryImpl
import com.example.constructioncalculator.data.repository.impl.MaterialRepositoryImpl
import com.example.constructioncalculator.data.repository.impl.ProjectRepositoryImpl
import com.example.constructioncalculator.ui.home.HomeFragment
import com.example.constructioncalculator.ui.jobs.JobsFragment
import com.example.constructioncalculator.ui.jobs.insert.InsertJobFragment
import com.example.constructioncalculator.ui.jobs.insert.add.AddMaterialFragment
import com.example.constructioncalculator.ui.jobs.read.JobReadFragment
import com.example.constructioncalculator.ui.jobs.update.JobUpdateFragment
import com.example.constructioncalculator.ui.materials.MaterialFragment
import com.example.constructioncalculator.ui.materials.insert.InsertMaterialFragment
import com.example.constructioncalculator.ui.materials.read.MaterialReadFragment
import com.example.constructioncalculator.ui.materials.update.MaterialUpdateFragment
import com.example.constructioncalculator.ui.projects.ProjectFragment
import com.example.constructioncalculator.ui.projects.insert.ProjectInsertFragment
import com.example.constructioncalculator.ui.projects.insert.add.AddJobToProjectFragment
import com.example.constructioncalculator.ui.projects.read.ProjectReadFragment
import dagger.*
import javax.inject.Singleton


@Component(modules = [DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(jobsFragment: JobsFragment)
    fun inject(insertJobsFragment: InsertJobFragment)
    fun inject(addMaterialFragment: AddMaterialFragment)
    fun inject(jobReadFragment: JobReadFragment)
    fun inject(jobUpdateFragment: JobUpdateFragment)
    fun inject(materialFragment: MaterialFragment)
    fun inject(insertMaterialFragment: InsertMaterialFragment)
    fun inject(materialReadFragment: MaterialReadFragment)
    fun inject(materialUpdateFragment: MaterialUpdateFragment)
    fun inject(projectFragment: ProjectFragment)
    fun inject(projectInsertFragment: ProjectInsertFragment)
    fun inject(addJobToProjectFragment: AddJobToProjectFragment)
    fun inject(projectReadFragment: ProjectReadFragment)

}

@Module(includes = [RepositoryModule::class])
class DatabaseModule {

    @Provides
    fun providesConstructionDatabase(context: Context) = ConstructionDatabase.newInstance(context)

    @Provides
    fun provideMaterialDao(constructionDatabase: ConstructionDatabase): MaterialDao {
        return constructionDatabase.materialDao()
    }
    @Provides
    fun provideJobDao(constructionDatabase: ConstructionDatabase): JobDao {
        return constructionDatabase.jobDao()
    }
    @Provides
    fun provideProjectDao(constructionDatabase: ConstructionDatabase): ProjectDao {
        return constructionDatabase.projectDao()
    }

}

@Module
interface RepositoryModule {

    @Binds
    fun providesJobRepository(jobRepositoryImpl: JobRepositoryImpl): JobRepository

    @Binds
    fun providesMaterialRepository(materialRepositoryImpl: MaterialRepositoryImpl): MaterialRepository

    @Binds
    fun providesProjectRepository(projectRepositoryImpl: ProjectRepositoryImpl): ProjectRepository
}