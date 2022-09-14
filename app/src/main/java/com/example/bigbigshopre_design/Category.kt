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
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_category, container, false)
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        try {
            val jsonString = getJSONFromAssets("Categories.json")!!
            initialCategoryModelClass = Gson().fromJson(jsonString, CategoryModelClass::class.java)
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        val firstCategoryList = initialCategoryModelClass.categories.filter { it.parent.contains(currentCategory) }
        currentCategoryModelClass.categories.addAll(firstCategoryList)

        try {
            val jsonString = getJSONFromAssets("Products.json")!!
            Log.d("Products", jsonString)
            initialProductModelClass = Gson().fromJson(jsonString, ProductModelClass::class.java)
            Log.d("Products", initialProductModelClass.products[0].toString())
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        currentProductModelClass.products.addAll(
            initialProductModelClass.products.filter { it.categories.contains("身體護理") }
        )

//        populateCategories()
//        populateProducts1()

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

//        binding.productLists.visibility = View.GONE

        binding.breadcrumbTv1.setOnClickListener {
            currentCategory = binding.breadcrumbTv1.text.toString()
            binding.Header.text = currentCategory
            currentCategoryModelClass.categories.clear()
            val temporaryList = initialCategoryModelClass.categories.filter { it.parent.contains(currentCategory) }
            currentCategoryModelClass.categories.addAll(temporaryList)

            binding.Header.text = binding.Header.text
            binding.breadcrumb3.visibility = View.GONE
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
//            populateCategories()
            categoryAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.GONE
        }

        binding.breadcrumbTv3.setOnClickListener {
            binding.Header.text = "個人護理"
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
//            populateCategories3PersonalCare()
            categoryAdapter.notifyDataSetChanged()
//            populateProducts1()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv4.setOnClickListener {
            binding.Header.text = "身體護理"
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
//            populateCategories4BodyCare()
            categoryAdapter.notifyDataSetChanged()
//            populateProducts2()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv5.setOnClickListener {
            binding.Header.text = "口罩及配件"
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
//            populateCategories5Mask()
            categoryAdapter.notifyDataSetChanged()
//            populateProducts3()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        return view
    }

    /*
    private fun populateProducts0() { productList.clear() }

    private fun populateProducts1()
    {
        productList.clear()
        val products1 = Product(R.drawable.shaver, "大吉大利", "男士剃鬚刀", "HK$199", "HK399")
        productList.add(products1)

        val products2 = Product(R.drawable.shampoo, "好乾淨", "洗頭水", "HK$55", "HK90")
        productList.add(products2)

        val products3 = Product(R.drawable.mask_for_kid, "細口仔", "兒童口罩", "HK$88", "HK123")
        productList.add(products3)

        val products4 = Product(R.drawable.mask_for_adult, "大口仔", "成人口罩", "HK$128", "HK188")
        productList.add(products4)
    }

    private fun populateProducts2()
    {
        productList.clear()

        val products2 = Product(R.drawable.shampoo, "好乾淨", "洗頭水", "HK$55", "HK90")
        productList.add(products2)

        val products3 = Product(R.drawable.mask_for_kid, "細口仔", "兒童口罩", "HK$88", "HK123")
        productList.add(products3)

        val products4 = Product(R.drawable.mask_for_adult, "大口仔", "成人口罩", "HK$128", "HK188")
        productList.add(products4)
    }

    private fun populateProducts3()
    {
        productList.clear()

        val products3 = Product(R.drawable.mask_for_kid, "細口仔", "兒童口罩", "HK$88", "HK123")
        productList.add(products3)

        val products4 = Product(R.drawable.mask_for_adult, "大口仔", "成人口罩", "HK$128", "HK188")
        productList.add(products4)
    }

    private fun populateProducts4()
    {
        productList.clear()

        val products8 = Product(R.drawable.mask_for_adult, "大口仔", "成人口罩", "HK$128", "HK188")
        productList.add(products8)
    }
     */

/*    private fun populateCategories() {
        categoryList.clear()
        val category1 = Category("講飲講食")
        categoryList.add(category1)
        val category2 = Category("護膚化妝")
        categoryList.add(category2)
        val category3 = Category("電子數碼")
        categoryList.add(category3)
        val category4 = Category("品味家居")
        categoryList.add(category4)
        val category5 = Category("個人護理")
        categoryList.add(category5)
        val category6 = Category("服裝配飾")
        categoryList.add(category6)
        val category7 = Category("保建養生")
        categoryList.add(category7)
        val category8 = Category("炊藝樂廚")
        categoryList.add(category8)
        val category9 = Category("母嬰育兒")
        categoryList.add(category9)
        val category10 = Category("玩具圖書")
        categoryList.add(category10)
        val category11 = Category("旅遊運動")
        categoryList.add(category11)
        val category12 = Category("文具精品")
        categoryList.add(category12)
        val category13 = Category("寵物用品")
        categoryList.add(category13)
    }

    private fun populateCategories3Food() {
        categoryList.clear()
        val category1 = Category("新鮮食品")
        categoryList.add(category1)
        val category2 = Category("急凍食品")
        categoryList.add(category2)
        val category3 = Category("冷凍食品")
        categoryList.add(category3)
        val category4 = Category("節慶食品")
        categoryList.add(category4)
        val category5 = Category("餐飲券")
        categoryList.add(category5)
        val category6 = Category("酒精飲料")
        categoryList.add(category6)
        val category7 = Category("飲料")
        categoryList.add(category7)
        val category8 = Category("海味乾貨")
        categoryList.add(category8)
        val category9 = Category("零食甜品")
        categoryList.add(category9)
        val category10 = Category("麵類")
        categoryList.add(category10)
        val category11 = Category("即食食品")
        categoryList.add(category11)
        val category12 = Category("罐頭乾貨")
        categoryList.add(category12)
        val category13 = Category("米食油")
        categoryList.add(category13)
    }

    private fun populateCategories3SkincareMakeup() {
        categoryList.clear()
        val category1 = Category("護膚品")
        categoryList.add(category1)
        val category2 = Category("美甲護甲")
        categoryList.add(category2)
        val category3 = Category("香水")
        categoryList.add(category3)
        val category4 = Category("化妝品")
        categoryList.add(category4)
    }

    private fun populateCategories3PersonalCare() {
        categoryList.clear()
        val category1 = Category("身體護理")
        categoryList.add(category1)
        val category2 = Category("頭髮護理")
        categoryList.add(category2)
        val category3 = Category("口腔護理")
        categoryList.add(category3)
        val category4 = Category("手部護理")
        categoryList.add(category4)
        val category5 = Category("足部護理")
        categoryList.add(category5)
        val category6 = Category("男士剃鬚")
        categoryList.add(category6)
        val category7 = Category("女士衛生")
        categoryList.add(category7)
        val category8 = Category("長者護理")
        categoryList.add(category8)
        val category9 = Category("成人用品")
        categoryList.add(category9)
    }

    private fun populateCategories4BodyCare() {
        categoryList.clear()
        val category1 = Category("口罩及配件")
        categoryList.add(category1)
        val category2 = Category("沐浴露")
        categoryList.add(category2)
        val category3 = Category("香皂")
        categoryList.add(category3)
        val category4 = Category("浴鹽/泡泡浴")
        categoryList.add(category4)
        val category5 = Category("磨砂/去角質工具")
        categoryList.add(category5)
        val category6 = Category("止汗香氛/爽身粉")
        categoryList.add(category6)
        val category7 = Category("潔膚棉/棉花棒")
        categoryList.add(category7)
        val category8 = Category("濕紙巾")
        categoryList.add(category8)
    }

    private fun populateCategories5Mask() {
        categoryList.clear()
        val category1 = Category("成人口罩")
        categoryList.add(category1)
        val category2 = Category("兒童口罩")
        categoryList.add(category2)
    }

    private fun populateCategories6MaskAdult() {
        categoryList.clear()
    }

 */

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(categoryDataModel: Category) {
        currentCategory = categoryDataModel.title
        binding.Header.text = currentCategory
        // change category when list item clicked
        currentCategoryModelClass.categories.clear()
        val temporaryCategoryList = initialCategoryModelClass.categories.filter { it.parent.contains(currentCategory) }
        currentCategoryModelClass.categories.addAll(temporaryCategoryList)

        when(currentCategory) {
            /*"講飲講食" -> {
                populateCategories_3_food()
                binding.breadcrumbTv3.text = category.title
                binding.breadcrumb3.visibility = View.VISIBLE
                productList.clear()
            }
            "護膚化妝" -> {
                populateCategories_3_skincare_makeup()
                binding.breadcrumbTv3.text = category.title
                binding.breadcrumb3.visibility = View.VISIBLE
            }*/
            "個人護理" -> {
//                populateCategories3PersonalCare()
//                populateProducts1()
//                binding.breadcrumbTv3.text = category.title
                binding.breadcrumb3.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "身體護理" -> {
//                populateCategories4BodyCare()
//                populateProducts2()
//                binding.breadcrumbTv4.text = category.title
                binding.breadcrumb4.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "口罩及配件" -> {
//                populateCategories5Mask()
//                populateProducts3()
//                binding.breadcrumbTv5.text = category.title
                binding.breadcrumb5.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "成人口罩" -> {
//                populateCategories6MaskAdult()
//                populateProducts4()
//                binding.breadcrumbTv6.text = category.title
                binding.breadcrumb6.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            else -> Log.i(TAG,"nothing select")
        }
        categoryAdapter.notifyDataSetChanged()
        productAdapter.notifyDataSetChanged()
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
