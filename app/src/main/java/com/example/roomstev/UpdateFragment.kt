package com.example.roomstev

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.example.roomstev.data.User
import com.example.roomstev.data.UserViewModel
import com.example.roomstev.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_update, container, false)
        _binding = FragmentUpdateBinding.bind(view) // Initialize _binding
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.updateFname.setText(args.currentUser.firstName)
        binding.updateLname.setText(args.currentUser.lastName)
        binding.updateAge.setText(args.currentUser.age.toString())

        binding.buttonUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

        private fun updateItem(){
            val firstName = binding.updateFname.text.toString()
            val lastName = binding.updateLname.text.toString()
            val age = binding.updateAge.text.toString().toInt()
            if(inputCheck(firstName, lastName, age.toString())){
                //create user object
                val updatedUser = User(args.currentUser.id, firstName, lastName, age)
                //update current user
                mViewModel.updateUser(updatedUser)
                Toast.makeText(requireContext(), "Updated Successfully!!", Toast.LENGTH_SHORT).show()
                //navigate back
                findNavController().navigate(R.id.action_updateFragment_to_LIstFragment2)
            }else{
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

    private fun inputCheck(fname: String, lname: String, age: String): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && TextUtils.isEmpty(age))
    }

}