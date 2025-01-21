package com.example.roomstev

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.roomstev.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LIstFragment2 : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_l_ist2, container, false)

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.action_LIstFragment2_to_addFragment22)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Add  Recyclerview
        var adapter = LAdapter()
        val recyclerV = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerV.adapter = adapter
        recyclerV.layoutManager = LinearLayoutManager(requireContext())

        //userViewModel
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            Log.d("TAGI", "my Data:: $user")
            adapter.submitList(user)
        })
       // adapter.submitList(userViewModel.readAllData.value)
    }

}