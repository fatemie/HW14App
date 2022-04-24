package com.example.hw14app.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14app.R
import com.example.hw14app.databinding.FragmentAddWordBinding
import com.example.hw14app.model.Word
import com.example.hw14app.viewModels.MainViewModel
import java.util.*


private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class AddWordFragment : Fragment() {
    lateinit var binding: FragmentAddWordBinding
    private val vModel: MainViewModel by activityViewModels()
    var now = Date().time.toString()
    private var fileName: String = ""
    var isRecording = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.buttonRegister.setOnClickListener {
            val word = Word(
                0,
                binding.editTextPersian.text.toString(),
                binding.editTextEnglish.text.toString(),
                binding.editTextExample.text.toString(),
                binding.editTextSynonym.text.toString(),
                binding.editTextWebLink.text.toString(),
                fileName
            )
            vModel.addWord(word)
            Toast.makeText(activity, "کلمه جدید با موفقیت افزوده شد.", Toast.LENGTH_SHORT).show()
            vModel.updateViews()
            findNavController().navigate(R.id.action_addWordFragment_to_mainFragment)
        }
        binding.buttonRecord.setOnClickListener {
            activity?.let { it1 ->
                ActivityCompat.requestPermissions(
                    it1,
                    permissions,
                    REQUEST_RECORD_AUDIO_PERMISSION
                )
            }
            if (isRecording) {
                binding.buttonRecord.text = "ضبط صدا"
                vModel.stopRecording()
                Toast.makeText(activity, "صدا با موفقیت افزوده شد.", Toast.LENGTH_SHORT).show()

            } else {
                binding.buttonRecord.text = "توقف ضبط"
                fileName = "${activity?.externalCacheDir?.absolutePath}/audiorecordtest$now.3gp"
                vModel.startRecording(fileName)
            }
            isRecording = !isRecording
        }
    }


    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) {
            Toast.makeText(activity, "no permission", Toast.LENGTH_SHORT).show()
        }

    }

}