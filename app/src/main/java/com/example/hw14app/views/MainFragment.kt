package com.example.hw14app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentMain2Binding
import com.example.hw14app.model.Word
import com.example.hw14app.viewModels.MainViewModel
import com.example.myapp.WordAdapter

class MainFragment : Fragment() {

    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentMain2Binding
    var searchOpen = false
    var word: Word? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMain2Binding.inflate(inflater, container, false)
        binding.wordCount = vModel.wordCountLiveData.value!!.toString()
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
        binding.buttonSearch.setOnClickListener {
            binding.searchCardView.isVisible = !searchOpen
            searchOpen = !searchOpen
        }
        binding.buttonSearch2.setOnClickListener {
            if (binding.persianSearch.text.isNullOrEmpty() &&
                !binding.englishSearch.text.isNullOrEmpty()
            ) {
                word = vModel.getWordWithEnglish(binding.englishSearch.text.toString())
            } else if (binding.englishSearch.text.isNullOrEmpty() &&
                !binding.persianSearch.text.isNullOrEmpty()
            ) {
                word = vModel.getWordWithPersian(binding.persianSearch.text.toString())
            }
            searchResult(word)
        }
    }

    private fun goToAddFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_addWordFragment)
    }

    private fun goToWordDetail(word: Word) {
        val action = MainFragmentDirections.actionMainFragmentToWordDetailFragment(word.id)
        findNavController().navigate(action)
    }

    fun searchResult(word: Word?) {
        if(word != null){
            goToWordDetail(word)
        }else{
            Toast.makeText(activity, "کلمه مورد نظر پیدا نشد", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initList() {
        var adapter = WordAdapter({ word -> goToWordDetail(word) })
        binding.wordsRecyclerView.adapter = adapter
        adapter.submitList(vModel.dictionary)
    }


}