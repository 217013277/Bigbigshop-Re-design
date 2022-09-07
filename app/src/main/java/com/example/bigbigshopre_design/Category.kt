package com.example.bigbigshopre_design

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
import com.example.bigbigshopre_design.lists.book.*
import com.example.bigbigshopre_design.lists.category.CATEGORY_ID_EXTRA
import com.example.bigbigshopre_design.lists.category.Category
import com.example.bigbigshopre_design.lists.category.CategoryAdapter
import com.example.bigbigshopre_design.lists.category.CategoryClickListener
import com.example.bigbigshopre_design.lists.category.categoryList
import com.example.bigbigshopre_design.lists.product.Product
import com.example.bigbigshopre_design.lists.product.ProductAdapter
import com.example.bigbigshopre_design.lists.product.ProductClickListener
import com.example.bigbigshopre_design.lists.product.productList


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

        populateBooks()
        populateCategories()
        populateProducts()

        val thisActivity = this
        binding.recycleView.isNestedScrollingEnabled = false
        binding.recycleView.apply {
            layoutManager = GridLayoutManager(activity?.applicationContext , 2)
            adapter = CategoryAdapter(categoryList, thisActivity)
        }

        binding.recycleViewProduct.isNestedScrollingEnabled = false
        binding.recycleViewProduct.apply {
            layoutManager = GridLayoutManager(activity?.applicationContext , 2)
            adapter = ProductAdapter(productList, thisActivity)
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

    private fun populateBooks() {
        bookList.clear()
        val book1 = Book(
            R.drawable.abtm,
            "Victoria Devine",
            "AGELESS BODY, TIMELESS MIND",
            "The definitive text on the healing powers of the mind/body connection. In Ageless Body, Timeless Mind, world-renowned pioneer of integrative medicine Deepak Chopra goes beyond ancient mind/body wisdom and current anti-ageing research to show that you do not have to grow old. With the passage of time, you can retain your physical vitality, creativity, memory and self-esteem. Based on the theories of Ayurveda and groundbreaking research, Chopra reveals how we can use our innate capacity for balance to direct the way our bodies metabolize time and achieve our unbounded potential."
        )
        bookList.add(book1)
        val book2 = Book(
            R.drawable.tmom,
            "Amanda Lohrey",
            "THE MIRACLE OF MINDFULNESS",
            "This is the definitive book on mindfulness from the beloved Zen master and Nobel Peace Prize nominee Thich Nhat Hanh. With his signature clarity and warmth, he shares practical exercises and anecdotes to help us arrive at greater self-understanding and peacefulness, whether we are beginners or advanced students.\n" + "\n" + "Beautifully written, The Miracle of Mindfulness is the essential guide to welcoming presence in your life and truly living in the moment from the father of mindfulness.\n"
        )
        bookList.add(book2)
        val book3 = Book(
            R.drawable.trlt,
            "M. Scott Peck",
            "THE ROAD LESS TRAVELLED",
            "A timeless classic in personal development, The Road Less Travelled is a landmark work that has inspired millions. Drawing on the experiences of his career as a psychiatrist, Scott Peck combines scientific and spiritual views to guide us through the difficult, painful times in life by showing us how to confront our problems through the key principles of discipline, love and grace.Teaching us how to distinguish dependency from love, how to become a more sensitive parent and how to connect with your true self, this incredible book is the key to accepting and overcoming life's challenges and achieving a higher level of self-understanding."
        )
        bookList.add(book3)
        val book4 = Book(
            R.drawable.iewu,
            "Colleen Hoover",
            "IT ENDS WITH US",
            "'A brave and heartbreaking novel that digs its claws into you and doesn't let go, long after you've finished it' Anna Todd, author of the After series\n" + "\n" + "'A glorious and touching read, a forever keeper' USA Today\n" + "\n" + "'Will break your heart while filling you with hope' Sarah Pekkanen, Perfect Neighbors\n",
        )
        bookList.add(book4)
        val book5 = Book(
            R.drawable.ips,
            "Ross Coulthart",
            "IN PLAIN SIGHT",
            "Investigative journalist Ross Coulthart has been intrigued by UFOs since mysterious glowing lights were reported near New Zealand's Kaikoura mountains when he was a teenager. The 1978 sighting is just one of thousands since the 1940s, and yet research into UFOs is still seen as the realm of crackpots and conspiracy theorists."
        )
        bookList.add(book5)
        val book6 = Book(
            R.drawable.ttmc,
            "Richard Osman",
            "THE THURSDAY MURDER CLUB",
            "In a peaceful retirement village, four unlikely friends meet up once a week to investigate unsolved murders.\n" + "\n" + "But when a brutal killing takes place on their very doorstep, the Thursday Murder Club find themselves in the middle of their first live case.\n" + "\n" + "Elizabeth, Joyce, Ibrahim and Ron might be pushing eighty but they still have a few tricks up their sleeves.",
        )
        bookList.add(book6)
        val book7 = Book(
            R.drawable.wyam,
            "Michael Robotham",
            "WHEN YOU ARE MINE",
            "Philomena McCarthy has defied the odds and become a promising young officer with the Metropolitan Police despite being the daughter of a notorious London gangster. Called to the scene of a domestic assault one day, she rescues a bloodied young woman, Tempe Brown, the mistress of a decorated detective. The incident is hushed up, but Phil has unwittingly made a dangerous enemy with powerful friends.\n"
        )
        bookList.add(book7)
    }

    private fun populateProducts()
    {
        val products1 = Product(
            R.drawable.abtm,
            "Victoria Devine",
            "AGELESS BODY, TIMELESS MIND",
            "The definitive text on the healing powers of the mind/body connection. In Ageless Body, Timeless Mind, world-renowned pioneer of integrative medicine Deepak Chopra goes beyond ancient mind/body wisdom and current anti-ageing research to show that you do not have to grow old. With the passage of time, you can retain your physical vitality, creativity, memory and self-esteem. Based on the theories of Ayurveda and groundbreaking research, Chopra reveals how we can use our innate capacity for balance to direct the way our bodies metabolize time and achieve our unbounded potential."
        )
        productList.add(products1)

        val book2 = Book(
            R.drawable.tmom,
            "Amanda Lohrey",
            "THE MIRACLE OF MINDFULNESS",
            "This is the definitive book on mindfulness from the beloved Zen master and Nobel Peace Prize nominee Thich Nhat Hanh. With his signature clarity and warmth, he shares practical exercises and anecdotes to help us arrive at greater self-understanding and peacefulness, whether we are beginners or advanced students.\n" + "\n" + "Beautifully written, The Miracle of Mindfulness is the essential guide to welcoming presence in your life and truly living in the moment from the father of mindfulness.\n"
        )
        bookList.add(book2)

        val book3 = Book(
            R.drawable.trlt,
            "M. Scott Peck",
            "THE ROAD LESS TRAVELLED",
            "A timeless classic in personal development, The Road Less Travelled is a landmark work that has inspired millions. Drawing on the experiences of his career as a psychiatrist, Scott Peck combines scientific and spiritual views to guide us through the difficult, painful times in life by showing us how to confront our problems through the key principles of discipline, love and grace.Teaching us how to distinguish dependency from love, how to become a more sensitive parent and how to connect with your true self, this incredible book is the key to accepting and overcoming life's challenges and achieving a higher level of self-understanding."
        )
        bookList.add(book3)

        val book4 = Book(
            R.drawable.iewu,
            "Colleen Hoover",
            "IT ENDS WITH US",
            "'A brave and heartbreaking novel that digs its claws into you and doesn't let go, long after you've finished it' Anna Todd, author of the After series\n" + "\n" + "'A glorious and touching read, a forever keeper' USA Today\n" + "\n" + "'Will break your heart while filling you with hope' Sarah Pekkanen, Perfect Neighbors\n",
        )
        bookList.add(book4)

        val book5 = Book(
            R.drawable.ips,
            "Ross Coulthart",
            "IN PLAIN SIGHT",
            "Investigative journalist Ross Coulthart has been intrigued by UFOs since mysterious glowing lights were reported near New Zealand's Kaikoura mountains when he was a teenager. The 1978 sighting is just one of thousands since the 1940s, and yet research into UFOs is still seen as the realm of crackpots and conspiracy theorists."
        )
        bookList.add(book5)

        val book6 = Book(
            R.drawable.ttmc,
            "Richard Osman",
            "THE THURSDAY MURDER CLUB",
            "In a peaceful retirement village, four unlikely friends meet up once a week to investigate unsolved murders.\n" + "\n" + "But when a brutal killing takes place on their very doorstep, the Thursday Murder Club find themselves in the middle of their first live case.\n" + "\n" + "Elizabeth, Joyce, Ibrahim and Ron might be pushing eighty but they still have a few tricks up their sleeves.",
        )
        bookList.add(book6)

        val book7 = Book(
            R.drawable.wyam,
            "Michael Robotham",
            "WHEN YOU ARE MINE",
            "Philomena McCarthy has defied the odds and become a promising young officer with the Metropolitan Police despite being the daughter of a notorious London gangster. Called to the scene of a domestic assault one day, she rescues a bloodied young woman, Tempe Brown, the mistress of a decorated detective. The incident is hushed up, but Phil has unwittingly made a dangerous enemy with powerful friends.\n"
        )
        bookList.add(book7)
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

    override fun onClick(category: Category) {
//        val intent = Intent(activity?.applicationContext, DetailActivity::class.java)
//        Log.i(TAG,category.id.toString())
//        intent.putExtra(CATEGORY_ID_EXTRA, category.id)
//        startActivity(intent)
    }

    override fun onClick(product: Product) {
        TODO("Not yet implemented")
    }
}