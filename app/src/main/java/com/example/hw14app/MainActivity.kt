package com.example.hw14app

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14app.databinding.ActivityMainBinding
import com.example.hw14app.repository.DictionaryRepository
import com.example.hw14app.viewModels.MainViewModel
import com.example.myapp.WordAdapter

class MainActivity : AppCompatActivity() {
    private val vModel : MainViewModel by viewModels()
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        binding.iconSplash.alpha= 0f
        binding.iconSplash.animate().setDuration(3000).alpha(1f).withEndAction {
            binding.navHostFragment.visibility = View.VISIBLE
            binding.iconSplash.visibility = View.GONE
            supportActionBar?.show()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }


}

class FirstDialogFragment : DialogFragment() {
    private val vModel : MainViewModel by viewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("کلمه موردنظر شما پیدا نشد!")
                .setTitle("جستجو")
                .setNeutralButton("باشه",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}