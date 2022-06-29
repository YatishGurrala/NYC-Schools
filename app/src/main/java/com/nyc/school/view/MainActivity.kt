package com.nyc.school.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nyc.school.model.SchoolResponse
import com.nyc.school.R
import com.nyc.school.adapter.AdapterCallBack
import com.nyc.school.adapter.ListAdapter
import com.nyc.school.databinding.ActivityMainBinding
import com.nyc.school.repository.MainRepository
import com.nyc.school.repository.MyViewModelFactory
import com.nyc.school.repository.RetrofitService
import com.nyc.school.viewmodel.MainViewModel
import com.stone.vega.library.VegaLayoutManager


class MainActivity : AppCompatActivity(), AdapterCallBack {
    private lateinit var binding: ActivityMainBinding
    private var imageList: List<SchoolResponse> = emptyList()
    private lateinit var viewModel: MainViewModel
    private lateinit var listAdapter: ListAdapter
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        viewModel.getAllImages()

        //get imageList livedata from viewModel by observer
        liveDataObserve()

    }

    private fun liveDataObserve() {
        viewModel.schoolList.observe(this@MainActivity) {
            imageList = it
            binding.recyclerview.layoutManager = VegaLayoutManager()
            binding.recyclerview.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            listAdapter = ListAdapter(imageList, this@MainActivity)
            binding.recyclerview.adapter = listAdapter
        }

        //get error message livedata from viewModel by observer
        viewModel.errorMessage.observe(this) {
            binding.progressBar.visibility = View.GONE
            showAlert(getString(R.string.something_went_wrong))
        }
    }


    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { _, _ ->
        }
        builder.show()
    }


    override fun itemOnClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("schoolName", imageList[position].schoolName)
        intent.putExtra("totalStudents", imageList[position].totalStudents)
        intent.putExtra("phoneNumber", imageList[position].phoneNumber)
        intent.putExtra("emailId", imageList[position].schoolEmail)
        intent.putExtra("website", imageList[position].website)
        intent.putExtra("location", imageList[position].location)
        startActivity(intent)
    }
}