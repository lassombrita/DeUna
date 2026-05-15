package com.servicios.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.servicios.app.R

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // This is a placeholder as the guide says HomeFragment already exists but we might need a Kotlin version if it was Java
        // or just to satisfy the nav_graph.
        return inflater.inflate(R.layout.activity_service_list, container, false)
    }
}