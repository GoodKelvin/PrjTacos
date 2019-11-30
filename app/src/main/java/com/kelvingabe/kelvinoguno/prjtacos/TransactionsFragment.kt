package com.kelvingabe.kelvinoguno.prjtacos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.kelvingabe.kelvinoguno.prjtacos.TransactionsFragment.OnListFragmentInteractionListener
import com.kelvingabe.kelvinoguno.prjtacos.dummy.DummyContent.DummyItem
import com.kelvingabe.kelvinoguno.prjtacos.model.App
import com.kelvingabe.kelvinoguno.prjtacos.model.Transfer
import kotlinx.android.synthetic.main.fragment_transaction_list.*

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
class TransactionsFragment : Fragment() {
    var options: FirestoreRecyclerOptions<Transfer>? = null
    var query: Query? = null
    lateinit var adapter: FirestoreRecyclerAdapter<Transfer, ViewHolder>
    lateinit var recyclerView: RecyclerView
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? =
        null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_transaction_list, container, false)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnListFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnListFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {
        // TODO: Customize parameter argument names
        private const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): TransactionsFragment {
            val fragment = TransactionsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        query = FirebaseFirestore.getInstance()
            .collection("users")
            .document(App.prefs.userID!!)
            .collection("transfers")
            .orderBy("device_timestamp")
        options = FirestoreRecyclerOptions.Builder<Transfer>()
            .setQuery(query!!, Transfer::class.java)
            .build()
    }

    override fun onStart() {
        super.onStart()
        recyclerView = list
        adapter = object : FirestoreRecyclerAdapter<Transfer, ViewHolder>(options!!) {
            protected override fun onBindViewHolder(@NonNull viewHolder: ViewHolder, i: Int, @NonNull transfer: Transfer) {
                viewHolder.account_name_view.setText(transfer.getAccount_name())
                viewHolder.send_amount_view.setText("${R.string.USD_sign}${transfer.send_amt}")
                viewHolder.receive_amount_view.setText("${R.string.naira_sign}${transfer.receive_amt}")
                viewHolder.status_view.setText(transfer.getTransfer_status())
                val timestamp = transfer.getDevice_timestamp()
                val date = timestamp.toDate()
                viewHolder.date_view.setText(date.toLocaleString())
            }

            @NonNull
            override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.transfer_list_item, parent, false)
                return ViewHolder(view)
            }
        }
        recyclerView.setAdapter(adapter)
        val linearLayoutManager = LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false)
        //recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                getActivity(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //@BindView(R.id.transfer_item_account_name_textView)
        var account_name_view: TextView
        //@BindView(R.id.transfer_item_send_amount_textView)
        var send_amount_view: TextView
        //@BindView(R.id.transfer_item_date_textView)
        var date_view: TextView
        //@BindView(R.id.transfer_item_status_textView)
        var status_view: TextView
        //@BindView(R.id.transfer_item_receive_amount_textView)
        var receive_amount_view: TextView

        init {
            account_name_view =
                view.findViewById(R.id.transfer_item_account_name_textView)
            send_amount_view = view.findViewById(R.id.transfer_item_send_amount_textView)
            date_view = view.findViewById(R.id.transfer_item_date_textView)
            status_view = view.findViewById(R.id.transfer_item_status_textView)
            receive_amount_view =
                view.findViewById(R.id.transfer_item_receive_amount_textView)
        }
    }
}