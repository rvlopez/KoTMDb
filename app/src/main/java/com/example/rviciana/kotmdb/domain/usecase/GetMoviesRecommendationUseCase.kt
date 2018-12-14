package com.example.rviciana.kotmdb.domain.usecase

import com.example.rviciana.kotmdb.data.MoviesRepository
import com.example.rviciana.kotmdb.domain.bo.MoviesResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class GetMoviesRecommendationUseCase @Inject constructor(
        private val moviesRepository: MoviesRepository,
        private val subscribeOn: Scheduler,
        private val observeOn: Scheduler
) {

    private var disposable: Disposable = Disposables.empty()

    fun execute(id: Int, onComplete: (MoviesResponse) -> Unit, onError: (Throwable) -> Unit) {
        disposable = moviesRepository.getMovieRecommendations(id)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(onComplete, onError)
    }

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}