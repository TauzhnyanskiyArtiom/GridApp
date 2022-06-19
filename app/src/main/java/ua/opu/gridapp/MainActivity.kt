package ua.opu.gridapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.github.javafaker.Faker
import ua.opu.gridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ElementDialog.ItemDialogListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ElementAdapter
    private val faker = Faker()
    private var list:MutableList<Element> = initList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ElementAdapter(this) {
            val dialog = ElementDialog.newInstance(it.number)
            dialog.show(supportFragmentManager, "dlg")
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(this, 4)
        adapter.submitList(list)
    }

    private fun initList(): MutableList<Element> {
        var resultList = mutableListOf<Element>()
        for (i in 1..COUNT_ITEMS)
            resultList.add(Element(faker.number().numberBetween(1, 99), faker.color().hex()))

        return resultList
    }


    data class Element(var number: Int, var color: String)

    override fun onDialogResult() {
        adapter.submitList(initList())
    }

    companion object {
        const val COUNT_ITEMS = 100
    }
}