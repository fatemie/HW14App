package com.example.hw14app.views

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentMain2Binding
import com.example.hw14app.repository.DictionaryRepository
import com.example.hw14app.viewModels.MainViewModel
import com.example.myapp.WordAdapter

class MainFragment : Fragment() {

    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentMain2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMain2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setListener()
    }

    private fun setListener() {
        binding.fab.setOnClickListener {
            goToAddFragment()
        }
    }

    private fun goToAddFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_addWordFragment)
    }


    private fun initList() {
        var adapter = WordAdapter({ word -> vModel.goToWordDetail(word) })
        binding.wordsRecyclerView.adapter = adapter
        adapter.submitList(DictionaryRepository.dictionary)
    }
}