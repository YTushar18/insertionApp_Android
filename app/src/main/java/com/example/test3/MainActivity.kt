
package com.example.test3
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.app.AlertDialog
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

    private fun clearList() {
        numbers.clear()
        unsortedListTextView.text = "Unsorted List:"
        sortedListTextView.text = "Sorted List:"
        clearIntermediateSteps()
    }

    private fun addNumber() {
        if (numbers.size >= 8) {
            showMaxNumbersExceededDialog("You can add a maximum of 8 numbers.")
            unsortedListTextView.text = "Unsorted List: ${numbers.joinToString(", ")}"
            inputNumberEditText.text.clear()
        } else {
            val input = inputNumberEditText.text.toString().toIntOrNull()
            input?.let {
                numbers.add(input)
                unsortedListTextView.text = "Unsorted List: ${numbers.joinToString(", ")}"
                inputNumberEditText.text.clear()
            }
        }
    }

    private fun showMaxNumbersExceededDialog(msg : String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun insertionSort() {
        if (numbers.size < 2) {
            showMaxNumbersExceededDialog("You need to add 2 or more values to the array.")
        }
        else {
            val steps = mutableListOf<String>()
            var counter = 1
            for (i in 1 until numbers.size) {
                val key = numbers[i]
                var j = i - 1
                while (j >= 0 && numbers[j] > key) {
                    numbers[j + 1] = numbers[j]
                    j--
                    steps.add("Step $counter: ${numbers.joinToString(", ")}") // Record intermediate steps
                    counter++
                }
                numbers[j + 1] = key
            }

            displayIntermediateSteps(steps) // Display intermediate steps
            sortedListTextView.text = "Sorted List: ${numbers.joinToString(", ")}"
        }
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