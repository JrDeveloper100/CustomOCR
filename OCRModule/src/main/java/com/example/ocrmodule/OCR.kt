package com.example.ocrmodule

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

object OCR {

    fun getText(context: Context, uri: Uri): String {
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

           val image = InputImage.fromFilePath(context, uri)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }


        val resultText = result.result.text
        val stringBuilder = StringBuilder()

        for (block in result.result.textBlocks) {
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

        return stringBuilder.toString()


    }

}