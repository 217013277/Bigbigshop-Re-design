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
import com.example.bigbigshopre_design.lists.category.Category
import com.example.bigbigshopre_design.lists.category.CategoryAdapter
import com.example.bigbigshopre_design.lists.category.CategoryClickListener
import com.example.bigbigshopre_design.lists.category.categoryList
import com.example.bigbigshopre_design.lists.product.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Category.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category : Fragment(), CategoryClickListener, ProductClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentCategoryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_category, container, false)
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        populateCategories()
        populateProducts1()

        binding.recyclerViewCategory.isNestedScrollingEnabled = false
        binding.recyclerViewCategory.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        categoryAdapter = CategoryAdapter(categoryList, this)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.recycleViewProduct.isNestedScrollingEnabled = false
        binding.recycleViewProduct.layoutManager = GridLayoutManager(activity?.applicationContext , 2)
        productAdapter = ProductAdapter(productList, this)
        binding.recycleViewProduct.adapter = productAdapter

        binding.productLists.visibility = View.GONE

        binding.breadcrumbTv1.setOnClickListener {
            binding.Header.text = "商品分類"
            binding.breadcrumb3.visibility = View.GONE
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            populateCategories()
            categoryAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.GONE
        }

        binding.breadcrumbTv3.setOnClickListener {
            binding.Header.text = "個人護理"
            binding.breadcrumb4.visibility = View.GONE
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            populateCategories3PersonalCare()
            categoryAdapter.notifyDataSetChanged()
            populateProducts1()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv4.setOnClickListener {
            binding.Header.text = "身體護理"
            binding.breadcrumb5.visibility = View.GONE
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            populateCategories4BodyCare()
            categoryAdapter.notifyDataSetChanged()
            populateProducts2()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        binding.breadcrumbTv5.setOnClickListener {
            binding.Header.text = "口罩及配件"
            binding.breadcrumb6.visibility = View.GONE
            binding.breadcrumb7.visibility = View.GONE
            populateCategories5Mask()
            categoryAdapter.notifyDataSetChanged()
            populateProducts3()
            productAdapter.notifyDataSetChanged()
            binding.productLists.visibility = View.VISIBLE
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Category.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Category().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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


    private fun populateCategories() {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(category: Category) {
        when(category.title) {
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
                populateCategories3PersonalCare()
                populateProducts1()
                binding.breadcrumbTv3.text = category.title
                binding.breadcrumb3.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "身體護理" -> {
                populateCategories4BodyCare()
                populateProducts2()
                binding.breadcrumbTv4.text = category.title
                binding.breadcrumb4.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "口罩及配件" -> {
                populateCategories5Mask()
                populateProducts3()
                binding.breadcrumbTv5.text = category.title
                binding.breadcrumb5.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            "成人口罩" -> {
                populateCategories6MaskAdult()
                populateProducts4()
                binding.breadcrumbTv6.text = category.title
                binding.breadcrumb6.visibility = View.VISIBLE
                binding.productLists.visibility = View.VISIBLE
            }
            else -> Log.i(TAG,"nothing select")
        }
        categoryAdapter.notifyDataSetChanged()
        productAdapter.notifyDataSetChanged()

        binding.Header.text = category.title
    }

    override fun onClick(product: Product) {
        val intent = Intent(activity?.applicationContext, DetailActivity::class.java)
        intent.putExtra(PRODUCT_ID_EXTRA, product.id)
        startActivity(intent)
    }
}