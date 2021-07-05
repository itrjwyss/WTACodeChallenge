package com.aknowledgejourney.wtacodecallenge.data.local.entity

import android.os.Parcelable
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aknowledgejourney.wtacodecallenge.R
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity.Companion.TABLE_NAME
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey
    val charId: Long,
    val name: String,
    val birthday: String, //Can be Unknown
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    val betterCallSaul: List<String>,
    var favorite: Boolean = false
) : BaseObservable(), Parcelable {

    companion object {
        const val TABLE_NAME = "character_table"
        const val COLUMN_ID = "charID"
        const val COLUMN_FAV = "favorite"

        @BindingAdapter("app:srcCompat")
        @JvmStatic
        fun setSource(view: View, value: Any?) {
            if ((view is ImageView) && (value != null) && (value is String)) {
                Glide.with(view)
                    .load(value)
                    .into(view)
            } else if ((view is ImageButton) && (value != null) && (value is Boolean)) {
                val context = view.context
                if (value) {
                    view.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_favorite_24, context.theme))
                } else {
                    view.setImageDrawable(ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_favorite_border_24, context.theme))
                }
            }
        }
    }

    fun getOccupationStr(): String {
        return occupation.joinToString(separator = ", ")
    }

    fun getAppearanceStr(): String {
        return appearance.joinToString(separator = ", ")
    }

    fun getBetterCallSaulStr(): String {
        return betterCallSaul.joinToString(separator = ", ")
    }

}
