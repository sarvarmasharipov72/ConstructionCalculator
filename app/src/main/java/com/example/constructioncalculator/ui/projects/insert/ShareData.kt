package com.example.constructioncalculator.ui.projects.insert

import com.example.constructioncalculator.model.Jobs

class ShareData {
    companion object {
        var price: Int = 0
        var name: String = ""
        var list: MutableList<Jobs> = mutableListOf()
    }
}