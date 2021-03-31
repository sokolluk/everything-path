package cz.sokolluk.everythingpaths.extension

import io.reactivex.rxjava3.disposables.Disposable

fun Disposable?.disposeIfAbleTo() {
    this?.let { if (!isDisposed) dispose() }
}