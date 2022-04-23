package com.example.hw14app.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentWordDetailBinding
import com.example.hw14app.viewModels.MainViewModel



class WordDetailFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding : FragmentWordDetailBinding
    private var wordId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wordId = it.getInt("wordId",0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val word = vModel.getWord(wordId)
        binding.textViewPersian.text = word?.persian
        binding.textViewEnglish.text = word?.english
        binding.textViewSynonym.text = word?.synonym
        binding.textViewExample.text = word?.example
        binding.textViewWebLink.text = word?.weakiLink
    }

}