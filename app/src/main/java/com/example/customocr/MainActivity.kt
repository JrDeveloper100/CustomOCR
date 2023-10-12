package com.example.customocr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ocrmodule.OCR

class MainActivity : AppCompatActivity() {
    private lateinit var tv_result : TextView
    private lateinit var btnSelectImage : Button
    private lateinit var imageView : ImageView
    private val REQUEST_IMAGE_SELECTION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_result = findViewById(R.id.tv_result)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        imageView = findViewById(R.id.imageView)
        btnSelectImage.setOnClickListener {
            openGallery()
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_SELECTION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_SELECTION && resultCode == RESULT_OK && data != null){
            val imageUri: Uri? = data.data
            if (imageUri != null) {
                tv_result.text=OCR.getText(this,imageUri)
            }

        }
    }

}