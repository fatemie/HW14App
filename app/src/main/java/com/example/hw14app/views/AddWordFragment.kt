package com.example.hw14app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentAddWordBinding
import com.example.hw14app.model.Word
import com.example.hw14app.viewModels.MainViewModel

class AddWordFragment : Fragment() {
    lateinit var binding: FragmentAddWordBinding
    private val vModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.buttonRegister.setOnClickListener {
            var word = Word(
                0,
                binding.editTextPersian.text.toString(),
                binding.editTextEnglish.text.toString(),
                binding.editTextExample.text.toString(),
                binding.editTextSynonym.text.toString(),
                binding.editTextWebLink.text.toString()
            )
            vModel.addWord(word)
            Toast.makeText(activity, "کلمه جدید با موفقیت افزوده شد.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addWordFragment_to_mainFragment)
        }
    }

}