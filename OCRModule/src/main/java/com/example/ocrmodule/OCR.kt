package com.example.ocrmodule

import android.content.Context
import android.net.Uri
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

object OCR {

    fun getText(context: Context, uri: Uri, tv_result: TextView): String {
        var resultText = ""
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

           val image = InputImage.fromFilePath(context, uri)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
                val stringBuilder = StringBuilder()

                for (block in visionText.textBlocks) {
                    for (line in block.lines) {
                        for (element in line.elements) {
                            val elementText = element.text
                            stringBuilder.append(elementText)
                            // Add space between elements within a line
                            stringBuilder.append(" ")
                        }
                        // Add a line break after each line
                        stringBuilder.append("\n")
                    }
                    // Add a double line break after each block
                    stringBuilder.append("\n\n")
                }

                resultText = stringBuilder.toString()
                tv_result.text = resultText



            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

//        val resultText = result.result.text
        return resultText
    }

}