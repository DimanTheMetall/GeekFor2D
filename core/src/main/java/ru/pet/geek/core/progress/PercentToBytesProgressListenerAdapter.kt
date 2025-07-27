package ru.pet.geek.core.progress

import ru.pet.geek.core.Adapter

class PercentToBytesProgressListenerAdapter<RESULT> :
    Adapter<PercentProgressListener<RESULT>, BytesProgressListener<RESULT>> {

    override fun adapt(from: PercentProgressListener<RESULT>): BytesProgressListener<RESULT> {
        return BytesProgressListener<RESULT> { progressState ->
            from.onProgressChanged(progressState.adaptState())
        }
    }

    private fun ProgressChangedState<BytesProgress, RESULT>.adaptState(): ProgressChangedState<Float, RESULT> {
        return when (this) {
            is ProgressChangedState.Error -> ProgressChangedState.Error(e = e)
            is ProgressChangedState.InProgress<BytesProgress> -> {
                val progress = (progress.current / progress.total).toFloat().coerceIn(0.0f, 1.0f)
                ProgressChangedState.InProgress(progress = progress)
            }
            is ProgressChangedState.SuccessComplete<RESULT> -> ProgressChangedState.SuccessComplete<RESULT>(
                result = result,
            )
        }
    }

}