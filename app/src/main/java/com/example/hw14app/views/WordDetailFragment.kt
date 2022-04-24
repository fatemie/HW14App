package com.example.hw14app.views

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentWordDetailBinding
import com.example.hw14app.model.Word
import com.example.hw14app.viewModels.MainViewModel
import java.io.IOException


class WordDetailFragment : Fragment() {
    private val vModel: MainViewModel by activityViewModels()
    lateinit var binding : FragmentWordDetailBinding
    private var wordId = 0
    lateinit var word : Word
    var isRecording = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wordId = it.getInt("wordId",0)
        }
        word = vModel.getWord(wordId)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListener()
    }

    private fun setListener() {
        binding.btnDelete.setOnClickListener {
            vModel.deleteWord(word)
            Toast.makeText(activity, "کلمه با موفقیت حذف شد", Toast.LENGTH_SHORT).show()
            vModel.updateViews()
            findNavController().navigate(R.id.action_wordDetailFragment_to_mainFragment)
        }
        binding.btnEdit.setOnClickListener {
            binding.llEdit.isVisible = true
        }
        binding.buttonRegister.setOnClickListener {
            val newWord = Word(
                wordId,
                binding.editTextPersian.text.toString(),
                binding.editTextEnglish.text.toString(),
                binding.editTextExample.text.toString(),
                binding.editTextSynonym.text.toString(),
                binding.editTextWebLink.text.toString(),
                word.soundAddress
            )
            vModel.updateWord(newWord)
            binding.llEdit.isVisible = false
            vModel.updateViews()
            Toast.makeText(activity, "اطلاعات کلمه با موفقیت به روز شد", Toast.LENGTH_SHORT).show()
        }
        binding.buttonWeb.setOnClickListener {
            goToWebLink(word.weakiLink)
        }
        binding.buttonPlaySound.setOnClickListener {
            vModel.startPlaying(word.soundAddress)
        }
        binding.buttonRecord.setOnClickListener {
            binding.buttonRecord.setOnClickListener {
                if (isRecording) {
                    binding.buttonRecord.text = "ضبط صدا"
                    vModel.stopRecording()
                    Toast.makeText(activity, "صدا با موفقیت افزوده شد.", Toast.LENGTH_SHORT).show()

                } else {
                    binding.buttonRecord.text = "توقف ضبط"
                    vModel.startRecording(word.soundAddress)
                }
                isRecording = !isRecording
            }
        }
    }

    private fun goToWebLink(url: String) {
        val action = WordDetailFragmentDirections.actionWordDetailFragmentToWebViewFragment(url)
        findNavController().navigate(action)
    }

    private fun initViews() {
        binding.textViewPersian.text = word.persian
        binding.textViewEnglish.text = word.english
        binding.textViewSynonym.text = word.synonym
        binding.textViewExample.text = word.example

        binding.editTextPersian.setText(word.persian)
        binding.editTextEnglish.setText(word.english)
        binding.editTextSynonym.setText(word.synonym)
        binding.editTextExample.setText(word.example)
        binding.editTextWebLink.setText(word.weakiLink)
    }

}