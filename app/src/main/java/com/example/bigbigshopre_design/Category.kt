package com.example.bigbigshopre_design

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigbigshopre_design.databinding.FragmentCategoryBinding
import com.example.bigbigshopre_design.lists.breadcrumb.BreadcrumbAdapter
import com.example.bigbigshopre_design.lists.breadcrumb.BreadcrumbClickListener
import com.example.bigbigshopre_design.lists.breadcrumb.BreadcrumbModelClass
import com.example.bigbigshopre_design.lists.breadcrumb.breadcrumbList
import com.example.bigbigshopre_design.lists.category.*
import com.example.bigbigshopre_design.lists.category.Category
import com.example.bigbigshopre_design.lists.product.*
import com.google.gson.Gson
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


/**
 * A simple [Fragment] subclass.
 * Use the [Category.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category : Fragment(), BreadcrumbClickListener, CategoryClickListener, ProductClickListener {

    private var _binding: FragmentCategoryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var breadcrumbAdapter: BreadcrumbAdapter
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

//        binding.productLists.visibility = View.GONE

        initBreadcrumbList()

        binding.recyclerViewBreadcrumb.layoutManager = LinearLayoutManager(activity?.applicationContext , LinearLayoutManager.HORIZONTAL, false)
        breadcrumbAdapter = BreadcrumbAdapter(breadcrumbList, this)
        binding.recyclerViewBreadcrumb.adapter = breadcrumbAdapter

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
            initialProductModelClass = Gson().fromJson(jsonString, ProductModelClass::class.java)
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        populateProductList()

        binding.recyclerViewCategory.isNestedScrollingEnabled = false
        binding.recyclerViewCategory.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        categoryAdapter = CategoryAdapter(currentCategoryModelClass.categoryList, this)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.recycleViewProduct.isNestedScrollingEnabled = false
        binding.recycleViewProduct.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        productAdapter = ProductAdapter(requireActivity().applicationContext, currentProductModelClass.productList, this)
        binding.recycleViewProduct.adapter = productAdapter

        var sortOrder = false
        binding.btnSort.setOnClickListener {
            if (!sortOrder) {
                currentProductModelClass.productList.sortBy { it.salesPrice }
                sortOrder = true
            } else {
                currentProductModelClass.productList.sortByDescending { it.salesPrice }
                sortOrder = false
            }

            productAdapter.notifyDataSetChanged()}

        return view
    }

    override fun onClick(breadcrumbModelClass: BreadcrumbModelClass) {
        currentCategory = breadcrumbModelClass.name
        val clickId = breadcrumbModelClass.id!!
        val size = breadcrumbList.size
        Log.d("clickedId", clickId.toString())
        if (clickId != breadcrumbList.size) {
            breadcrumbList.subList(clickId+1, breadcrumbList.size).clear()
            breadcrumbAdapter.notifyItemRangeRemoved(clickId+1,size - clickId )
        } else {
            breadcrumbList.removeAt(clickId)
            breadcrumbAdapter.notifyItemRemoved(clickId)
        }

        populateCategoryList()
        categoryAdapter.notifyDataSetChanged()
        populateProductList()
        productAdapter.notifyDataSetChanged()
    }

    private fun initBreadcrumbList() {
        breadcrumbList.clear()
        breadcrumbList.add(BreadcrumbModelClass(currentCategory))
    }

    private fun addBreadcrumbList(s: String) {
        breadcrumbList.add(BreadcrumbModelClass(s))
        breadcrumbAdapter.notifyItemInserted(breadcrumbList.size -1)
        binding.recyclerViewBreadcrumb.scrollToPosition(breadcrumbAdapter.itemCount-1)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(categoryDataModel: Category) {
        currentCategory = categoryDataModel.title
        binding.Header.text = currentCategory
        // change category when list item clicked
        populateCategoryList()
        populateProductList()

        categoryAdapter.notifyDataSetChanged()
        productAdapter.notifyDataSetChanged()

        addBreadcrumbList(currentCategory)
    }

    private fun populateCategoryList() {
        currentCategoryModelClass.categoryList.clear()
        val temporaryCategoryList =
            initialCategoryModelClass.categoryList.filter { it.parent.contains(currentCategory) }
        currentCategoryModelClass.categoryList.addAll(temporaryCategoryList)
    }

    private fun populateProductList() {
        currentProductModelClass.productList.clear()
        val tempProductList =
            initialProductModelClass.productList.filter { it.categories.contains((currentCategory)) }
        currentProductModelClass.productList.addAll(tempProductList)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val menuItem = menu.findItem(R.id.actionSearch)
        val searchView = menuItem.actionView as SearchView

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                Toast.makeText(requireContext(), "Open search", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Toast.makeText(requireContext(), "close search", Toast.LENGTH_SHORT).show()
                populateProductList()
                productAdapter.notifyDataSetChanged()
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val searchedProducts: ArrayList<Product> = ArrayList()
        val tempProductList =
            initialProductModelClass.productList.filter { it.categories.contains((currentCategory)) }
        // running a for loop to compare elements.
        for (item in tempProductList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.brand.lowercase().contains(text.lowercase()) ||
                item.name.lowercase().contains(text.lowercase())
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                searchedProducts.add(item)
            }
        }
        if (searchedProducts.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
//            productAdapter.updateList(searchedProducts)

            currentProductModelClass.productList.clear()
            currentProductModelClass.productList.addAll(searchedProducts)
            productAdapter.notifyDataSetChanged()
        }

    }
}
