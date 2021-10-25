package ir.roela.simpleweathermodule

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinnerAdapter constructor(context: Context, resource: Int, items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {

    private var disabledPositions = arrayOf<Int>()

    // Affects default (closed) state of the spinner
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
//        view.setTextColor()
        return view
    }

    // Affects opened state of the spinner
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        setItemTextColor(position, view)
        view.layoutDirection = View.LAYOUT_DIRECTION_RTL
//        view.setBackgroundColor(ContextCompat.getColor(context, R.color.mid_gray))
        return view
    }

    private fun setItemTextColor(position: Int, textView: TextView) {
        for (disablePosition in disabledPositions) {
            if (disablePosition == position) {
                textView.setTextColor(Color.GRAY)
                return
            }
        }
//        return textView.setTextColor(
//            ContextCompat.getColor(
//                super.getContext(),
//                R.color.colorPrimaryDark
//            )
//        )
    }

    fun setDisablePosition(positions: Array<Int>) {
        this.disabledPositions = positions
    }

    override fun isEnabled(position: Int): Boolean {
        for (disablePosition in disabledPositions) {
            if (disablePosition == position) {
                return false
            }
        }
        return true
    }

}