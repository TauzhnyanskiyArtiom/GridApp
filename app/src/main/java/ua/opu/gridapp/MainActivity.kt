package ua.opu.gridapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.github.javafaker.Faker
import ua.opu.gridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private val faker = Faker()
    private var list:MutableList<Item> = initList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemAdapter(this) {
            val dialog = ItemDialog.newInstance(it.number)
            dialog.show(supportFragmentManager, "dlg")
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(this, 4)
        adapter.submitList(list)
    }

    private fun initList(): MutableList<Item> {
        var resultList = mutableListOf<Item>()
        for (i in 1..100)
            resultList.add(Item(faker.number().numberBetween(1, 99), faker.color().hex()))

        return resultList
    }


    data class Item(var number: Int, var color: String)
}