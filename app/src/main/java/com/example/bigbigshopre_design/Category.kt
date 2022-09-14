package com.example.bigbigshopre_design

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bigbigshopre_design.databinding.FragmentCategoryBinding
import com.example.bigbigshopre_design.lists.category.*
import com.example.bigbigshopre_design.lists.category.Category
import com.example.bigbigshopre_design.lists.product.*
import com.google.gson.Gson
import java.io.IOException
import org.json.JSONException
import java.nio.charset.Charset


/**
 * A simple [Fragment] subclass.
 * Use the [Category.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category : Fragment(), CategoryClickListener, ProductClickListener {

    private var _binding: FragmentCategoryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private var initialCategoryModelClass = CategoryModelClass(arrayListOf())
    private var currentCategoryModelClass = CategoryModelClass(arrayListOf())
    private var currentCategory = "商品分類"

    private var initialProductModelClass = ProductModelClass(arrayListOf())
    private var currentProductModelClass = ProductModelClass(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.productLists.visibility = View.GONE

        try {
            val jsonString = getJSONFromAssets("Categories.json")!!
            initialCategoryModelClass = Gson().fromJson(jsonString, CategoryModelClass::class.java)
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        populateCategoryList()

        try {
            val jsonString = getJSONFromAssets("Products.json")!!
            Log.d("Products", jsonString)
            initialProductModelClass = Gson().fromJson(jsonString, ProductModelClass::class.java)
            Log.d("Products", initialProductModelClass.products[0].toString())
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        populateProductList()

        binding.recyclerViewCategory.isNestedScrollingEnabled = false
        binding.recyclerViewCategory.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        categoryAdapter = CategoryAdapter(currentCategoryModelClass.categories, this)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.recycleViewProduct.isNestedScrollingEnabled = false
        binding.recycleViewProduct.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        productAdapter = ProductAdapter(requireActivity().applicationContext, currentProductModelClass.products, this)
        binding.recycleViewProduct.adapter = productAdapter

        var sortOrder = false
        binding.btnSort.setOnClickListener {
            if (!sortOrder) {
                currentProductModelClass.products.sortBy { it.name }
                sortOrder = true
            } else {
                currentProductModelClass.products.sortByDescending { it.name }
                sortOrder = false
            }

            productAdapter.notifyDataSetChanged()}

        binding.breadcrumbTv1.setOnClickListener {
            currentCategory = binding.breadcrumbTv1.text.toString()
            Log.d("currentCategory", currentCategory)
            binding.Header.text = currentCategory
            populateCategoryList()

            populateProductList()


            binding.Header.text = binding.Header.text
            binding.breadcrumb3.visibility = View.GONE
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            categoryAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.GONE
        }

        binding.breadcrumbTv3.setOnClickListener {
            binding.Header.text = "個人護理"
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE

            categoryAdapter.notifyDataSetChanged()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv4.setOnClickListener {
            binding.Header.text = "身體護理"
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            categoryAdapter.notifyDataSetChanged()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv5.setOnClickListener {
            binding.Header.text = "口罩及配件"
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            categoryAdapter.notifyDataSetChanged()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(categoryDataModel: Category) {
        currentCategory = categoryDataModel.title
        binding.Header.text = currentCategory
        // change category when list item clicked
        populateCategoryList()
        populateProductList()

        when(currentCategory) {
            "個人護理" -> {
                binding.breadcrumb3.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "身體護理" -> {
                binding.breadcrumb4.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "口罩及配件" -> {
                binding.breadcrumb5.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "成人口罩" -> {
                binding.breadcrumb6.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            else -> Log.i(TAG,"nothing select")
        }
        categoryAdapter.notifyDataSetChanged()
        productAdapter.notifyDataSetChanged()
    }

    private fun populateCategoryList() {
        currentCategoryModelClass.categories.clear()
        val temporaryCategoryList =
            initialCategoryModelClass.categories.filter { it.parent.contains(currentCategory) }
        currentCategoryModelClass.categories.addAll(temporaryCategoryList)
    }

    private fun populateProductList() {
        currentProductModelClass.products.clear()
        val tempProductList =
            initialProductModelClass.products.filter { it.categories.contains((currentCategory)) }
        currentProductModelClass.products.addAll(tempProductList)
    }

    override fun onClick(product: Product) {
        val intent = Intent(requireActivity().applicationContext, DetailActivity::class.java)
        intent.putExtra(PRODUCT_ID_EXTRA, product.id)
//        intent.putExtra("product", listOf(product))
        startActivity(intent)
    }

    private fun getJSONFromAssets(fileName: String): String? {
        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = this.requireContext().assets.open(fileName)
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}
