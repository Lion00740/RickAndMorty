package com.example.rickandmorty.ui.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.CharacterAdapter
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onLoading()
        setupRecycler(this@MainActivity)
        binding.toolBar.navigationIcon = null

        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.bookmarks -> {
                    binding.toolBar.setNavigationIcon(R.drawable.back)
                    viewModel.setState(false)
                    viewModel.getAllBookmarks()
                    true
                }
                R.id.search -> {
                    binding.toolBar.setNavigationIcon(R.drawable.back)
                    true
                }
                else -> false
            }
        }

        binding.toolBar.setNavigationOnClickListener {
            viewModel.setState(true)
            binding.toolBar.navigationIcon = null
            viewModel.getAllCharacters()
        }

        viewModel.getAllCharacters()

        viewModel.error.observe(this@MainActivity, Observer {
            if (it != null) {
                dialog(it)
            }
        })

        viewModel.list.observe(this@MainActivity, Observer {
            adapter.submitList(it)
            onResponse()
        })

        adapter.onItemClick = {
            val intent = Intent(this@MainActivity, DescriptionItemActivity::class.java).apply {
                putExtra("id", it.id)
            }
            startActivity(intent)
        }
    }
    //что-то в этом духе, но переделать, так как иногда вылетает список заметок
    override fun onResume() {
        super.onResume()

        viewModel.stateList.observe(this@MainActivity, Observer {
            if (it) {
                viewModel.getAllCharacters()
            } else {
                viewModel.getAllBookmarks()
            }
        })

        viewModel.list.observe(this@MainActivity, Observer {
            adapter.submitList(it)
        })
    }
    private fun dialog(errorMessage: String) {
        val dialogButtonListener = DialogInterface.OnClickListener { dialog, element ->
            when(element) {
                DialogInterface.BUTTON_POSITIVE -> dialog.cancel()
                DialogInterface.BUTTON_NEGATIVE -> finish()
            }
        }

        val errorDialog = AlertDialog.Builder(this)
            .setTitle("Проблема!")
            .setMessage(errorMessage)
            .setPositiveButton("Продолжить", dialogButtonListener)
            .setNegativeButton("Выход", dialogButtonListener)
            .create()

        errorDialog.show()
    }
    private fun onLoading() = with(binding) {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }
    private fun onResponse() = with(binding) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
    private fun setupRecycler(context: Context) = with(binding) {
        adapter = CharacterAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}