//package com.example.test3
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}
package com.example.test3
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val numbers = mutableListOf<Int>()
    private lateinit var unsortedListTextView: TextView
    private lateinit var inputNumberEditText: EditText
    private lateinit var sortedListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unsortedListTextView = findViewById(R.id.unsortedListTextView)
        inputNumberEditText = findViewById(R.id.inputNumberEditText)
        sortedListTextView = findViewById(R.id.sortedListTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val sortButton = findViewById<Button>(R.id.sortButton)
        val clearButton = findViewById<Button>(R.id.clearButton) // Add this line

        addButton.setOnClickListener { addNumber() }
        sortButton.setOnClickListener { insertionSort() }
        clearButton.setOnClickListener { clearList() }

    }

    private fun clearList() { // Add this function
        numbers.clear()
        unsortedListTextView.text = "Unsorted List:"
        sortedListTextView.text = "Sorted List:"
    }

    private fun addNumber() {
        val input = inputNumberEditText.text.toString().toIntOrNull()
        input?.let {
            numbers.add(input)
            unsortedListTextView.text = "Unsorted List: ${numbers.joinToString(", ")}"
            inputNumberEditText.text.clear()
        }
    }

    private fun insertionSort() {
        for (i in 1 until numbers.size) {
            val key = numbers[i]
            var j = i - 1
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j]
                j--
            }
            numbers[j + 1] = key
        }
        sortedListTextView.text = "Sorted List: ${numbers.joinToString(", ")}"
    }
}
