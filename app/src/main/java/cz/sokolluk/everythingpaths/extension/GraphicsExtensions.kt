package cz.sokolluk.everythingpaths.extension

import android.content.res.Resources
import android.util.DisplayMetrics


val Number.dpToPx
    get() = toFloat() * (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)


val Number.pxToDp
    get() = toFloat() / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)