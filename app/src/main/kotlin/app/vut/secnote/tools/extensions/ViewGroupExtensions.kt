package app.vut.secnote.tools.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.layoutInflater(): LayoutInflater = LayoutInflater.from(context)