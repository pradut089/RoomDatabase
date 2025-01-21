package com.example.roomstev

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.roomstev.data.User
import com.example.roomstev.data.UserViewModel

class AddFragment2 : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add2, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //view.findViewById<EditText>(R.id.fname).setText("hello")
        view.findViewById<Button>(R.id.button_add).setOnClickListener{
            insertDataToDatabase()
        }


        return view
    }

    private fun insertDataToDatabase() {
        val fname = view?.findViewById<EditText>(R.id.fname)?.text.toString()
        val lname = view?.findViewById<EditText>(R.id.lname)?.text.toString()
        val age = view?.findViewById<EditText>(R.id.age)?.text.toString()

        if(inputCheck(fname, lname, age)){
            val user = User(0, fname, lname, Integer.parseInt(age))
            mUserViewModel.addUser(user)
            Toast.makeText(requireActivity(), "Successfully Added", Toast.LENGTH_SHORT).show()
           // findNavController().navigate(R.id.action_addFragment22_to_LIstFragment2)
            //below line is to navigate back to previous fragment and remove this fragment from backstack
            Navigation.findNavController(requireView()).navigateUp()

        }else{
            Toast.makeText(requireActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fname: String, lname: String, age: String): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && TextUtils.isEmpty(age))
    }


}