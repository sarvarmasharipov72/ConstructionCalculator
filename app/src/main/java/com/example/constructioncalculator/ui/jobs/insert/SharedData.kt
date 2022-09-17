package com.example.constructioncalculator.ui.jobs.insert

import com.example.constructioncalculator.model.Material

class SharedData {
    companion object {
        var sharedData: Material? = null
        var count: Byte = 0
        var counts: Byte = 100

        var name: String = ""
        var measurement = ""
        var price = ""
        var id = 1
        var list: MutableList<Material> = mutableListOf()
    }
}