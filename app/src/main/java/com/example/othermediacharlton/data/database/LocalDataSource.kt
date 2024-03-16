import com.example.othermediacharlton.data.database.FixturesEntity
import com.example.othermediacharlton.data.database.MatchesDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSource(private val database: MatchesDatabase) {

    fun getAllMatches(): Observable<List<FixturesEntity>> {
        return database.matchesDao().getAllMatches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertAllFixtures(match: List<FixturesEntity>): Completable {
        return Completable.fromCallable {
            database.matchesDao().insertMatch(match)
        }
            .subscribeOn(Schedulers.io())
    }

    fun deleteMatch(match: FixturesEntity): Completable {
        return database.matchesDao().deleteMatch(match)
    }
}