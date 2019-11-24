package com.kelvingabe.kelvinoguno.prjtacos

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kelvingabe.kelvinoguno.prjtacos.adapter.AccountsAdapter
import com.kelvingabe.kelvinoguno.prjtacos.database.AppDatabase
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry
import com.kelvingabe.kelvinoguno.prjtacos.model.App
import com.kelvingabe.kelvinoguno.prjtacos.model.RecipientAccountInfo
import com.kelvingabe.kelvinoguno.prjtacos.model.Transaction
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.DecimalFormat
import java.util.*

class HomeFragment : Fragment(), TextWatcher {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null
    internal var mDb: AppDatabase? = null
    lateinit var mainViewModel: MainViewModel
    lateinit var accNames: ArrayList<String>
    lateinit var hybridNames: ArrayList<String>
    lateinit var adapter: AccountsAdapter
    private var accNumbers: ArrayList<String>? = null
    private var accBanks: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecipientInput()
        convertInputs()
    }

    @SuppressLint("ClickableViewAccessibility")
    protected fun configureRecipientInput() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
        mainViewModel.recipientAccounts.observe(this, Observer { entries ->
            adapter =
                AccountsAdapter(activity, R.layout.accounts_list_item, makeInfo(entries!!))
            adapter.setBank(accBanks)
            adapter.setAccNumber(accNumbers)
            adapter.setHybridInfo(hybridNames)
            to_editText!!.setAdapter(adapter)
        })

        to_editText!!.threshold = 0
        to_editText!!.setOnTouchListener { view, motionEvent ->
            to_editText!!.showDropDown()
            //validateAccountName();
            false
        }
        to_editText.setOnItemClickListener { parent, view, position, id ->
            Transaction.receiver_name = hybridNames[position]
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun makeInfo(movieEntries: List<RecipientAccountEntry>): List<String> {
        accNames = ArrayList()
        accNumbers = ArrayList()
        accBanks = ArrayList()
        hybridNames = ArrayList()
        for (i in movieEntries.indices) {
            val last3 = movieEntries[i].account_number.replace(".*?(.?.?.?)?$".toRegex(), "$1")
            var hybridName = movieEntries[i].full_name + " \n" + movieEntries[i].bank + "***$last3"
            accNames.add(i, movieEntries[i].full_name)
            accNumbers!!.add(i, movieEntries[i].account_number)
            accBanks!!.add(i, movieEntries[i].bank)
            hybridNames.add(hybridName)
        }
        val recipientAccountInfo = RecipientAccountInfo()
        recipientAccountInfo.accBank = accBanks
        return accNames
    }

    private fun convertInputs() {
        send_editText.addTextChangedListener(this);
        receive_editText.addTextChangedListener(this);
    }

    private fun usdToNaira(s: String): String {
        return if (s.trim { it <= ' ' }.isNotEmpty()) {
            val f = java.lang.Float.parseFloat(s) * 353
            val decimalFormat = DecimalFormat("#.##")
            val twoDigitsF = java.lang.Float.valueOf(decimalFormat.format(f))
            twoDigitsF.toString()
        } else {
            ""
        }
    }

    fun onSendClicked(view: View): Boolean {
        var checkRecipient = App.utils.isEditTextEmpty(to_editText, "Select a recipient")
        var checkSendAmount = App.utils.isEditTextEmpty(send_editText, "Field cannot be empty")
        var checkReceiveAmount =
            App.utils.isEditTextEmpty(receive_editText, "Field cannot be empty")
        return !(checkRecipient || checkReceiveAmount || checkSendAmount)
    }

    fun marshalData() {
        Transaction.receive_amount = receive_editText.text.toString()
        Transaction.send_amount = send_editText.text.toString()
    }

    private fun nairaToUSD(s: String): String {
        return if (s.trim { it <= ' ' }.isNotEmpty()) {
            val f = java.lang.Double.parseDouble(s) / 353
            val decimalFormat = DecimalFormat("#.##")
            val twoDigitsF = java.lang.Float.valueOf(decimalFormat.format(f))
            twoDigitsF.toString()
        } else {
            ""
        }
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {
        if (receive_editText!!.hasFocus()) {
            send_editText!!.removeTextChangedListener(this)
            val s = editable.toString()
            val d = nairaToUSD(s)
            send_editText!!.setText(d)
            send_editText!!.addTextChangedListener(this)
        } else if (send_editText!!.hasFocus()) {
            receive_editText!!.removeTextChangedListener(this)
            val s = editable.toString()
            val d = usdToNaira(s)
            receive_editText!!.setText(d)
            receive_editText!!.addTextChangedListener(this)
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
