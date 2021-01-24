package com.example.employeedatabase

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dialog_update.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        //runs the function that displays the employees in the list
        showData()
        btnAdd.setOnClickListener {
            addRecord()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
    Function is used to get the Items List which is added in the database table
     */
    private fun getItemsList(): ArrayList<EmployeeModel> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmployeeModel> = databaseHandler.viewEmployee()

        return empList
    }

    /**
     * Function is used to show the list of inserted data.
     */

    private fun showData() {
        if (getItemsList().size > 0) {
            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            //set the LayoutManager that this RecyclerView will use
            rvItemsList.layoutManager = LinearLayoutManager(this)
            //Adapter class is initialized and list is passed in the parameter
            val itemAdapter = ItemAdapter(this, getItemsList())
            //adapter instance is set to the recyclerview to inflate the item
            rvItemsList.adapter = itemAdapter
        } else {
            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    /**
     * Used when the Add Employee button is clicked, it adds an employee to the list.
     */
    private fun addRecord() {
        val name = etName.text.toString()
        val email = etEmailId.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty()) {
            val status = databaseHandler.addEmployee(EmployeeModel(0, name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                showData()
                etName.text.clear()
                etEmailId.text.clear()
            } else {
                Toast.makeText(applicationContext, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }
        }

    }

    /**
     * Used when the Edit button is clicked, it shows the custom update dialog.
     */
    fun updateRecordDialog(employeeModel: EmployeeModel) {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*
        Sets the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen
         */
        updateDialog.setContentView(R.layout.dialog_update)
        updateDialog.etUpdateName.setText(employeeModel.name)
        updateDialog.etUpdateEmailID.setText(employeeModel.email)

        updateDialog.tvUpdate.setOnClickListener(View.OnClickListener {
            val name = updateDialog.etUpdateName.text.toString()
            val email = updateDialog.etUpdateEmailID.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if (!name.isEmpty() && !email.isEmpty()) {
                val status = databaseHandler.updateEmployee(EmployeeModel(employeeModel.id, name, email))


                if (status > -1) {
                    Toast.makeText(applicationContext, "Record updated", Toast.LENGTH_LONG).show()
                    showData()
                    updateDialog.dismiss()
                }
            } else {
                Toast.makeText(applicationContext, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }
        })

        updateDialog.tvCancel.setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })

        updateDialog.show()
    }

    /**
     * Used when the delete button is clicked, it shows the delete alert dialog
     */

    fun deleteRecordAlertDialog(employeeModel: EmployeeModel) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Record")
        builder.setMessage("Are you sure you want to delete ${employeeModel.name}?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val databaseHandler : DatabaseHandler = DatabaseHandler(this)

            val status = databaseHandler.deleteEmployee(EmployeeModel(employeeModel.id, "", ""))

            if (status > -1) {
                Toast.makeText(applicationContext, "Record delete successfully",Toast.LENGTH_LONG).show()
                showData()
            }
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss()
        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

}