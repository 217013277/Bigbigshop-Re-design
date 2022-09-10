package com.example.bigbigshopre_design

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigbigshopre_design.databinding.FragmentCartBinding
import com.example.bigbigshopre_design.lists.cartProduct.CartProduct
import com.example.bigbigshopre_design.lists.cartProduct.CartProductAdapter
import com.example.bigbigshopre_design.lists.cartProduct.CartProductClickListener
import com.example.bigbigshopre_design.lists.cartProduct.cartProductList

class Cart : Fragment(), CartProductClickListener {
    private var _binding: FragmentCartBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var cartProductAdapter: CartProductAdapter

    private var cartSum: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        populateProducts()

        binding.recyclerViewCartProduct.isNestedScrollingEnabled = false
        binding.recyclerViewCartProduct.layoutManager = LinearLayoutManager(activity?.applicationContext)
        cartProductAdapter = CartProductAdapter(cartProductList, this)
        binding.recyclerViewCartProduct.adapter = cartProductAdapter

        calculateSum()

//        binding.selectAll.setOnClickListener { selectAll() }
        binding.selectAllCheckBox.setOnClickListener { selectAll() }

        binding.addOnPromotionBar.setOnClickListener {
            if (binding.addOnPromotionContent.visibility == View.VISIBLE) {
                binding.addOnPromotionBarIndicator.rotation = 0.0F
                binding.addOnPromotionContent.visibility = View.GONE
            } else {
                binding.addOnPromotionBarIndicator.rotation = 90.0F
                binding.addOnPromotionContent.visibility = View.VISIBLE
            }
        }

        binding.bottomBarEditButton.setOnClickListener { showPopup(binding.bottomBarEditButton) }

        binding.nextButton.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, CheckoutActivity::class.java))
        }

        return view
    }

    private fun calculateSum() {
        cartSum = 0
        cartProductList.forEach {
            if (it.isCheck) cartSum += it.price * it.quantity
        }

        binding.cartSum.text = String.format("%.2f",cartSum.toDouble())
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(this.context, view)
        popup.inflate(R.menu.cart_product_edit)

        popup.setOnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.edit -> {
                    Toast.makeText(this.context, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.remove -> {
                    Toast.makeText(this.context, item.title, Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        popup.show()
    }

    private fun selectAll () {
        if (binding.selectAllCheckBox.isChecked) {
//            binding.selectAllCheckBox.isChecked  = true
            cartProductList.forEach { it.isCheck = true }
        } else {
//            binding.selectAllCheckBox.isChecked  = false
            cartProductList.forEach { it.isCheck = false }
        }
        cartProductAdapter.notifyItemRangeChanged(0, cartProductList.size)
    }

    private fun populateProducts() {
        cartProductList.clear()
        val products1 = CartProduct(R.drawable.mask_for_kid, "細口仔", "Carun x Med360+ 3S特柔放敏口罩 (50片獨立包裝) 小童口罩 ASTM LV3", 88, 123,1)
        cartProductList.add(products1)

        val products2 = CartProduct(R.drawable.mask_for_adult, "大口仔", "Carun x Med360+ 3S特柔放敏口罩 (50片獨立包裝) 成人口罩 ASTM LV3", 128, 188, 1)
        cartProductList.add(products2)
    }

    override fun onClick(cartProduct: CartProduct) {
    }

    override fun onEdit(id: Int) {
        showPopup(binding.recyclerViewCartProduct[id].findViewById(R.id.cartProductEditButton))
    }

    override fun onSelect(id: Int) {
        cartProductList[id].isCheck = !cartProductList[id].isCheck
        cartProductAdapter.notifyItemChanged(id)
        calculateSum()
    }

    override fun onDelete(id: Int) {
        cartProductList.removeAt(id)
        cartProductAdapter.notifyItemRemoved(id)
    }

    override fun onChange() {
        calculateSum()
    }

}