package com.nyc.school.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyc.school.R
import com.nyc.school.databinding.DetailActivityBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent != null) {
            val schoolName = intent.getStringExtra("schoolName")
            val totalStudents = intent.getStringExtra("totalStudents")
            val phoneNumber = intent.getStringExtra("phoneNumber")
            val emailId = intent.getStringExtra("emailId")
            val website = intent.getStringExtra("website")
            val location = intent.getStringExtra("location")
//            Glide.with(this).load(imageUrl.toString()).into(binding.imageview)
            binding.name?.text = schoolName
            binding.totalStudents?.text = "Total students: $totalStudents"
            binding.phoneNumber?.text = "Phone number: $phoneNumber"
            binding.emailId?.text = "Email: $emailId"
            binding.website?.text = "Website: $website"
            binding.location?.text = location
        }
    }
}