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
    private lateinit var intermediateStepsTextView: TextView
    private lateinit var stepsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        unsortedListTextView = findViewById(R.id.unsortedListTextView)
        inputNumberEditText = findViewById(R.id.inputNumberEditText)
        sortedListTextView = findViewById(R.id.sortedListTextView)
        intermediateStepsTextView = findViewById(R.id.intermediateStepsTextView)
        stepsTextView = findViewById(R.id.stepsTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val sortButton = findViewById<Button>(R.id.sortButton)
        val clearButton = findViewById<Button>(R.id.clearButton)


        addButton.setOnClickListener { addNumber() }
        sortButton.setOnClickListener { insertionSort() }
        clearButton.setOnClickListener { clearList() }

    }

    private fun clearList() { // Add this function
        numbers.clear()
        unsortedListTextView.text = "Unsorted List:"
        sortedListTextView.text = "Sorted List:"
        clearIntermediateSteps()
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
        val steps = mutableListOf<String>() // Add this line to store intermediate steps
        for (i in 1 until numbers.size) {
            val key = numbers[i]
            var j = i - 1
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j]
                j--
                steps.add("Step $i: ${numbers.joinToString(", ")}") // Record intermediate steps
            }
            numbers[j + 1] = key
        }

        displayIntermediateSteps(steps) // Display intermediate steps
        sortedListTextView.text = "Sorted List: ${numbers.joinToString(", ")}"
    }

    private fun displayIntermediateSteps(steps: List<String>) {
        intermediateStepsTextView.visibility = View.VISIBLE
        stepsTextView.text = steps.joinToString("\n")
    }

    private fun clearIntermediateSteps() {
        intermediateStepsTextView.visibility = View.GONE
        stepsTextView.text = ""
    }
}
