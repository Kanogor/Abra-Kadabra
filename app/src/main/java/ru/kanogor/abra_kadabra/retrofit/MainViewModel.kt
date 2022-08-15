package ru.kanogor.abra_kadabra.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _name = MutableLiveData("Nothing")
    val name: LiveData<String> = _name

    private val _gender = MutableLiveData("Nothing")
    val gender: LiveData<String> = _gender

    private val _location = MutableLiveData("Nothing")
    val location: LiveData<String> = _location

    private val _email = MutableLiveData("Nothing")
    val email: LiveData<String> = _email

    private val _login = MutableLiveData("Nothing")
    val login: LiveData<String> = _login

    private val _dob = MutableLiveData("Nothing")
    val dob: LiveData<String> = _dob

    private val _registered = MutableLiveData("Nothing")
    val registered: LiveData<String> = _registered

    private val _phone = MutableLiveData("Nothing")
    val phone: LiveData<String> = _phone

    private val _cell = MutableLiveData("Nothing")
    val cell: LiveData<String> = _cell

    private val _id = MutableLiveData("Nothing")
    val id: LiveData<String> = _id

    private var _nat = MutableLiveData("Nothing")
    val nat: LiveData<String> = _nat

    private val _url = MutableLiveData("Nothing")
    val url: LiveData<String> = _url


    fun updateApi() {
        viewModelScope.launch {
            _state.value = State.Loading
            val searchApi = RetrofitInstance.searchUserApi.getInfo()
            if (searchApi.isSuccessful) {
                val resultsData = searchApi.body()?.results!![0]
                val title = resultsData.name.title
                val first = resultsData.name.first
                val last = resultsData.name.last
                _name.value = "$title $first $last"
                _gender.value = resultsData.gender
                val streetNumber = resultsData.location.street.number
                val streetName = resultsData.location.street.name
                val city = resultsData.location.city
                val state = resultsData.location.state
                val country = resultsData.location.country
                val postcode = resultsData.location.postcode
                val coordinatesLat = resultsData.location.coordinates.latitude
                val coordinatesLong = resultsData.location.coordinates.longitude
                val timezoneOffset = resultsData.location.timezone.offset
                val timezoneDescr = resultsData.location.timezone.description
                _location.value = """
                                    $streetNumber, $streetName
                                    $city
                                    $state
                                    $country
                                    $postcode
                                    $coordinatesLat, $coordinatesLong
                                    $timezoneOffset 
                                    $timezoneDescr
                                 """.trimIndent()
                _email.value = resultsData.email
                val login = resultsData.login.username
                val password = resultsData.login.password
                _login.value = """
                                $login
                                Password: $password
                               """.trimIndent()
                val dob = resultsData.dob.date
                val dobAge = resultsData.dob.age
                _dob.value = """
                                $dob
                                Age: $dobAge
                             """.trimIndent()
                val registrDate = resultsData.registered.date
                val registrAge = resultsData.registered.age
                _registered.value = """
                                        $registrDate
                                        Age: $registrAge
                                    """.trimIndent()
                _phone.value = resultsData.phone
                _cell.value = resultsData.cell
                val idName = resultsData.id.name
                val idValue = resultsData.id.value
                _id.value = "$idName, $idValue"
                _nat.value = resultsData.nat
                _url.value = resultsData.picture.large
                _state.value = State.Success
            } else {
                _state.value = State.Fail("Упс, попробуйте еще раз")
            }
        }
    }
}