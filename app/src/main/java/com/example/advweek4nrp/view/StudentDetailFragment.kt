package com.example.advweek4nrp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek4nrp.R
import com.example.advweek4nrp.databinding.FragmentStudentDetailBinding
import com.example.advweek4nrp.databinding.FragmentStudentListBinding
import com.example.advweek4nrp.viewmodel.DetailViewModel
import com.example.advweek4nrp.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtStudentId.setText(it.id)
            binding.txtStudentName.setText(it.name)
            binding.txtDob.setText(it.bod)
            binding.txtPhone.setText(it.phone)
        })
    }
}